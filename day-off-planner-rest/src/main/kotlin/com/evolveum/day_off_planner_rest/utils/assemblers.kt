package com.evolveum.day_off_planner_rest.utils

import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel
import com.evolveum.day_off_planner_rest_api.model.UserLoginResponseApiModel
import com.evolveum.day_off_planner_rest.data.entity.Role
import com.evolveum.day_off_planner_rest.data.entity.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun User.toUserDetails(): UserDetails = org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(password)
        .authorities(roles.map { SimpleGrantedAuthority(it.name) })
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build()

fun User.toUserLoginResponseApiModel(token: String) = UserLoginResponseApiModel().also { model ->
    model.token = token
    model.user = this.toUserApiModel()
}

fun User.toUserApiModel() = UserApiModel().also { model ->
    model.id = id
    model.firstName = firstName
    model.lastName = lastName
    model.email = email
    model.roles = roles.map { UserApiModel.RolesEnum.fromValue(it.name) }
}

fun UserCreateApiModel.toUser(passwordEncoder: BCryptPasswordEncoder, password: String) =
        User(firstName, lastName, email, passwordEncoder.encode(password))
                .also { it.roles = if (isAdmin) listOf(Role.USER, Role.ADMIN) else listOf(Role.USER) }