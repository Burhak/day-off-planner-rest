package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toLeaveTypeApiModel
import com.evolveum.day_off_planner_rest.assembler.toUserApiModel
import com.evolveum.day_off_planner_rest.service.LeaveTypeService
import com.evolveum.day_off_planner_rest_api.api.AdminApi
import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest.service.UserService
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController(
        private val userService: UserService,
        private val leaveTypeService: LeaveTypeService
) : AdminApi {

    override fun createUser(body: UserApiModel): ResponseEntity<UserApiModel> {
        return ResponseEntity(userService.createUser(body).toUserApiModel(), HttpStatus.CREATED)
    }

    override fun updateUser(body: UserApiModel, id: Long?): ResponseEntity<UserApiModel> {
        return ResponseEntity(userService.updateUser(body, id!!).toUserApiModel(), HttpStatus.OK)
    }

    override fun deleteUser(id: Long?): ResponseEntity<Void> {
        userService.deleteUser(id!!)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun createLeaveType(body: LeaveTypeApiModel): ResponseEntity<LeaveTypeApiModel> {
        return ResponseEntity(leaveTypeService.createLeaveType(body).toLeaveTypeApiModel(), HttpStatus.CREATED)
    }

    override fun updateLeaveType(body: LeaveTypeApiModel, id: Long?): ResponseEntity<LeaveTypeApiModel> {
        return ResponseEntity(leaveTypeService.updateLeaveType(body, id!!).toLeaveTypeApiModel(), HttpStatus.OK)
    }

    override fun deleteLeaveType(id: Long?): ResponseEntity<Void> {
        leaveTypeService.deleteLeaveType(id!!)
        return ResponseEntity(HttpStatus.OK)
    }
}