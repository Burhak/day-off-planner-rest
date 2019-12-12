package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.Holiday
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import java.time.LocalDate
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional


@Repository
@Transactional
interface HolidayRepository : JpaRepository<Holiday, UUID> {

    @Query(value = "select h from Holiday h where h.date = :day")
    fun findOneByDay(@Param("day") day: LocalDate): Holiday?

    @Query(value = "select h from Holiday h where year(h.date) = :year")
    fun findAllByYear(@Param("year") year: Int): List<Holiday>

}