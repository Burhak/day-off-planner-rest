package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.UserAssembler
import com.evolveum.day_off_planner_rest.assembler.toUserDetails
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel
import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.data.repository.UserRepository
import com.evolveum.day_off_planner_rest.exception.NotFoundException
import com.evolveum.day_off_planner_rest.exception.WrongPasswordException
import com.evolveum.day_off_planner_rest_api.model.PasswordChangeApiModel
import com.evolveum.day_off_planner_rest_api.model.PasswordResetApiModel
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.annotation.PostConstruct

@Service
@Transactional
class UserService(
        private val passwordEncoder: BCryptPasswordEncoder,
        private val userRepository: UserRepository,
        private val userAssembler: UserAssembler,
        private val emailService: EmailService
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails = getUserByEmail(username).toUserDetails()

    fun getUserById(id: UUID): User = userRepository.findOneById(id) ?: throw NotFoundException("User with id $id was not found")

    fun getUserByEmail(email: String): User = userRepository.findOneByEmail(email) ?: throw NotFoundException("User with email $email was not found")

    fun getLoggedUser(): User = getUserByEmail(SecurityContextHolder.getContext().authentication.principal.toString())

    fun getAllUsers(): List<User> = userRepository.findAllNotDeleted()

    fun createUser(userCreateApiModel: UserCreateApiModel): User {
        val password = generateRandomPassword()
        val user = userRepository.save(userAssembler.disassemble(userCreateApiModel).apply { this.password = passwordEncoder.encode(password) })

        emailService.sendSimpleMessage(user.email, "Account created", "Welcome to Day Off Planner! Your password is: $password")

        return user
    }

    fun updateUser(userCreateApiModel: UserCreateApiModel, id: UUID): User {
        return userRepository.save(userAssembler.disassemble(getUserById(id), userCreateApiModel))
    }

    fun deleteUser(id: UUID) {
        userRepository.save(getUserById(id).apply { deleted = true })
    }

    fun changePassword(passwordChangeApiModel: PasswordChangeApiModel) {
        val user = getLoggedUser()
        if (!passwordEncoder.matches(passwordChangeApiModel.oldPassword, user.password)) {
            throw WrongPasswordException("Invalid password")
        }
        user.password = passwordEncoder.encode(passwordChangeApiModel.newPassword)
        userRepository.save(user)
    }

    fun resetPassword(passwordResetApiModel: PasswordResetApiModel) {
        val user = getUserByEmail(passwordResetApiModel.email)
        val password = generateRandomPassword()

        emailService.sendSimpleMessage(user.email, "Password reset", "Your new password is: $password")
        user.password = passwordEncoder.encode(password)
        userRepository.save(user)
    }

    private fun generateRandomPassword(length: Int = 8): String = STRING_CHARACTERS.shuffled().take(length).joinToString("")

    @PostConstruct
    fun createAdmin() {
        if (userRepository.count() == 0L) {
            userRepository.save(
                    User(
                            "admin",
                            "admin",
                            "admin@admin.com",
                            passwordEncoder.encode("password"),
                            true
                    )
            )
        }
    }

    companion object {
        private val STRING_CHARACTERS = ('0'..'9').toList() + ('a'..'z').toList() + ('A'..'Z').toList()
    }

}