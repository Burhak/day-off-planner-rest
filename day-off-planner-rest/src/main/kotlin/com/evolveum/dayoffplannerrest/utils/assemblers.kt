package com.evolveum.dayoffplannerrest.utils

import com.evolveum.dayoffplannerrest.data.dto.request.RegisterUserDto
import com.evolveum.dayoffplannerrest.data.dto.response.UserDto
import com.evolveum.dayoffplannerrest.data.entity.Role
import com.evolveum.dayoffplannerrest.data.entity.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

fun User.toUserDetails(): UserDetails = org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(password)
        .authorities(roles.map { SimpleGrantedAuthority(it.name) })
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build()

fun User.toUserDto() = UserDto(id, firstName, lastName, email, roles.map(Role::name))

fun RegisterUserDto.toUser(passwordEncoder: PasswordEncoder) =
        User(firstName, lastName, email, passwordEncoder.encode(password))
                .also { it.roles = if (isAdmin) listOf(Role.USER, Role.ADMIN) else listOf(Role.USER) }