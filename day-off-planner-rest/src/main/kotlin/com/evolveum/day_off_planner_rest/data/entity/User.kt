package com.evolveum.day_off_planner_rest.data.entity

import javax.persistence.*

@Entity
@Table(name = "oauth_user")
data class User(
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var password: String = "",
        var admin: Boolean = false,
        @ManyToOne var supervisor: User? = null
) {
    @Id @GeneratedValue
    var id: Long = 0L

    var deleted: Boolean = false

    @OneToMany(mappedBy = "supervisor")
    @JvmSuppressWildcards
    var employees: List<User> = listOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "approver",
            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "approver_id", referencedColumnName = "id")])
    @JvmSuppressWildcards
    var approvers: List<User> = listOf()
}