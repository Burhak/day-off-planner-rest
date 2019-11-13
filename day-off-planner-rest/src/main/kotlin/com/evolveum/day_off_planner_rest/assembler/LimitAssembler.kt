package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.Carryover
import com.evolveum.day_off_planner_rest.data.entity.IndividualLimit
import com.evolveum.day_off_planner_rest.data.entity.LeaveType
import com.evolveum.day_off_planner_rest.data.entity.User
import com.evolveum.day_off_planner_rest.exception.WrongParamException
import com.evolveum.day_off_planner_rest_api.model.CarryoverApiModel
import com.evolveum.day_off_planner_rest_api.model.LimitApiModel
import com.evolveum.day_off_planner_rest_api.model.LimitUpdateApiModel
import org.springframework.stereotype.Component

@Component
class LimitAssembler {

    fun disassemble(limit: IndividualLimit, limitUpdateApiModel: LimitUpdateApiModel): IndividualLimit =
            limit.apply {
                if (limitUpdateApiModel.limit < 0)
                    throw WrongParamException("Limit must be >= 0")

                this.limit = limitUpdateApiModel.limit
            }
}

fun IndividualLimit.toLimitApiModel(): LimitApiModel = LimitApiModel()
        .user(user.id)
        .leaveType(leaveType.id)
        .limit(limit)

fun Carryover.toCarryoverApiModel(): CarryoverApiModel = CarryoverApiModel()
        .user(user.id)
        .leaveType(leaveType.id)
        .year(year)
        .carryover(hours)
