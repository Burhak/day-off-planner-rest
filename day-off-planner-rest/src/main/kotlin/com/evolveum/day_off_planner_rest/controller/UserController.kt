package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toCarryoverApiModel
import com.evolveum.day_off_planner_rest.assembler.toLimitApiModel
import com.evolveum.day_off_planner_rest.assembler.toUserApiModel
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
        private val limitService: LimitService
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

    override fun getLimit(leaveTypeId: UUID): ResponseEntity<LimitApiModel> {
        return ResponseEntity(limitService.getIndividualLimit(leaveTypeId)?.toLimitApiModel(), HttpStatus.OK)
    }

    override fun getCarryover(leaveTypeId: UUID, year: Int?): ResponseEntity<CarryoverApiModel> {
        return ResponseEntity(limitService.getCarryover(leaveTypeId, year)?.toCarryoverApiModel(), HttpStatus.OK)
    }
}