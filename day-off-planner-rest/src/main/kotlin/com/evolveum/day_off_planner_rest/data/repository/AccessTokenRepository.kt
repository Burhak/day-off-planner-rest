package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.AccessToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface AccessTokenRepository : JpaRepository<AccessToken, String> {

    @Query(value = "select at from AccessToken at where at.email = :email")
    fun findOneByEmail(@Param("email") email: String): AccessToken?

    @Modifying
    @Query(value = "delete from AccessToken at where current_timestamp > at.expiresAt")
    fun deleteExpired()
}