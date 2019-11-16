package com.evolveum.day_off_planner_rest.data.entity

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "access_token")
data class AccessToken(
        @Id var email: String = "",
        @Column(length = 510) var token: String = "",
        var expiresAt: LocalDateTime = LocalDateTime.now(),
        var tokenType: String = ""
) : Serializable {
    fun isExpired(): Boolean = expiresAt.isBefore(LocalDateTime.now())
}