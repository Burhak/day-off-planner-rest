package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.LeaveTypeAssembler
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.repository.LeaveTypeRepository
import com.evolveum.day_off_planner_rest.exception.AlreadyUsedException
import com.evolveum.day_off_planner_rest.exception.NotFoundException
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeApiModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LeaveTypeService(
        private val leaveTypeRepository: LeaveTypeRepository,
        private val leaveTypeAssembler: LeaveTypeAssembler
) {

    fun getLeaveTypeById(id: Long): LeaveType = leaveTypeRepository.findOneById(id) ?: throw NotFoundException("Leave type with id $id was not found")

    fun getAllLeaveTypes(): List<LeaveType> = leaveTypeRepository.findAllNotDeleted()

    fun createLeaveType(leaveTypeApiModel: LeaveTypeApiModel): LeaveType {
        checkName(leaveTypeApiModel.name)
        return leaveTypeRepository.save(leaveTypeAssembler.disassemble(leaveTypeApiModel))
    }

    fun updateLeaveType(leaveTypeApiModel: LeaveTypeApiModel, id: Long): LeaveType {
        val leaveType = getLeaveTypeById(id)
        checkName(leaveTypeApiModel.name, id)
        return leaveTypeRepository.save(leaveTypeAssembler.disassemble(leaveType, leaveTypeApiModel))
    }

    fun deleteLeaveType(id: Long) {
        leaveTypeRepository.save(getLeaveTypeById(id).apply { deleted = true })
    }

    private fun checkName(name: String, id: Long = -1L) {
        val leaveType = leaveTypeRepository.findOneByName(name)
        if (leaveType != null && leaveType.id != id) {
            throw AlreadyUsedException("Leave type with name $name already exists")
        }
    }
}