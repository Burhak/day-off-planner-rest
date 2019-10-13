package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class NotFoundException(message: String) : EvolveumException(message) {
    override fun getHttpStatusCode() = HttpStatus.NOT_FOUND

    override fun getError() = "NOT_FOUND"
}