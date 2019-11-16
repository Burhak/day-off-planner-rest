package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class AlreadyUsedException(message: String) : EvolveumException(message) {
    override fun getHttpStatusCode() = HttpStatus.CONFLICT

    override fun getError() = "ALREADY_USED"
}