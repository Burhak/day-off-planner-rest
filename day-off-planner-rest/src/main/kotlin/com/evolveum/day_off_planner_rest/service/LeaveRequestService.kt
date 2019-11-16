package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.LeaveRequestAssembler
import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import com.evolveum.day_off_planner_rest.data.entity.LeaveRequestApproval
import com.evolveum.day_off_planner_rest.data.enums.LeaveRequestStatus
import com.evolveum.day_off_planner_rest.data.repository.LeaveRequestRepository
import com.evolveum.day_off_planner_rest.exception.LimitExceededException
import com.evolveum.day_off_planner_rest.exception.WrongParamException
import com.evolveum.day_off_planner_rest.util.date.DateRange
import com.evolveum.day_off_planner_rest.util.date.DayStartEnd
import com.evolveum.day_off_planner_rest.util.date.Year
import com.evolveum.day_off_planner_rest.util.date.isWeekend
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestCreateApiModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class LeaveRequestService(
        private val leaveRequestRepository: LeaveRequestRepository,
        private val leaveRequestAssembler: LeaveRequestAssembler,
        private val userService: UserService,
        private val settingService: SettingService,
        private val limitService: LimitService,
        private val holidayService: HolidayService,
        private val emailService: EmailService
) {

    fun duration(from: LocalDateTime, to: LocalDateTime): List<Int> =
        DateRange(from, to, settingService.getWorkDayStartEnd(), true).splitToYears().map { it.duration() }

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
                            "User ${user.fullName} has requested ${type.name}. Please visit .../$id to approve/reject this request.")
                }

                emailService.sendMessage(user.email, "Leave request submitted", "Your leave request (${type.name}) is waiting for approval. You will be notified once approved/rejected.")
            } else {
                emailService.sendMessage(user.email, "Leave request approved", "Your leave request (${type.name}) was APPROVED.")
            }
        }
    }

    private fun LeaveRequest.checkLimit() {
        if (!type.isLimited()) return

        val workDayStartEnd = settingService.getWorkDayStartEnd()

        DateRange(this, workDayStartEnd, true).splitToYears().forEach { year ->
            val requesting = year.duration()
            val totalRequested = getRequestedHoursForYear(year.year, workDayStartEnd)

            val limit = limitService.getUserLimit(user, type, year.year)
            if (totalRequested + requesting > limit)
                throw LimitExceededException("Limit of this leave type has been exceeded")
        }
    }

    private fun Year.duration(): Int {
        val duration = splitToDays()
                .filterNot { it.day.isWeekend() || holidayService.isHoliday(it.day) }
                .sumBy { it.duration() }

        if (duration < 1) throw WrongParamException("End must be at least 1 hour after start")

        return duration
    }

    private fun LeaveRequest.getRequestedHoursForYear(year: Int, workDayStartEnd: DayStartEnd): Int =
        leaveRequestRepository.findLeavesByYear(user, type, year)
                .sumBy { DateRange(it, workDayStartEnd, false).takeYear(year).duration() }
}