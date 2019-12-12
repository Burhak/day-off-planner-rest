package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import com.evolveum.day_off_planner_rest.data.entity.LeaveRequestApproval
import com.evolveum.day_off_planner_rest.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional
interface LeaveRequestApprovalRepository : JpaRepository<LeaveRequestApproval, UUID> {

    @Query(value = "select lra from LeaveRequestApproval lra where lra.approver = :approver and lra.leaveRequest = :leaveRequest")
    fun findOne(@Param("approver") approver: User, @Param("leaveRequest") leaveRequest: LeaveRequest): LeaveRequestApproval?
}