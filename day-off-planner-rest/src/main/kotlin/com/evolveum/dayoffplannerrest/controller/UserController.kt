package com.evolveum.dayoffplannerrest.controller

import com.evolveum.dayoffplannerrest.data.dto.response.UserDto
import com.evolveum.dayoffplannerrest.service.UserService
import com.evolveum.dayoffplannerrest.utils.toUserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping("/me")
    fun me(): UserDto {
        return userService.getLoggedUser().toUserDto()
    }
}