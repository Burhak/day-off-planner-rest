package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.service.UserService
import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class UserAssembler(@Lazy private val userService: UserService) {

    fun disassemble(userCreateApiModel: UserCreateApiModel): User =
            disassemble(User(), userCreateApiModel)

    fun disassemble(user: User, userCreateApiModel: UserCreateApiModel): User =
            user.apply {
                this.firstName = userCreateApiModel.firstName
                this.lastName = userCreateApiModel.lastName
                this.email = userCreateApiModel.email
                this.admin = userCreateApiModel.isAdmin
                this.jobDescription = userCreateApiModel.jobDescription
                this.phone = userCreateApiModel.phone
                this.supervisor =
                        if (userCreateApiModel.supervisor == null) null
                        else userService.getUserById(userCreateApiModel.supervisor)
                this.approvers = userCreateApiModel.approvers.map { userService.getUserById(it) }
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

fun User.toUserApiModel(): UserApiModel = UserApiModel()
        .id(id)
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .admin(admin)
        .jobDescription(jobDescription)
        .phone(phone)
        .supervisor(supervisor?.id)
        .approvers(approvers.map { it.id })
