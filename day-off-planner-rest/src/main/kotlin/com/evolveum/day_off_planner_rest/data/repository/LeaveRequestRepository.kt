package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LeaveRequestRepository : CrudRepository<LeaveRequest, Long> {

}