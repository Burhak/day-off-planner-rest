package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    @Query(value = "select u from User u where u.id = :id and u.deleted = false")
    fun findOneById(@Param("id") id: UUID): User?

    @Query(value = "select u from User u where u.email = :email and u.deleted = false")
    fun findOneByEmail(@Param("email") email: String): User?

    @Query(value = "select u from User u where u.deleted = false")
    fun findAllNotDeleted(): List<User>

    @Query(value = "select case when count(u) > 0 then true else false end from User u where ((u.supervisor = :user) or (:user member of u.approvers))")
    fun isApprover(@Param("user") user: User): Boolean
}