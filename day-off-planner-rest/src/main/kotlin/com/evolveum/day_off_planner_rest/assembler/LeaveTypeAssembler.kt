package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeCreateApiModel
import org.springframework.stereotype.Component

@Component
class LeaveTypeAssembler {

    fun disassemble(leaveTypeCreateApiModel: LeaveTypeCreateApiModel): LeaveType =
            disassemble(LeaveType(), leaveTypeCreateApiModel)

    fun disassemble(leaveType: LeaveType, leaveTypeCreateApiModel: LeaveTypeCreateApiModel): LeaveType =
            leaveType.apply {
                this.name = leaveTypeCreateApiModel.name
                this.color = leaveTypeCreateApiModel.color
                this.approvalNeeded = leaveTypeCreateApiModel.isApprovalNeeded
                this.limit = leaveTypeCreateApiModel.limit
                this.carryover = if (limit != null) leaveTypeCreateApiModel.carryover else null
            }
}

fun LeaveType.toLeaveTypeApiModel(): LeaveTypeApiModel = LeaveTypeApiModel()
        .id(id)
        .name(name)
        .color(color)
        .approvalNeeded(approvalNeeded)
        .limit(limit)
        .carryover(carryover)
