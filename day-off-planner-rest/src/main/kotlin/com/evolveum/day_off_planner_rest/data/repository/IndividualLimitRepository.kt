package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.IndividualLimit
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IndividualLimitRepository : JpaRepository<IndividualLimit, UUID> {

    @Query(value = "select il from IndividualLimit il where il.user = :user and il.leaveType = :leaveType")
    fun findOne(@Param("user") user: User, @Param("leaveType") leaveType: LeaveType): IndividualLimit?

    @Query(value = "select il from IndividualLimit il where il.user = :user")
    fun findAllByUser(@Param("user") user: User): List<IndividualLimit>
}