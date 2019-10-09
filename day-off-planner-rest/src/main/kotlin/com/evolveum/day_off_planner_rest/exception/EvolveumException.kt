package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

abstract class EvolveumException : RuntimeException {

    constructor(): super()
    constructor(message: String): super(message)

    abstract fun getHttpStatusCode(): HttpStatus

    abstract fun getError(): String
}