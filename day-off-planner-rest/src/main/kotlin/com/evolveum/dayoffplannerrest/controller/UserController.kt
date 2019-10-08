package com.evolveum.dayoffplannerrest.controller

import com.evolveum.day_off_planner_rest_api.api.UserApi
import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.dayoffplannerrest.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) : UserApi {

    override fun getAllUsers(): ResponseEntity<MutableList<UserApiModel>> {
        return ResponseEntity.ok(userService.getAllUsers().toMutableList())
    }
}