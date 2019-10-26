package com.evolveum.day_off_planner_rest.data.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "carryover")
@IdClass(Carryover.PK::class)
data class Carryover(
        @Id @ManyToOne(optional = false) var leaveType: LeaveType = LeaveType(),
        @Id @ManyToOne(optional = false) var user: User = User(),
        @Id var year: Int = 0,
        var hours: Int = 0
) : Serializable {

    data class PK(
            var leaveType: LeaveType = LeaveType(),
            var user: User = User(),
            var year: Int = 0
    ) : Serializable
}