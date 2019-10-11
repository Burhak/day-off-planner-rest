package com.evolveum.day_off_planner_rest.data.entity

import com.evolveum.day_off_planner_rest.data.enums.LeaveRequestStatus
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "leave_request")
data class LeaveRequest(
        @NotNull var fromDate: LocalDate = LocalDate.now(),
        @NotNull var toDate: LocalDate = LocalDate.now(),
        @NotNull @OneToOne var user: User = User(),
        @NotNull @OneToOne var type: LeaveType = LeaveType(),
        @NotNull @Enumerated(EnumType.STRING) var status: LeaveRequestStatus = LeaveRequestStatus.PENDING
) {
    @Id
    @GeneratedValue
    var id: Long = 0L
}