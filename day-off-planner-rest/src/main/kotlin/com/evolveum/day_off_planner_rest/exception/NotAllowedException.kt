package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class NotAllowedException(message: String) : EvolveumException(message) {
    override fun getHttpStatusCode() = HttpStatus.FORBIDDEN

    override fun getError() = "NOT_ALLOWED"
}