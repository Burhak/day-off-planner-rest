package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.data.repository.UserRepository
import com.evolveum.day_off_planner_rest.exception.NotFoundException
import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest_api.model.UserLoginResponseApiModel
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class UserAssembler(private val userRepository: UserRepository) {

    fun disassemble(userApiModel: UserApiModel): User = disassemble(User(), userApiModel)

    fun disassemble(user: User, userApiModel: UserApiModel): User = user.apply {
        this.firstName = userApiModel.firstName
        this.lastName = userApiModel.lastName
        this.email = userApiModel.email
        this.admin = userApiModel.isAdmin
        this.supervisor =
                if (userApiModel.supervisor == null) null
                else (userRepository.findOneById(userApiModel.supervisor)
                        ?: throw NotFoundException("User with id ${userApiModel.supervisor} was not found"))
    }
}

fun User.toUserDetails(): UserDetails = org.springframework.security.core.userdetails.User
        .withUsername(email)
        .password(password)
        .authorities(SimpleGrantedAuthority(if (admin) "ADMIN" else "USER"))
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build()

fun User.toUserLoginResponseApiModel(token: String): UserLoginResponseApiModel = UserLoginResponseApiModel()
        .token(token)
        .user(this.toUserApiModel())

fun User.toUserApiModel(): UserApiModel = UserApiModel()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .admin(admin)
        .supervisor(supervisor?.id)
