package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.AccessTokenAssembler
import com.evolveum.day_off_planner_rest.data.entity.AccessToken
import com.evolveum.day_off_planner_rest.data.repository.AccessTokenRepository
import com.evolveum.day_off_planner_rest_api.model.UserLoginResponseApiModel
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AccessTokenService(
        private val accessTokenRepository: AccessTokenRepository,
        private val accessTokenAssembler: AccessTokenAssembler
) {

    fun getAccessToken(email: String): AccessToken? = accessTokenRepository.findOneByEmail(email)

    fun saveAccessToken(token: AccessToken): AccessToken = accessTokenRepository.save(token)

    fun assembleResponse(token: AccessToken): UserLoginResponseApiModel = accessTokenAssembler.assemble(token)

    fun deleteAccessToken(email: String) = accessTokenRepository.deleteById(email)

    // every 15 minutes
    @Scheduled(fixedDelay = 15 * 60 * 1000)
    fun deleteExpiredTokens() {
        logger.debug("Deleting expired tokens...")
        accessTokenRepository.deleteExpired()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}