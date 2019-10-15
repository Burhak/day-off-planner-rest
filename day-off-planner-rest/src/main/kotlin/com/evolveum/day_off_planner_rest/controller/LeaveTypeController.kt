package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toLeaveTypeApiModel
import com.evolveum.day_off_planner_rest.service.LeaveTypeService
import com.evolveum.day_off_planner_rest_api.api.LeaveTypeApi
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class LeaveTypeController(private val leaveTypeService: LeaveTypeService) : LeaveTypeApi {

    override fun getAllLeaveTypes(): ResponseEntity<MutableList<LeaveTypeApiModel>> {
        return ResponseEntity(leaveTypeService.getAllLeaveTypes().map { it.toLeaveTypeApiModel() }.toMutableList(), HttpStatus.OK)

    }

    override fun getLeaveTypeById(id: UUID): ResponseEntity<LeaveTypeApiModel> {
        return ResponseEntity(leaveTypeService.getLeaveTypeById(id).toLeaveTypeApiModel(), HttpStatus.OK)
    }
}