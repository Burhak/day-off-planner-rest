package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.assembler.LeaveTypeAssembler
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.repository.LeaveTypeRepository
import com.evolveum.day_off_planner_rest.exception.NotFoundException
import com.evolveum.day_off_planner_rest_api.model.LeaveTypeCreateApiModel
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.annotation.PostConstruct

@Service
@Transactional
class LeaveTypeService(
        private val leaveTypeRepository: LeaveTypeRepository,
        private val leaveTypeAssembler: LeaveTypeAssembler
) {

    fun getLeaveTypeById(id: UUID): LeaveType = leaveTypeRepository.findOneById(id)
            ?: throw NotFoundException("Leave type with id $id was not found")

    fun getAllLeaveTypes(): List<LeaveType> = leaveTypeRepository.findAllNotDeleted()

    fun createLeaveType(leaveTypeCreateApiModel: LeaveTypeCreateApiModel): LeaveType {
        return leaveTypeRepository.save(leaveTypeAssembler.disassemble(leaveTypeCreateApiModel))
    }

    fun updateLeaveType(leaveTypeCreateApiModel: LeaveTypeCreateApiModel, id: UUID): LeaveType {
        return leaveTypeRepository.save(leaveTypeAssembler.disassemble(getLeaveTypeById(id), leaveTypeCreateApiModel))
    }

    fun deleteLeaveType(id: UUID) {
        leaveTypeRepository.save(getLeaveTypeById(id).apply { deleted = true })
    }

    @PostConstruct
    fun createInitialLeaveTypes() {
        if (getAllLeaveTypes().isEmpty()) {
            val vacation = LeaveType("Vacation", "#4285F4",true, 20 * 8, 5 * 8)
            val homeOffice = LeaveType("Home office", "#00C851", false, null, null)
            val doctorVisit = LeaveType("Doctor visit", "#FF4444", false, 5 * 8, null)

            leaveTypeRepository.saveAll(listOf(vacation, homeOffice, doctorVisit))
        }
    }
}