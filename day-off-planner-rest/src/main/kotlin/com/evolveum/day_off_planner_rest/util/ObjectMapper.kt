package com.evolveum.day_off_planner_rest.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

object ObjectMapper : ObjectMapper() {

    init {
        this.findAndRegisterModules()
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}