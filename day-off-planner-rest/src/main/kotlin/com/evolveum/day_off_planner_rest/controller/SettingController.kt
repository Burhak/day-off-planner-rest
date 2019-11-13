package com.evolveum.day_off_planner_rest.controller

import com.evolveum.day_off_planner_rest.assembler.toSettingApiModel
import com.evolveum.day_off_planner_rest.service.SettingService
import com.evolveum.day_off_planner_rest_api.api.SettingApi
import com.evolveum.day_off_planner_rest_api.model.SettingApiModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SettingController(private val settingService: SettingService) : SettingApi {

    override fun getAllSettings(): ResponseEntity<MutableList<SettingApiModel>> {
        return ResponseEntity(settingService.getAllSettings().map { it.toSettingApiModel() }.toMutableList(), HttpStatus.OK)
    }
}