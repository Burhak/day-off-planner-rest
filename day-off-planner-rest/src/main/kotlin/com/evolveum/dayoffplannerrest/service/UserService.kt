package com.evolveum.dayoffplannerrest.service

import com.evolveum.dayoffplannerrest.data.dto.request.RegisterUserDto
import com.evolveum.dayoffplannerrest.data.entity.Role
import com.evolveum.dayoffplannerrest.data.entity.User
import com.evolveum.dayoffplannerrest.data.repository.UserRepository
import com.evolveum.dayoffplannerrest.exception.EmailAlreadyUsedException
import com.evolveum.dayoffplannerrest.exception.UserNotFoundException
import com.evolveum.dayoffplannerrest.utils.toUser
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

    override fun loadUserByUsername(username: String) = userRepository.findByEmail(username)?.toUserDetails()
                ?: throw UserNotFoundException("User with email $username was not found")

    fun createUser(registerUserDto: RegisterUserDto) {
        if (userRepository.findByEmail(registerUserDto.email) != null) {
            throw EmailAlreadyUsedException("Email ${registerUserDto.email} is already used")
        }

        val user = registerUserDto.toUser(passwordEncoder)
        userRepository.save(user)
    }

    fun getLoggedUser(): User {
        val user = SecurityContextHolder.getContext().authentication.principal.toString()
        return userRepository.findByEmail(user) ?: throw UserNotFoundException("User with email $user was not found")
    }

    private fun generateRandomPassword() = STRING_CHARACTERS.shuffled().take(12).joinToString("")

    companion object {
        private val STRING_CHARACTERS = ('0'..'z').toList()
    }

}