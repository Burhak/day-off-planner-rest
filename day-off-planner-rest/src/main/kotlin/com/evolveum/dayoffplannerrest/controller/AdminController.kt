package com.evolveum.dayoffplannerrest.controller

import com.evolveum.dayoffplannerrest.data.dto.request.RegisterUserDto
import com.evolveum.dayoffplannerrest.data.entity.User
import com.evolveum.dayoffplannerrest.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
class AdminController(private val userService: UserService) {

    @PostMapping("/createUser")
    fun createUser(@RequestBody registerUserDto: RegisterUserDto): ResponseEntity<HttpStatus> {
        userService.createUser(registerUserDto)
        return ResponseEntity.ok().build()
    }
}