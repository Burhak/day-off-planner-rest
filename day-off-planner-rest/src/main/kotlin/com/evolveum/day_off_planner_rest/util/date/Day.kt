package com.evolveum.day_off_planner_rest.util.date

import java.time.*

data class Day(
        val day: LocalDate,
        val from: LocalTime,
        val to: LocalTime,
        private val dayStart: Int,
        private val dayEnd: Int
) : ValidRange(from, to, dayStart, dayEnd) {

    fun duration(): Int = Duration.between(from, to).toHours().toInt()

    fun with(from: LocalTime, to: LocalTime): Day = Day(day, from, to, dayStart, dayEnd)
}