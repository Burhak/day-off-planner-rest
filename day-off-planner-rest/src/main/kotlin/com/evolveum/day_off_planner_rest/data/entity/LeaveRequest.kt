package com.evolveum.day_off_planner_rest.data.entity

import com.evolveum.day_off_planner_rest.data.enums.LeaveRequestStatus
import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "leave_request")
data class LeaveRequest(
        var fromDate: LocalDateTime = LocalDateTime.now(),
        var toDate: LocalDateTime = LocalDateTime.now(),
        @ManyToOne(optional = false) var user: User = User(),
        @ManyToOne(optional = false) var type: LeaveType = LeaveType(),
        @Enumerated(EnumType.STRING) var status: LeaveRequestStatus = LeaveRequestStatus.PENDING
) : Serializable {
    @Id
    @GeneratedValue
    var id: UUID = UUID.randomUUID()

    @CreationTimestamp
    var timestamp: LocalDateTime = LocalDateTime.now()

    @OneToMany(mappedBy = "leaveRequest", cascade = [CascadeType.ALL])
    @JvmSuppressWildcards
    var approvals: List<LeaveRequestApproval> = mutableListOf()

    @OneToMany(mappedBy = "leaveRequest", cascade = [CascadeType.ALL])
    @JvmSuppressWildcards
    var messages: List<LeaveRequestMessage> = mutableListOf()
}