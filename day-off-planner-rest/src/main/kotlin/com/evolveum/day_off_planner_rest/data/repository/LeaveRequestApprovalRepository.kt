package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.LeaveRequestApproval
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LeaveRequestApprovalRepository : CrudRepository<LeaveRequestApproval, Long> {
}