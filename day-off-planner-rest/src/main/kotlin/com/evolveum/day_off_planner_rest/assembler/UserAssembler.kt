package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.data.repository.UserRepository
import com.evolveum.day_off_planner_rest.exception.UserNotFoundException
import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel
import com.evolveum.day_off_planner_rest_api.model.UserLoginResponseApiModel
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class UserAssembler(private val userRepository: UserRepository) {

    fun disassemble(userCreateApiModel: UserCreateApiModel): User = disassemble(User(), userCreateApiModel)

    fun disassemble(user: User, userCreateApiModel: UserCreateApiModel): User = user.apply {
        this.firstName = userCreateApiModel.firstName
        this.lastName = userCreateApiModel.lastName
        this.email = userCreateApiModel.email
        this.admin = userCreateApiModel.isAdmin
        this.supervisor =
                if (userCreateApiModel.supervisor == null) null
                else (userRepository.findOneById(userCreateApiModel.supervisor)
                        ?: throw UserNotFoundException("User with id ${userCreateApiModel.supervisor} was not found"))
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
