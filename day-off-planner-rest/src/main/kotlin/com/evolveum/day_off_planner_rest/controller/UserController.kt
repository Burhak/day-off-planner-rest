package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toUserApiModel
import com.evolveum.day_off_planner_rest_api.api.UserApi
import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest.service.UserService
import com.evolveum.day_off_planner_rest_api.model.PasswordChangeApiModel
import com.evolveum.day_off_planner_rest_api.model.PasswordResetApiModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) : UserApi {

    override fun getAllUsers(): ResponseEntity<MutableList<UserApiModel>> {
        return ResponseEntity.ok(userService.getAllUsers().map { it.toUserApiModel() }.toMutableList())
    }

    override fun changePassword(body: PasswordChangeApiModel): ResponseEntity<Void> {
        userService.changePassword(body)
        return ResponseEntity.ok().build()
    }

    override fun resetPassword(body: PasswordResetApiModel): ResponseEntity<Void> {
        userService.resetPassword(body)
        return ResponseEntity.accepted().build()
    }

    override fun getUserById(id: Long?): ResponseEntity<UserApiModel> {
        return ResponseEntity.ok(userService.getUserById(id!!).toUserApiModel())
    }
}