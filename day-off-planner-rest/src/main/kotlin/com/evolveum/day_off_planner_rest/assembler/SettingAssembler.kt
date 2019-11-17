package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.data.entity.Setting
import com.evolveum.day_off_planner_rest.exception.WrongParamException
import com.evolveum.day_off_planner_rest_api.model.SettingApiModel
import com.evolveum.day_off_planner_rest_api.model.SettingUpdateApiModel
import org.springframework.stereotype.Component

@Component
class SettingAssembler {

    fun disassemble(setting: Setting, settingUpdateApiModel: SettingUpdateApiModel): Setting =
            setting.apply {
                if (hasMin() && settingUpdateApiModel.value < min)
                    throw WrongParamException("Setting value must be >= $min")

                if (hasMax() && settingUpdateApiModel.value > max)
                    throw WrongParamException("Setting value must be <= $max")

                this.value = settingUpdateApiModel.value
            }
}

fun Setting.toSettingApiModel(): SettingApiModel = SettingApiModel()
        .key(key)
        .description(description)
        .value(if (isSet()) value else null)
        .min(if (hasMin()) min else null)
        .max(if (hasMax()) max else null)