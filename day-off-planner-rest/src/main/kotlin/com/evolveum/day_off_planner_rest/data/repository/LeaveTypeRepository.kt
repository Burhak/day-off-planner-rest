package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LeaveTypeRepository : JpaRepository<LeaveType, Long> {

    @Query(value = "select lt from LeaveType lt where lt.id = :id and lt.deleted = false")
    fun findOneById(@Param("id") id: Long): LeaveType?

    @Query(value = "select lt from LeaveType lt where lt.name = :name and lt.deleted = false")
    fun findOneByName(@Param("name") name: String): LeaveType?

    @Query(value = "select lt from LeaveType lt where lt.deleted = false")
    fun findAllNotDeleted(): List<LeaveType>
}