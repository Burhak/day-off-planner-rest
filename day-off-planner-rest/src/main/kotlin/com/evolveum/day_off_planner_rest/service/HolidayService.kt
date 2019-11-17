package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.data.entity.Holiday
import com.evolveum.day_off_planner_rest.data.repository.HolidayRepository
import com.evolveum.day_off_planner_rest.util.date.toDate
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate


@Service
@Transactional
class HolidayService(private val holidayRepository: HolidayRepository) {

    @Value("\${holidays.calendar-id}")
    private val calendarId: String = ""

    fun isHoliday(day: LocalDate): Boolean {
        if (holidayRepository.findAllByYear(day.year).isEmpty()) {
            holidayRepository.saveAll(getHolidaysFromGoogle(day.year))
        }

        return holidayRepository.findOneByDay(day) != null
    }

    private fun getHolidaysFromGoogle(year: Int): List<Holiday> = getGoogleCalendar()
            .events()
            .list(calendarId)
            .setTimeMin(DateTime(LocalDate.of(year, 1, 1).atStartOfDay().toDate()))
            .setTimeMax(DateTime(LocalDate.of(year + 1, 1, 1).atStartOfDay().toDate()))
            .execute()
            .items
            .map { Holiday(LocalDate.parse(it.start.date.toString()), it.summary) }

    private fun getGoogleCalendar(): Calendar = Calendar
            .Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    HttpCredentialsAdapter(
                            GoogleCredentials
                                    .fromStream(this.javaClass.getResourceAsStream("/google_credentials.json"))
                                    .createScoped("https://www.googleapis.com/auth/calendar")
                                    .apply { refreshIfExpired() }))
            .setApplicationName("Evo Day Off Planner")
            .build()
}