package com.evolveum.day_off_planner_rest.data.entity

import javax.persistence.*

@Entity
@Table(name = "leave_type")
data class LeaveType(
        var name: String = "",
        var approvalNeeded: Boolean = false,
        var limited: Boolean = false,
        var halfDayAllowed: Boolean = false
) {
    @Id
    @GeneratedValue
    var id: Long = 0L

    var deleted: Boolean = false
}