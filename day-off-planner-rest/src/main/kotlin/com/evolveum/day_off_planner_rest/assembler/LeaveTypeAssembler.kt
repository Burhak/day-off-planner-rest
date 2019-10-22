package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeCreateApiModel
import org.springframework.stereotype.Component

@Component
class LeaveTypeAssembler {

    fun disassemble(leaveTypeCreateApiModel: LeaveTypeCreateApiModel): LeaveType = disassemble(LeaveType(), leaveTypeCreateApiModel)

    fun disassemble(leaveType: LeaveType, leaveTypeCreateApiModel: LeaveTypeCreateApiModel): LeaveType = leaveType.apply {
        this.name = leaveTypeCreateApiModel.name
        this.approvalNeeded = leaveTypeCreateApiModel.isApprovalNeeded
        this.limited = leaveTypeCreateApiModel.isLimited
    }
}

fun LeaveType.toLeaveTypeApiModel(): LeaveTypeApiModel = LeaveTypeApiModel()
        .id(id)
        .name(name)
        .approvalNeeded(approvalNeeded)
        .limited(limited)
