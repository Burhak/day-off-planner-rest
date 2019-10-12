package com.evolveum.day_off_planner_rest.data.entity

import com.evolveum.day_off_planner_rest.data.enums.LeaveRequestStatus
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "leave_request")
data class LeaveRequest(
        var fromDate: LocalDate = LocalDate.now(),
        var toDate: LocalDate = LocalDate.now(),
        @ManyToOne(optional = false) var user: User = User(),
        @ManyToOne(optional = false) var type: LeaveType = LeaveType(),
        @Enumerated(EnumType.STRING) var status: LeaveRequestStatus = LeaveRequestStatus.PENDING
) {
    @Id
    @GeneratedValue
    var id: Long = 0L

    @CreationTimestamp
    var timestamp: LocalDateTime = LocalDateTime.now()

    @OneToMany(mappedBy = "leaveRequest", cascade = [CascadeType.ALL])
    @JvmSuppressWildcards
    var approvals: List<LeaveRequestApproval> = listOf()
}