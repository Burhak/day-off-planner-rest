package com.evolveum.day_off_planner_rest.util.date

import com.evolveum.day_off_planner_rest.data.entity.LeaveRequest
import com.evolveum.day_off_planner_rest.exception.WrongParamException
import java.time.LocalDateTime

data class DateRange(
        val from: LocalDateTime,
        val to: LocalDateTime,
        private val dayStart: Int,
        private val dayEnd: Int,
        private val validate: Boolean
): ValidRange(from, to, dayStart, dayEnd, validate) {

    constructor(from: LocalDateTime, to: LocalDateTime, dayStartEnd: DayStartEnd, validate: Boolean):
            this(from, to, dayStartEnd.start, dayStartEnd.end, validate)

    constructor(leaveRequest: LeaveRequest, dayStartEnd: DayStartEnd, validate: Boolean):
            this(leaveRequest.fromDate, leaveRequest.toDate, dayStartEnd, validate)

    fun with(from: LocalDateTime, to: LocalDateTime): DateRange = DateRange(from, to, dayStart, dayEnd, validate)

    fun takeYear(year: Int): Year {
        val yearStart = LocalDateTime.of(year, 1, 1, dayStart, 0, 0)
        val yearEnd = LocalDateTime.of(year, 12, 31, dayEnd, 0, 0)

        if (to.isBefore(yearStart) || from.isAfter(yearEnd))
            throw WrongParamException("Specified range does not contain year $year")

        return Year(year, maxOf(from, yearStart), minOf(to, yearEnd), dayStart, dayEnd, validate)
    }

    fun splitToYears(): List<Year> = (from.year..to.year).map { takeYear(it) }
}