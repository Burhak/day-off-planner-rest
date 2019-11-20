package com.evolveum.day_off_planner_rest.data.entity

import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "leave_request_message")
data class LeaveRequestMessage(
        @ManyToOne(optional = false) var leaveRequest: LeaveRequest = LeaveRequest(),
        @ManyToOne(optional = false) var approver: User = User(),
        var message: String = ""
) : Serializable {
    @Id
    @GeneratedValue
    var id: UUID = UUID.randomUUID()

    @CreationTimestamp
    var timestamp: LocalDateTime = LocalDateTime.now()
}