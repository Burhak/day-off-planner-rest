package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toCarryoverApiModel
import com.evolveum.day_off_planner_rest.assembler.toLimitApiModel
import com.evolveum.day_off_planner_rest.assembler.toRequestHoursApiModel
import com.evolveum.day_off_planner_rest.assembler.toUserApiModel
import com.evolveum.day_off_planner_rest.service.LeaveRequestService
import com.evolveum.day_off_planner_rest.service.LimitService
import com.evolveum.day_off_planner_rest_api.api.UserApi
import com.evolveum.day_off_planner_rest.service.UserService
import com.evolveum.day_off_planner_rest_api.model.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class UserController(
        private val userService: UserService,
        private val limitService: LimitService,
        private val leaveRequestService: LeaveRequestService
) : UserApi {

    override fun getAllUsers(): ResponseEntity<MutableList<UserApiModel>> {
        return ResponseEntity(userService.getAllUsers().map { it.toUserApiModel() }.toMutableList(), HttpStatus.OK)
    }

    override fun changePassword(body: PasswordChangeApiModel): ResponseEntity<Void> {
        userService.changePassword(body)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun resetPassword(body: PasswordResetApiModel): ResponseEntity<Void> {
        userService.resetPassword(body)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

    override fun getUserById(id: UUID): ResponseEntity<UserApiModel> {
        return ResponseEntity(userService.getUserById(id).toUserApiModel(), HttpStatus.OK)
    }

    override fun getLoggedUser(): ResponseEntity<UserApiModel> {
        return ResponseEntity(userService.getLoggedUser().toUserApiModel(), HttpStatus.OK)
    }

    override fun getAllLimits(userId: UUID): ResponseEntity<MutableList<LimitApiModel>> {
        return ResponseEntity(limitService.getAllIndividualLimits(userId).map { it.toLimitApiModel() }.toMutableList(), HttpStatus.OK)
    }

    override fun getLimit(userId: UUID, leaveTypeId: UUID): ResponseEntity<LimitApiModel> {
        return ResponseEntity(limitService.getIndividualLimit(userId, leaveTypeId)?.toLimitApiModel(), HttpStatus.OK)
    }

    override fun getAllCarryovers(userId: UUID, year: Int?): ResponseEntity<MutableList<CarryoverApiModel>> {
        return ResponseEntity(limitService.getAllCarryovers(userId, year).map { it.toCarryoverApiModel() }.toMutableList(), HttpStatus.OK)
    }

    override fun getCarryover(userId: UUID, leaveTypeId: UUID, year: Int?): ResponseEntity<CarryoverApiModel> {
        return ResponseEntity(limitService.getCarryover(userId, leaveTypeId, year)?.toCarryoverApiModel(), HttpStatus.OK)
    }

    override fun getRequestedHours(userId: UUID, leaveTypeId: UUID, year: Int?): ResponseEntity<RequestedHoursApiModel> {
        return ResponseEntity(leaveRequestService.getRequestedHours(userId, leaveTypeId, year).toRequestHoursApiModel(userId, leaveTypeId, year), HttpStatus.OK)
    }

    override fun isApprover(id: UUID): ResponseEntity<Boolean> {
        return ResponseEntity(userService.isApprover(id), HttpStatus.OK)
    }
}