package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.LeaveTypeAssembler
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.repository.LeaveTypeRepository
import com.evolveum.day_off_planner_rest.exception.AlreadyUsedException
import com.evolveum.day_off_planner_rest.exception.NotFoundException
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeCreateApiModel
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

    fun createLeaveType(leaveTypeCreateApiModel: LeaveTypeCreateApiModel): LeaveType {
        checkName(leaveTypeCreateApiModel.name)
        return leaveTypeRepository.save(leaveTypeAssembler.disassemble(leaveTypeCreateApiModel))
    }

    fun updateLeaveType(leaveTypeCreateApiModel: LeaveTypeCreateApiModel, id: Long): LeaveType {
        val leaveType = getLeaveTypeById(id)
        checkName(leaveTypeCreateApiModel.name, id)
        return leaveTypeRepository.save(leaveTypeAssembler.disassemble(leaveType, leaveTypeCreateApiModel))
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