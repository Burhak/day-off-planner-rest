package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toLeaveRequestApiModel
import com.evolveum.day_off_planner_rest.assembler.toLeaveRequestApprovalApiModel
import com.evolveum.day_off_planner_rest.exception.WrongParamException
import com.evolveum.day_off_planner_rest.service.LeaveRequestService
import com.evolveum.day_off_planner_rest_api.api.LeaveApi
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApprovalApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestCreateApiModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class LeaveRequestController(private val leaveRequestService: LeaveRequestService) : LeaveApi {

    override fun createLeaveRequest(body: LeaveRequestCreateApiModel): ResponseEntity<LeaveRequestApiModel> {
        return ResponseEntity(leaveRequestService.createLeaveRequest(body).toLeaveRequestApiModel(), HttpStatus.CREATED)
    }

    override fun approveLeaveRequest(id: UUID, approve: Boolean?): ResponseEntity<LeaveRequestApprovalApiModel> {
        if (approve == null) throw WrongParamException("Parameter 'approve' is not set")
        return ResponseEntity(leaveRequestService.approve(id, approve).toLeaveRequestApprovalApiModel(), HttpStatus.OK)
    }
}