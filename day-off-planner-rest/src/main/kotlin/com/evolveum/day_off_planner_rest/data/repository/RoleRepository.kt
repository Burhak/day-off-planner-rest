package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, String>