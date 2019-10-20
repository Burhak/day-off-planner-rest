package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.AccessToken
import com.evolveum.day_off_planner_rest.service.UserService
import com.evolveum.day_off_planner_rest_api.model.UserLoginResponseApiModel
import org.springframework.stereotype.Component

@Component
class AccessTokenAssembler(private val userService: UserService) {

    fun assemble(accessToken: AccessToken): UserLoginResponseApiModel = UserLoginResponseApiModel()
            .token(accessToken.token)
            .tokenType(accessToken.tokenType)
            .expiresAt(accessToken.expiresAt)
            .user(userService.getUserByEmail(accessToken.email).toUserApiModel())
}