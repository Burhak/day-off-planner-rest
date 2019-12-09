package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.LeaveRequestAssembler
import com.evolveum.day_off_planner_rest.data.entity.*
import com.evolveum.day_off_planner_rest.data.enums.LeaveRequestStatus
import com.evolveum.day_off_planner_rest.data.repository.LeaveRequestApprovalRepository
import com.evolveum.day_off_planner_rest.data.repository.LeaveRequestMessageRepository
import com.evolveum.day_off_planner_rest.data.repository.LeaveRequestRepository
import com.evolveum.day_off_planner_rest.exception.*
import com.evolveum.day_off_planner_rest.util.date.*
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestAddMessageApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestCreateApiModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Predicate

@Service
@Transactional
class LeaveRequestService(
        @PersistenceContext private val entityManager: EntityManager,
        private val leaveRequestRepository: LeaveRequestRepository,
        private val leaveRequestApprovalRepository: LeaveRequestApprovalRepository,
        private val leaveRequestMessageRepository: LeaveRequestMessageRepository,
        private val leaveRequestAssembler: LeaveRequestAssembler,
        private val userService: UserService,
        private val settingService: SettingService,
        private val limitService: LimitService,
        private val holidayService: HolidayService,
        private val emailService: EmailService,
        private val leaveTypeService: LeaveTypeService
) {

    fun getLeaveRequestById(id: UUID): LeaveRequest = leaveRequestRepository.findOneById(id)
            ?: throw NotFoundException("Leave request with id $id was not found")

    fun getLeaveRequestByIdWithApproverCheck(id: UUID): LeaveRequest = getLeaveRequestById(id)
            .also { if (it.approvals.isNotEmpty()) getApproval(userService.getLoggedUser(), it) }

    fun getRequestedHours(userId: UUID, leaveTypeId: UUID, year: Int?): Int = getRequestedHoursForYear(
            userService.getUserById(userId),
            leaveTypeService.getLeaveTypeById(leaveTypeId),
            year ?: LocalDate.now().year,
            settingService.getWorkDayStartEnd()
    )

    fun filterLeaveRequests(from: LocalDate?, to: LocalDate?, status: List<LeaveRequestStatus>, users: List<UUID>, leaveTypes: List<UUID>, approvers: List<UUID>): List<LeaveRequest> {
        val builder = entityManager.criteriaBuilder
        val query = builder.createQuery(LeaveRequest::class.java)
        val root = query.from(LeaveRequest::class.java)

        val predicates = mutableListOf<Predicate>()

        if (from != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get<LocalDateTime>("toDate"), from.atStartOfDay()))

        if (to != null)
            predicates.add(builder.lessThan(root.get<LocalDateTime>("fromDate"), to.plusDays(1).atStartOfDay()))

        if (status.isNotEmpty())
            predicates.add(root.get<LeaveRequestStatus>("status").`in`(status))

        if (users.isNotEmpty())
            predicates.add(root.get<User>("user").`in`(users.map { userService.getUserById(it) }))

        if (leaveTypes.isNotEmpty())
            predicates.add(root.get<LeaveType>("type").`in`(leaveTypes.map { leaveTypeService.getLeaveTypeById(it) }))

        if (approvers.isNotEmpty()) {
            val approverPath = root.joinList<LeaveRequest, LeaveRequestApproval>("approvals").get<User>("approver")
            approvers.forEach { approverId -> predicates.add(builder.equal(approverPath, userService.getUserById(approverId))) }
        }

        query.where(*predicates.toTypedArray()).orderBy(builder.desc(root.get<LocalDateTime>("fromDate")), builder.desc(root.get<LocalDateTime>("toDate")))
        return entityManager.createQuery(query).resultList
    }

    fun createLeaveRequest(leaveRequestCreateApiModel: LeaveRequestCreateApiModel): LeaveRequest {
        val leaveRequest = leaveRequestAssembler.disassemble(leaveRequestCreateApiModel, userService.getLoggedUser())

        leaveRequest.checkLimit()

        // if user has a supervisor create approvals
        val supervisor = leaveRequest.user.supervisor
        if (leaveRequest.type.approvalNeeded && supervisor != null) {
            leaveRequest.approvals = listOf(LeaveRequestApproval(leaveRequest, supervisor)) +
                    leaveRequest.user.approvers.map { approver -> LeaveRequestApproval(leaveRequest, approver) }
            leaveRequest.status = LeaveRequestStatus.PENDING
        } else {
            leaveRequest.status = LeaveRequestStatus.APPROVED
        }

        return leaveRequestRepository.save(leaveRequest).apply {
            if (status == LeaveRequestStatus.PENDING) {
                approvals.forEach {
                    emailService.sendMessage(
                            it.approver.email,
                            "Leave request approval",
                            "User ${user.fullName} has requested ${type.name}. Please visit .../leave/$id to approve/reject this request.")
                }

                emailService.sendMessage(user.email, "Leave request submitted", "Your leave request (${type.name}) is waiting for approval. You will be notified once approved/rejected.")
            } else {
                emailService.sendMessage(user.email, "Leave request approved", "Your leave request (${type.name}) was APPROVED.")
                if (user.supervisor != null)
                    emailService.sendMessage(
                            user.supervisor!!.email,
                            "${user.fullName} - ${type.name}",
                            "User ${user.fullName} has created new leave (${type.name})"
                    )

            }
        }
    }

    fun cancelLeaveRequest(id: UUID): LeaveRequest {
        val leaveRequest = getLeaveRequestById(id)
        val user = userService.getLoggedUser()

        if (user != leaveRequest.user)
            throw NotAllowedException("You are not allowed to cancel this leave request")

        leaveRequest.canCancel()
        return leaveRequest.cancel()
    }

    fun approveLeaveRequest(id: UUID, approve: Boolean): LeaveRequest {
        val leaveRequest = getLeaveRequestById(id)
        val user = userService.getLoggedUser()

        val approval = getApproval(user, leaveRequest)

        leaveRequest.checkPending()

        if (approval.approved != null)
            throw NotAllowedException("You have already voted for this leave request")

        leaveRequestApprovalRepository.save(approval.apply { approved = approve })
        return leaveRequest.processVoting()
    }

    fun forceApproveLeaveRequest(id: UUID, approve: Boolean): LeaveRequest {
        val leaveRequest = getLeaveRequestById(id)
        val user = userService.getLoggedUser()

        if (user != leaveRequest.user.supervisor)
            throw NotAllowedException("Only supervisor can force approve leave request")

        leaveRequest.checkPending()

        return if (approve) leaveRequest.approve() else leaveRequest.reject()
    }

    fun addMessage(leaveRequestAddMessageApiModel: LeaveRequestAddMessageApiModel, id: UUID): LeaveRequest {
        val leaveRequest = getLeaveRequestById(id)
        val user = userService.getLoggedUser()
        getApproval(user, leaveRequest)

        leaveRequestMessageRepository.save(LeaveRequestMessage(leaveRequest, user, leaveRequestAddMessageApiModel.message))

        // notify rest of approvers
        leaveRequest.approvals.filter { it.approver != user }.forEach {
            emailService.sendMessage(
                    it.approver.email,
                    "New message - ${leaveRequest.user.fullName} (${leaveRequest.type.name})",
                    "New message was added to leave request of ${leaveRequest.user.fullName} (${leaveRequest.type.name}): \n\n${leaveRequestAddMessageApiModel.message}"
            )
        }
        return leaveRequest
    }

    private fun getApproval(approver: User, leaveRequest: LeaveRequest): LeaveRequestApproval =
            leaveRequestApprovalRepository.findOne(approver, leaveRequest)
                    ?: throw NotAllowedException("You are not one of approvers for this leave request")

    private fun LeaveRequest.processVoting(): LeaveRequest = when {
        approvals.any { it.approved == null } -> this               // some approvals are not resolved yet
        approvals.all { it.approved == true } -> approve()          // all approvals are approved => approve request
        approvals.all { it.approved == false } -> reject()          // all approvals are rejected => reject request
        else -> this.apply {
            if (user.supervisor != null) emailService.sendMessage(  // notify supervisor on conflict
                user.supervisor!!.email,
                "${user.fullName} - approval conflict",
                "All approvers have voted in leave request of ${user.fullName} (${type.name}) but their votes differ. Resolve this conflict by visiting .../leave/$id")
        }
    }

    private fun LeaveRequest.approve(): LeaveRequest {
        status = LeaveRequestStatus.APPROVED
        emailService.sendMessage(user.email, "Leaver request approved", "Your leave request (${type.name}) was APPROVED.")
        return leaveRequestRepository.save(this)
    }

    private fun LeaveRequest.reject(): LeaveRequest {
        status = LeaveRequestStatus.REJECTED
        emailService.sendMessage(user.email, "Leaver request rejected", "Your leave request (${type.name}) was REJECTED.")
        return leaveRequestRepository.save(this)
    }

    private fun LeaveRequest.cancel(): LeaveRequest {
        status = LeaveRequestStatus.CANCELLED
        return leaveRequestRepository.save(this)
    }

    private fun LeaveRequest.checkLimit() {
        // these declarations are here at the beginning to validate also not limited requests
        val workDayStartEnd = settingService.getWorkDayStartEnd()
        val range = DateRange(this, workDayStartEnd, true)

        if (!type.isLimited()) return

        range.splitToYears().forEach { year ->
            val requesting = year.duration()
            val totalRequested = getRequestedHoursForYear(user, type, year.year, workDayStartEnd)

            val limit = limitService.getUserLimit(user, type, year.year)
            if (totalRequested + requesting > limit)
                throw LimitExceededException("Limit of this leave type has been exceeded")
        }
    }

    private fun LeaveRequest.checkPending() {
        if (status != LeaveRequestStatus.PENDING)
            throw AlreadyResolvedException("This leave request has been already $status")
    }

    private fun LeaveRequest.canCancel() {
        if (status != LeaveRequestStatus.PENDING && status != LeaveRequestStatus.APPROVED)
            throw AlreadyResolvedException("This leave request has been already $status")
    }

    private fun Year.duration(): Int {
        val duration = splitToDays()
                .filterNot { it.day.isWeekend() || holidayService.isHoliday(it.day) }
                .sumBy { it.duration() }

        if (duration < 1) throw WrongParamException("End must be at least 1 hour after start")

        return duration
    }

    private fun getRequestedHoursForYear(user: User, leaveType: LeaveType, year: Int, workDayStartEnd: DayStartEnd): Int =
            leaveRequestRepository.findLeavesByYear(user, leaveType, year)
                    .sumBy { DateRange(it, workDayStartEnd, false).takeYear(year).duration() }
}