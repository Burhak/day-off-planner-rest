package com.evolveum.day_off_planner_rest.data.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "carryover", uniqueConstraints = [UniqueConstraint(columnNames = ["leave_type_id", "user_id", "year"])])
data class Carryover(
        @ManyToOne(optional = false) var leaveType: LeaveType = LeaveType(),
        @ManyToOne(optional = false) var user: User = User(),
        var year: Int = 0,
        var hours: Int = 0
) : Serializable {
    @Id
    @GeneratedValue
    var id: UUID = UUID.randomUUID()
}