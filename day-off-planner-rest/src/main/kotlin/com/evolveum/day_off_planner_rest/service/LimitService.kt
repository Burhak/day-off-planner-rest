package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.LimitAssembler
import com.evolveum.day_off_planner_rest.data.entity.Carryover
import com.evolveum.day_off_planner_rest.data.entity.IndividualLimit
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.data.repository.CarryoverRepository
import com.evolveum.day_off_planner_rest.data.repository.IndividualLimitRepository
import com.evolveum.day_off_planner_rest.exception.NotFoundException
import com.evolveum.day_off_planner_rest.exception.NotLimitedException
import com.evolveum.day_off_planner_rest_api.model.LimitUpdateApiModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
@Transactional
class LimitService(
        private val limitRepository: IndividualLimitRepository,
        private val carryoverRepository: CarryoverRepository,
        private val userService: UserService,
        private val leaveTypeService: LeaveTypeService,
        private val limitAssembler: LimitAssembler
) {

    fun getIndividualLimit(leaveTypeId: UUID): IndividualLimit? =
            getIndividualLimit(userService.getLoggedUser(), leaveTypeService.getLeaveTypeById(leaveTypeId))

    fun getCarryover(leaveTypeId: UUID, year: Int?): Carryover? =
            getCarryover(userService.getLoggedUser(), leaveTypeService.getLeaveTypeById(leaveTypeId), year ?: LocalDate.now().year)

    fun getUserLimit(user: User, leaveType: LeaveType, year: Int): Int {
        val carryover = getCarryover(user, leaveType, year)?.hours ?: 0
        val limit = getIndividualLimit(user, leaveType)?.limit ?: leaveType.limit ?: Int.MAX_VALUE - carryover

        return limit + carryover
    }

    fun updateIndividualLimit(limitUpdateApiModel: LimitUpdateApiModel, userId: UUID, leaveTypeId: UUID): IndividualLimit {
        val user = userService.getUserById(userId)
        val leaveType = leaveTypeService.getLeaveTypeById(leaveTypeId)
        val limit = getIndividualLimit(user, leaveType) ?: IndividualLimit(leaveType, user)
        return limitRepository.save(limitAssembler.disassemble(limit, limitUpdateApiModel))
    }

    fun deleteIndividualLimit(userId: UUID, leaveTypeId: UUID) {
        limitRepository.delete(getIndividualLimit(userService.getUserById(userId), leaveTypeService.getLeaveTypeById(leaveTypeId))
                ?: throw NotFoundException("Individual limit not fount"))
    }

    private fun getIndividualLimit(user: User, leaveType: LeaveType): IndividualLimit? =
            limitRepository.findOne(user, leaveType.checkIfLimited())

    private fun getCarryover(user: User, leaveType: LeaveType, year: Int): Carryover? =
            carryoverRepository.findOne(user, leaveType.checkIfLimited(), year)

    private fun LeaveType.checkIfLimited(): LeaveType =
            if (isLimited()) this else throw NotLimitedException("This leave type is not limited")
}