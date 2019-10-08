package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class EmailAlreadyUsedException(message: String) : EvolveumException(message) {
    override fun getHttpStatusCode() = HttpStatus.CONFLICT

    override fun getError() = "EMAIL_ALREADY_USED"

}