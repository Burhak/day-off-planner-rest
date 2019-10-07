package com.evolveum.dayoffplannerrest.utils

import com.evolveum.dayoffplannerrest.data.dto.request.RegisterUserDto
import com.evolveum.dayoffplannerrest.data.dto.response.UserDto
import com.evolveum.dayoffplannerrest.data.entity.User
import org.springframework.security.core.userdetails.UserDetails

fun User.toUserDetails(): UserDetails = org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(password)
        .authorities(listOf())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build()

fun User.toUserDto() = UserDto(id, firstName, lastName, email)

fun RegisterUserDto.toUser() = User(firstName, lastName, email, password)