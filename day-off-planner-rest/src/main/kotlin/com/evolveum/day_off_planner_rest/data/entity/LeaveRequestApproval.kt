package com.evolveum.day_off_planner_rest.data.entity

import javax.persistence.*

@Entity
@Table(name = "leave_request_approval")
data class LeaveRequestApproval(
        @ManyToOne(optional = false) var leaveRequest: LeaveRequest = LeaveRequest(),
        @ManyToOne(optional = false) var approver: User = User(),
        var approved: Boolean? = null
        ) {
    @Id
    @GeneratedValue
    var id: Long = 0L
}