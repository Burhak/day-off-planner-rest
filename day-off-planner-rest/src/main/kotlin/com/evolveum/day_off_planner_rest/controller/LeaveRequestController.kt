package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toLeaveRequestApiModel
import com.evolveum.day_off_planner_rest.assembler.toLeaveRequestWithApprovalsApiModel
import com.evolveum.day_off_planner_rest.data.enums.LeaveRequestStatus
import com.evolveum.day_off_planner_rest.exception.WrongParamException
import com.evolveum.day_off_planner_rest.service.LeaveRequestService
import com.evolveum.day_off_planner_rest_api.api.LeaveApi
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestAddMessageApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestCreateApiModel
import com.evolveum.day_off_planner_rest_api.model.LeaveRequestWithApprovalsApiModel
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.util.*

@RestController
class LeaveRequestController(private val leaveRequestService: LeaveRequestService) : LeaveApi {

    override fun getLeaveRequestById(id: UUID): ResponseEntity<LeaveRequestApiModel> {
        return ResponseEntity(leaveRequestService.getLeaveRequestById(id).toLeaveRequestApiModel(), HttpStatus.OK)
    }

    override fun getLeaveRequestByIdWithApprovals(id: UUID): ResponseEntity<LeaveRequestWithApprovalsApiModel> {
        return ResponseEntity(leaveRequestService.getLeaveRequestByIdWithApproverCheck(id).toLeaveRequestWithApprovalsApiModel(), HttpStatus.OK)
    }

    override fun createLeaveRequest(body: LeaveRequestCreateApiModel): ResponseEntity<LeaveRequestApiModel> {
        return ResponseEntity(leaveRequestService.createLeaveRequest(body).toLeaveRequestApiModel(), HttpStatus.CREATED)
    }

    override fun cancelLeaveRequest(id: UUID): ResponseEntity<LeaveRequestApiModel> {
        return ResponseEntity(leaveRequestService.cancelLeaveRequest(id).toLeaveRequestApiModel(), HttpStatus.OK)
    }

    override fun approveLeaveRequest(id: UUID, approve: Boolean?): ResponseEntity<LeaveRequestWithApprovalsApiModel> {
        if (approve == null) throw WrongParamException("Parameter 'approve' is not set")
        return ResponseEntity(leaveRequestService.approveLeaveRequest(id, approve).toLeaveRequestWithApprovalsApiModel(), HttpStatus.OK)
    }

    override fun addMessage(body: LeaveRequestAddMessageApiModel, id: UUID): ResponseEntity<LeaveRequestWithApprovalsApiModel> {
        return ResponseEntity(leaveRequestService.addMessage(body, id).toLeaveRequestWithApprovalsApiModel(), HttpStatus.OK)
    }

    override fun forceApproveLeaveRequest(id: UUID, approve: Boolean?): ResponseEntity<LeaveRequestWithApprovalsApiModel> {
        if (approve == null) throw WrongParamException("Parameter 'approve' is not set")
        return ResponseEntity(leaveRequestService.forceApproveLeaveRequest(id, approve).toLeaveRequestWithApprovalsApiModel(), HttpStatus.OK)
    }

    override fun filterLeaveRequests(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) from: LocalDate?,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) to: LocalDate?,
            status: MutableList<String>?,
            users: MutableList<UUID>?,
            leaveTypes: MutableList<UUID>?,
            approvers: MutableList<UUID>?
    ): ResponseEntity<MutableList<LeaveRequestApiModel>> {
        return ResponseEntity(leaveRequestService.filterLeaveRequests(
                from,
                to,
                status?.map { LeaveRequestStatus.valueOf(it) } ?: listOf(),
                users ?: listOf(),
                leaveTypes ?: listOf(),
                approvers ?: listOf()
        ).map { it.toLeaveRequestApiModel() }.toMutableList(), HttpStatus.OK)
    }
}