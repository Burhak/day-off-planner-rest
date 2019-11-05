package com.evolveum.day_off_planner_rest.util.date

import java.time.*

data class Year(
        val year: Int,
        val from: LocalDateTime,
        val to: LocalDateTime,
        private val dayStart: Int,
        private val dayEnd: Int
) : ValidRange(from, to, dayStart, dayEnd) {

    fun splitToDays(): List<Day> = from.toLocalDate().toEpochDay().rangeTo(to.toLocalDate().toEpochDay())
            .map { Day(LocalDate.ofEpochDay(it), LocalTime.of(dayStart, 0), LocalTime.of(dayEnd, 0), dayStart, dayEnd) }
            .toMutableList()
            .apply {
                this[0] = this[0].with(from.toLocalTime(), this[0].to)
                this[lastIndex] = this[lastIndex].with(this[lastIndex].from, to.toLocalTime()) }
            .toList()
}