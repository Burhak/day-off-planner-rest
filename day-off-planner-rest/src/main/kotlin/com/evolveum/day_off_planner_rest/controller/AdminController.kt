package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toLeaveTypeApiModel
import com.evolveum.day_off_planner_rest.assembler.toLimitApiModel
import com.evolveum.day_off_planner_rest.assembler.toSettingApiModel
import com.evolveum.day_off_planner_rest.assembler.toUserApiModel
import com.evolveum.day_off_planner_rest.service.LeaveTypeService
import com.evolveum.day_off_planner_rest.service.LimitService
import com.evolveum.day_off_planner_rest.service.SettingService
import com.evolveum.day_off_planner_rest_api.api.AdminApi
import com.evolveum.day_off_planner_rest.service.UserService
import com.evolveum.day_off_planner_rest_api.model.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class AdminController(
        private val userService: UserService,
        private val leaveTypeService: LeaveTypeService,
        private val limitService: LimitService,
        private val settingService: SettingService
) : AdminApi {

    override fun createUser(body: UserCreateApiModel): ResponseEntity<UserApiModel> {
        return ResponseEntity(userService.createUser(body).toUserApiModel(), HttpStatus.CREATED)
    }

    override fun updateUser(body: UserCreateApiModel, id: UUID): ResponseEntity<UserApiModel> {
        return ResponseEntity(userService.updateUser(body, id).toUserApiModel(), HttpStatus.OK)
    }

    override fun deleteUser(id: UUID): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun updateLimit(body: LimitUpdateApiModel, userId: UUID, leaveTypeId: UUID): ResponseEntity<LimitApiModel> {
        return ResponseEntity(limitService.updateIndividualLimit(body, userId, leaveTypeId).toLimitApiModel(), HttpStatus.OK)
    }

    override fun deleteLimit(userId: UUID, leaveTypeId: UUID): ResponseEntity<Void> {
        limitService.deleteIndividualLimit(userId, leaveTypeId)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun updateSetting(body: SettingUpdateApiModel, key: String): ResponseEntity<SettingApiModel> {
        return ResponseEntity(settingService.updateSetting(body, key).toSettingApiModel(), HttpStatus.OK)
    }

    override fun createLeaveType(body: LeaveTypeCreateApiModel): ResponseEntity<LeaveTypeApiModel> {
        return ResponseEntity(leaveTypeService.createLeaveType(body).toLeaveTypeApiModel(), HttpStatus.CREATED)
    }

    override fun updateLeaveType(body: LeaveTypeCreateApiModel, id: UUID): ResponseEntity<LeaveTypeApiModel> {
        return ResponseEntity(leaveTypeService.updateLeaveType(body, id).toLeaveTypeApiModel(), HttpStatus.OK)
    }

    override fun deleteLeaveType(id: UUID): ResponseEntity<Void> {
        leaveTypeService.deleteLeaveType(id)
        return ResponseEntity(HttpStatus.OK)
    }
}