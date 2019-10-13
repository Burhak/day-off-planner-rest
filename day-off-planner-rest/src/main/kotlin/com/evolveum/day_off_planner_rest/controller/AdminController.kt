package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest_api.api.AdminApi
import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel
import com.evolveum.day_off_planner_rest.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController(private val userService: UserService) : AdminApi {

    override fun createUser(body: UserCreateApiModel): ResponseEntity<UserApiModel> {
        return ResponseEntity.ok(userService.createUser(body))
    }

    override fun updateUser(body: UserCreateApiModel, id: Long?): ResponseEntity<UserApiModel> {
        return ResponseEntity.ok(userService.updateUser(body, id!!))
    }

    override fun deleteUser(id: Long?): ResponseEntity<Void> {
        userService.deleteUser(id!!)
        return ResponseEntity.ok().build()
    }
}