package com.evolveum.day_off_planner_rest.data.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "oauth_user")
data class User(
        @NotNull var firstName: String = "",
        @NotNull var lastName: String = "",
        @NotNull var email: String = "",
        @NotNull var password: String = "",
        var admin: Boolean = false,
        var deleted: Boolean = false
) {
    @Id @GeneratedValue
    var id: Long = 0L

    @OneToOne
    var supervisor: User? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "approver",
            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "approver_id", referencedColumnName = "id")])
    @JvmSuppressWildcards
    var approvers: List<User> = listOf()
}