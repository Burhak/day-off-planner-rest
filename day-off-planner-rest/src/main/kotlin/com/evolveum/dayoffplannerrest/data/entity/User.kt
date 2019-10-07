package com.evolveum.dayoffplannerrest.data.entity

import javax.persistence.*

@Entity
@Table(name = "oauth_user")
data class User(
        var firstName: String = "",
        var lastName: String = "",
        var email: String = "",
        var password: String = "",
        var deleted: Boolean = false
) {
    @Id @GeneratedValue
    var id: Long = 0

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "oauth_user_role",
            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "role", referencedColumnName = "name")])
    @JvmSuppressWildcards
    var roles: List<Role> = listOf()
}