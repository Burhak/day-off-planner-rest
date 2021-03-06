package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import com.evolveum.day_off_planner_rest.data.entity.LeaveRequestApproval
import com.evolveum.day_off_planner_rest.data.entity.LeaveRequestMessage
import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.service.LeaveTypeService
import com.evolveum.day_off_planner_rest_api.model.*
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class LeaveRequestAssembler(private val leaveTypeService: LeaveTypeService) {

    fun disassemble(leaveRequestCreateApiModel: LeaveRequestCreateApiModel, user: User): LeaveRequest =
            disassemble(LeaveRequest(user = user), leaveRequestCreateApiModel)

    fun disassemble(leaveRequest: LeaveRequest, leaveRequestCreateApiModel: LeaveRequestCreateApiModel): LeaveRequest =
            leaveRequest.apply {
                this.fromDate = leaveRequestCreateApiModel.fromDate.truncatedTo(ChronoUnit.HOURS)
                this.toDate = leaveRequestCreateApiModel.toDate.truncatedTo(ChronoUnit.HOURS)
                this.type = leaveTypeService.getLeaveTypeById(leaveRequestCreateApiModel.leaveType)
            }
}

fun LeaveRequest.toLeaveRequestApiModel(): LeaveRequestApiModel = LeaveRequestApiModel()
        .id(id)
        .user(user.id)
        .leaveType(type.id)
        .status(LeaveRequestApiModel.StatusEnum.fromValue(status.name))
        .fromDate(fromDate)
        .toDate(toDate)

fun LeaveRequest.toLeaveRequestWithApprovalsApiModel(): LeaveRequestWithApprovalsApiModel = LeaveRequestWithApprovalsApiModel()
        .leaveRequest(this.toLeaveRequestApiModel())
        .approvals(approvals.map { it.toLeaveRequestApprovalApiModel() })
        .messages(messages.sortedByDescending { it.timestamp }.map { it.toLeaveRequestMessageApiModel() })

fun Int.toRequestHoursApiModel(userId: UUID, leaveTypeId: UUID, year: Int?): RequestedHoursApiModel = RequestedHoursApiModel()
        .user(userId)
        .leaveType(leaveTypeId)
        .year(year ?: LocalDate.now().year)
        .requestedHours(this)

fun LeaveRequestApproval.toLeaveRequestApprovalApiModel(): LeaveRequestApprovalApiModel = LeaveRequestApprovalApiModel()
        .approver(approver.id)
        .leaveRequest(leaveRequest.id)
        .approved(approved)

fun LeaveRequestMessage.toLeaveRequestMessageApiModel(): LeaveRequestMessageApiModel = LeaveRequestMessageApiModel()
        .approver(approver.id)
        .leaveRequest(leaveRequest.id)
        .message(message)
        .timestamp(timestamp)
