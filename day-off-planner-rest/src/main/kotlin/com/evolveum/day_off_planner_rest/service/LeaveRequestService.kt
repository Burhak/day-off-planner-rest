package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.LeaveRequestAssembler
import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import com.evolveum.day_off_planner_rest.data.entity.LeaveRequestApproval
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.data.enums.LeaveRequestStatus
import com.evolveum.day_off_planner_rest.data.repository.LeaveRequestRepository
import com.evolveum.day_off_planner_rest.exception.LimitExceededException
import com.evolveum.day_off_planner_rest.exception.WrongParamException
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestCreateApiModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.LocalDateTime

@Service
@Transactional
class LeaveRequestService(
        private val leaveRequestRepository: LeaveRequestRepository,
        private val leaveRequestAssembler: LeaveRequestAssembler,
        private val userService: UserService,
        private val settingService: SettingService
) {

    fun createLeaveRequest(leaveRequestCreateApiModel: LeaveRequestCreateApiModel): LeaveRequest {
        val leaveRequest = leaveRequestAssembler.disassemble(leaveRequestCreateApiModel, userService.getLoggedUser())
        val duration = getDurationWithCheck(leaveRequest.fromDate, leaveRequest.toDate)

//        if (leaveRequest.type.isLimited()) {
//            val hoursLeft = leaveTypeLimitService.getHoursLeft(leaveRequest.user, leaveRequest.type)
//            if (hoursLeft < duration) {
//                throw LimitExceededException("Not enough hours remaining for leave '${leaveRequest.type.name}'")
//            }
//            leaveTypeLimitService.setHoursLeft(leaveRequest.user, leaveRequest.type, hoursLeft - duration)
//        }

        // if user has a supervisor create approvals
        val supervisor = leaveRequest.user.supervisor
        if (leaveRequest.type.approvalNeeded && supervisor != null) {
            leaveRequest.approvals = listOf(LeaveRequestApproval(leaveRequest, supervisor)) +
                    leaveRequest.user.approvers.map { approver -> LeaveRequestApproval(leaveRequest, approver) }
            leaveRequest.status = LeaveRequestStatus.PENDING
            // TODO: send emails to approvers + something to user
        } else {
            leaveRequest.status = LeaveRequestStatus.APPROVED
            // TODO: send confirmation email to user
        }

        return leaveRequestRepository.save(leaveRequest)
    }

    private fun getDurationWithCheck(start: LocalDateTime, end: LocalDateTime): Int {
        val (dayStart, dayEnd) = settingService.getWorkDayRange()
        val hours = Duration.between(start, end).toHours()

        if (hours < 1)
            throw WrongParamException("End must be at least 1 hour after start")

        if (start.hour < dayStart || start.hour > dayEnd - 1)
            throw WrongParamException("Start time must be between $dayStart:00 (inc) and ${dayEnd - 1}:00 (inc)")

        if (end.hour < dayStart + 1 || end.hour > dayEnd)
            throw WrongParamException("End time must be between ${dayStart + 1}:00 (inc) and $dayEnd:00 (inc)")

        return hours.toInt()
    }

//    private fun getRequestedHoursForYear(user: User, leaveType: LeaveType, year: Int): Int {
//        // TODO: filter holidays and weekends
//        val (dayStart, dayEnd) = settingService.getWorkDayRange()
//
//        val yearStart = LocalDateTime.of(year, 1, 1, dayStart, 0, 0)
//        val yearEnd = LocalDateTime.of(year, 12, 31, dayEnd, 0, 0)
//
//        leaveRequestRepository.findLeavesByYear(user, leaveType, year).map { request ->
//            maxOf(request.fromDate, yearStart).
//        }
//    }
//
//    private fun LeaveRequest.takeYear(year: Int) {
//
//    }
}