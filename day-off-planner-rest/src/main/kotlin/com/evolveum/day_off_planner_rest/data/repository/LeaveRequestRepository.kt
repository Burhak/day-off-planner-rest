package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Repository
@Transactional
interface LeaveRequestRepository : JpaRepository<LeaveRequest, UUID> {

    @Query(value = "select lr from LeaveRequest lr where lr.id = :id")
    fun findOneById(@Param("id") id: UUID): LeaveRequest?

    @Query(value = """
        select lr from LeaveRequest lr
            where lr.user = :user
            and lr.type = :leaveType
            and lr.status in ('PENDING', 'APPROVED')
            and year(lr.fromDate) <= :year
            and year(lr.toDate) >= :year""")
    fun findLeavesByYear(@Param("user") user: User, @Param("leaveType") leaveType: LeaveType, @Param("year") year: Int): List<LeaveRequest>

    @Query(value = """
        select case when (count(lr) > 0) then true else false end  
        from LeaveRequest lr
        where lr.user = :user
        and lr.status in ('PENDING', 'APPROVED')
        and not ((lr.fromDate >= :to and lr.toDate > :to) or (lr.fromDate < :from and lr.toDate <= :from))""")
    fun hasCollision(@Param("user") user: User, @Param("from") from: LocalDateTime, @Param("to") to: LocalDateTime): Boolean
}