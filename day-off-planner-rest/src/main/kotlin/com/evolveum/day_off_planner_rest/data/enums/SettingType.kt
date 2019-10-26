package com.evolveum.day_off_planner_rest.data.enums

enum class SettingType(
        val key: String,
        val description: String,
        val min: Int,
        val max: Int
) {
    WORKDAY_START("WORKDAY_START", "General start of a workday - X:00, e.g. 9", 0, 11),
    WORKDAY_LENGTH("WORKDAY_LENGTH", "General length of a workday in hours - e.g. 8", 4, 12)
}