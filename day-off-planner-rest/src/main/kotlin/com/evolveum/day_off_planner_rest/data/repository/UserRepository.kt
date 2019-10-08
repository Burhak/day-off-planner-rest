package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    @Query(value = "select u from User u where u.email = :email and u.deleted = false")
    fun findByEmail(@Param("email") email: String): User?

    @Query(value = "select u from User u where u.deleted = false")
    fun findAllNotDeleted(): List<User>
}