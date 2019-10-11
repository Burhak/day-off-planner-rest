package com.evolveum.day_off_planner_rest.data.converter

import java.sql.Date
import java.time.LocalDate
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateConverter : AttributeConverter<LocalDate, Date> {

    override fun convertToDatabaseColumn(localDate: LocalDate?): Date? {
        return if (localDate == null) null else Date.valueOf(localDate)
    }

    override fun convertToEntityAttribute(sqlDate: Date?): LocalDate? {
        return sqlDate?.toLocalDate()
    }
}
