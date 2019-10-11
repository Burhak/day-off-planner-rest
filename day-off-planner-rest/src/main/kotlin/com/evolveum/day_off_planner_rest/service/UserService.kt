package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest_api.model.UserApiModel
import com.evolveum.day_off_planner_rest_api.model.UserCreateApiModel
import com.evolveum.day_off_planner_rest.data.entity.Role
import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.data.repository.RoleRepository
import com.evolveum.day_off_planner_rest.data.repository.UserRepository
import com.evolveum.day_off_planner_rest.exception.EmailAlreadyUsedException
import com.evolveum.day_off_planner_rest.exception.UserNotFoundException
import com.evolveum.day_off_planner_rest.utils.toUser
import com.evolveum.day_off_planner_rest.utils.toUserApiModel
import com.evolveum.day_off_planner_rest.utils.toUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Service
@Transactional
class UserService(
        private val passwordEncoder: BCryptPasswordEncoder,
        private val roleRepository: RoleRepository,
        private val userRepository: UserRepository,
        private val emailService: EmailService
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

        emailService.sendSimpleMessage(user.email, "Account created", "Welcome to Day Off Planner! Your password is: $password")

        return user.toUserApiModel()
    }

    fun getLoggedUser(): User {
        val user = SecurityContextHolder.getContext().authentication.principal.toString()
        return userRepository.findByEmail(user) ?: throw UserNotFoundException("User with email $user was not found")
    }

    fun getAllUsers(): List<UserApiModel> =
        userRepository.findAllNotDeleted().map { it.toUserApiModel() }


    private fun generateRandomPassword() = STRING_CHARACTERS.shuffled().take(8).joinToString("")

    @PostConstruct
    fun createAdmin() {
        if (!roleRepository.existsById(Role.ADMIN.name)) roleRepository.save(Role.ADMIN)
        if (!roleRepository.existsById(Role.USER.name)) roleRepository.save(Role.USER)

        if (userRepository.count() == 0L) {
            userRepository.save(User(
                    "admin",
                    "admin",
                    "admin@admin.com",
                    passwordEncoder.encode("password")
            ).apply { roles = listOf(Role.ADMIN) })
        }
    }

    companion object {
        private val STRING_CHARACTERS = ('0'..'9').toList() + ('a'..'z').toList() + ('A'..'Z').toList()
    }

}