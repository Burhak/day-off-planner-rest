package com.evolveum.day_off_planner_rest.data.entity

import java.io.Serializable
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "holiday")
data class Holiday(
        var date: LocalDate = LocalDate.now(),
        var name: String = ""
) : Serializable {
    @Id
    @GeneratedValue
    var id: UUID = UUID.randomUUID()
}