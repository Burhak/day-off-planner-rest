package com.evolveum.dayoffplannerrest.data.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

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
}