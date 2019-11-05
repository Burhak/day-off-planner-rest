package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.data.repository.CarryoverRepository
import com.evolveum.day_off_planner_rest.data.repository.IndividualLimitRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LimitService(
        private val limitRepository: IndividualLimitRepository,
        private val carryoverRepository: CarryoverRepository
) {

    fun getUserLimit(user: User, leaveType: LeaveType, year: Int): Int {
        val carryover = carryoverRepository.findOne(user, leaveType, year)?.hours ?: 0
        val limit = limitRepository.findOne(user, leaveType)?.limit ?: leaveType.limit ?: Int.MAX_VALUE - carryover

        return limit + carryover
    }
}