package com.evolveum.day_off_planner_rest.data.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "leave_type")
data class LeaveType(
        @Column(unique = true)
        var name: String = "",
        var approvalNeeded: Boolean = false,
        var limit: Int? = null,
        var carryover: Int? = null
) : Serializable {
    @Id
    @GeneratedValue
    var id: UUID = UUID.randomUUID()

    var deleted: Boolean = false

    fun isLimited() = limit != null
    fun supportsCarryover() = carryover != null
}