package com.evolveum.day_off_planner_rest.data.entity

import com.evolveum.day_off_planner_rest.data.enums.SettingType
import com.evolveum.day_off_planner_rest.exception.InvalidSettingException
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "setting")
data class Setting(
        @Id var key: String = "",
        var description: String = "",
        var min: Int = -1,
        var max: Int = -1,
        var value: Int = -1
) : Serializable {
    constructor(type: SettingType): this(type.key, type.description, type.min, type.max)

    fun isSet(): Boolean = value >= 0
    fun hasMin(): Boolean = min > 0
    fun hasMax(): Boolean = max > 0

    fun getValueWithThrow(): Int = if (isSet()) value else throw InvalidSettingException("Setting $key is not set")
}