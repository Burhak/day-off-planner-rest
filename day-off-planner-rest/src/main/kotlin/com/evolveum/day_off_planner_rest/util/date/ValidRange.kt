package com.evolveum.day_off_planner_rest.util.date

import com.evolveum.day_off_planner_rest.exception.WrongParamException
import java.time.temporal.*

abstract class ValidRange(from: Temporal, to: Temporal, dayStart: Int, dayEnd: Int) {
    init {
        val fromHour = from[ChronoField.HOUR_OF_DAY]
        val toHour = to[ChronoField.HOUR_OF_DAY]

        if (from.until(to, ChronoUnit.HOURS) < 1)
            throw WrongParamException("Start time must be ONE hour before end time")

        if (fromHour < dayStart || fromHour > dayEnd - 1)
            throw WrongParamException("Start time must be between $dayStart:00 (inc) and ${dayEnd - 1}:00 (inc)")

        if (toHour < dayStart + 1 || toHour > dayEnd)
            throw WrongParamException("End time must be between ${dayStart + 1}:00 (inc) and $dayEnd:00 (inc)")
    }
}