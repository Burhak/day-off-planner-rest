package com.evolveum.day_off_planner_rest.data.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "individual_limit")
@IdClass(IndividualLimit.PK::class)
data class IndividualLimit(
        @Id @ManyToOne(optional = false) var leaveType: LeaveType = LeaveType(),
        @Id @ManyToOne(optional = false) var user: User = User(),
        var limit: Int = 0
) : Serializable {

    data class PK(
            var leaveType: LeaveType = LeaveType(),
            var user: User = User()
    ) : Serializable
}