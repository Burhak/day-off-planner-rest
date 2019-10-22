package com.evolveum.day_off_planner_rest.data.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "employee")
data class User(
        var firstName: String = "",
        var lastName: String = "",
        @Column(unique = true)
        var email: String = "",
        var password: String = "",
        var admin: Boolean = false,
        @ManyToOne var supervisor: User? = null
) {
    @Id @GeneratedValue
    var id: UUID = UUID.randomUUID()

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