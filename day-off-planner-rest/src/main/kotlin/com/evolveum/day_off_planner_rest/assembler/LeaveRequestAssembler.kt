package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.service.LeaveTypeService
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestCreateApiModel
import com.evolveum.day_off_planner_rest_api.model.RequestedHoursApiModel
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

fun Int.toRequestHoursApiModel(userId: UUID, leaveTypeId: UUID, year: Int?) = RequestedHoursApiModel()
        .user(userId)
        .leaveType(leaveTypeId)
        .year(year ?: LocalDate.now().year)
        .requestedHours(this)
