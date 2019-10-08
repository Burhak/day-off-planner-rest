package com.evolveum.dayoffplannerrest.service

import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel
import com.evolveum.dayoffplannerrest.data.entity.Role
import com.evolveum.dayoffplannerrest.data.entity.User
import com.evolveum.dayoffplannerrest.data.repository.UserRepository
import com.evolveum.dayoffplannerrest.exception.EmailAlreadyUsedException
import com.evolveum.dayoffplannerrest.exception.UserNotFoundException
import com.evolveum.dayoffplannerrest.utils.toUser
import com.evolveum.dayoffplannerrest.utils.toUserApiModel
import com.evolveum.dayoffplannerrest.utils.toUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
        private val passwordEncoder: PasswordEncoder,
        private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String) = getUserByEmail(username).toUserDetails()

    fun getUserByEmail(email: String): User = userRepository.findByEmail(email)
            ?: throw UserNotFoundException("User with email $email was not found")

    fun createUser(userCreateApiModel: UserCreateApiModel): UserApiModel {
        if (userRepository.findByEmail(userCreateApiModel.email) != null) {
            throw EmailAlreadyUsedException("Email ${userCreateApiModel.email} is already used")
        }

        val password = generateRandomPassword()

        val user = userCreateApiModel.toUser(passwordEncoder, password)
        userRepository.save(user)
        return user.toUserApiModel()
    }

    fun getLoggedUser(): User {
        val user = SecurityContextHolder.getContext().authentication.principal.toString()
        return userRepository.findByEmail(user) ?: throw UserNotFoundException("User with email $user was not found")
    }

    fun getAllUsers(): List<UserApiModel> =
        userRepository.findAllNotDeleted().map { it.toUserApiModel() }


    private fun generateRandomPassword() = STRING_CHARACTERS.shuffled().take(12).joinToString("")

    companion object {
        private val STRING_CHARACTERS = ('0'..'z').toList()
    }

}