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

    fun getAllIndividualLimits(userId: UUID): List<IndividualLimit> =
            limitRepository.findAllByUser(userService.getUserById(userId)).filter { it.leaveType.isLimited() }

    fun getIndividualLimit(userId: UUID, leaveTypeId: UUID): IndividualLimit? =
            getIndividualLimit(userService.getUserById(userId), leaveTypeService.getLeaveTypeById(leaveTypeId))

    fun getAllCarryovers(userId: UUID, year: Int?): List<Carryover> =
            carryoverRepository.findAllByUser(userService.getUserById(userId), year ?: LocalDate.now().year).filter { it.leaveType.supportsCarryover() }

    fun getCarryover(userId: UUID, leaveTypeId: UUID, year: Int?): Carryover? =
            getCarryover(userService.getUserById(userId), leaveTypeService.getLeaveTypeById(leaveTypeId), year ?: LocalDate.now().year)

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

    fun newCarryover(user: User, leaveType: LeaveType, requestedOnPreviousYear: Int, newYear: Int) {
        val maxCarryover = leaveType.carryover ?: return
        val limit = getUserLimit(user, leaveType, newYear - 1)

        val newCarryoverValue = maxOf(minOf(maxCarryover, limit - requestedOnPreviousYear), 0)
        carryoverRepository.save(Carryover(leaveType, user, newYear, newCarryoverValue))
    }

    private fun getIndividualLimit(user: User, leaveType: LeaveType): IndividualLimit? =
            limitRepository.findOne(user, leaveType.checkIfLimited())

    private fun getCarryover(user: User, leaveType: LeaveType, year: Int): Carryover? =
            carryoverRepository.findOne(user, leaveType.checkIfSupportsCarryover(), year)

    private fun LeaveType.checkIfLimited(): LeaveType =
            if (isLimited()) this else throw NotLimitedException("This leave type is not limited")

    private fun LeaveType.checkIfSupportsCarryover(): LeaveType =
            if (supportsCarryover()) this else throw NotLimitedException("This leave type does not support carryover")
}