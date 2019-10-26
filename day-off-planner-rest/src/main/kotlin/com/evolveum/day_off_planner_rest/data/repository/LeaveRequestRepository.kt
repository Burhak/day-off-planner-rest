package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LeaveRequestRepository : JpaRepository<LeaveRequest, UUID> {

    @Query(value = """
        select lr from LeaveRequest lr
            where lr.user = :user
            and lr.type = :leaveType
            and lr.status in ('PENDING, APPROVED')
            and year(lr.fromDate) <= :year
            and year(lr.toDate) <= :year""")
    fun findLeavesByYear(@Param("user") user: User, @Param("leaveType") leaveType: LeaveType, @Param("year") year: Int): List<LeaveRequest>
}