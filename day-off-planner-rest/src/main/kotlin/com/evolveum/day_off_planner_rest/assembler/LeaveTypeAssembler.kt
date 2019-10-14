package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel
import org.springframework.stereotype.Component

@Component
class LeaveTypeAssembler {

    fun disassemble(leaveTypeApiModel: LeaveTypeApiModel): LeaveType = disassemble(LeaveType(), leaveTypeApiModel)

    fun disassemble(leaveType: LeaveType, leaveTypeApiModel: LeaveTypeApiModel): LeaveType = leaveType.apply {
        this.name = leaveTypeApiModel.name
        this.approvalNeeded = leaveTypeApiModel.isApprovalNeeded
        this.limited = leaveTypeApiModel.isLimited
        this.halfDayAllowed = leaveTypeApiModel.isHalfDayAllowed
    }
}

fun LeaveType.toLeaveTypeApiModel(): LeaveTypeApiModel = LeaveTypeApiModel()
        .id(id)
        .name(name)
        .approvalNeeded(approvalNeeded)
        .limited(limited)
        .halfDayAllowed(halfDayAllowed)
