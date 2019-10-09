package com.evolveum.day_off_planner_rest.data.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "oauth_role")
data class Role(@Id var name: String) {

    companion object {
        val USER = Role("USER")
        val ADMIN = Role("ADMIN")
    }
}