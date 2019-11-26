package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.Carryover
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CarryoverRepository : JpaRepository<Carryover, UUID> {

    @Query(value = "select c from Carryover c where c.user = :user and c.leaveType = :leaveType and c.year = :year")
    fun findOne(@Param("user") user: User, @Param("leaveType") leaveType: LeaveType, @Param("year") year: Int): Carryover?

    @Query(value = "select c from Carryover c where c.user = :user and c.year = :year")
    fun findAllByUser(@Param("user") user: User, @Param("year") year: Int): List<Carryover>
}