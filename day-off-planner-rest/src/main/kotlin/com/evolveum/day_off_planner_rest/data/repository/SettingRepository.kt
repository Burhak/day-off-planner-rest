package com.evolveum.day_off_planner_rest.data.repository

import com.evolveum.day_off_planner_rest.data.entity.Setting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SettingRepository : JpaRepository<Setting, String> {

    @Query(value = "select s from Setting s where s.key = :key")
    fun findOneByKey(@Param("key") key: String): Setting?
}