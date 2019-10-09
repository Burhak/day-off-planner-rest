package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class UserNotFoundException(message: String) : EvolveumException(message) {
    override fun getHttpStatusCode() = HttpStatus.NOT_FOUND

    override fun getError() = "USER_NOT_FOUND"
}