package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class InvalidCredentialsException : EvolveumException("Invalid email or password") {
    override fun getHttpStatusCode() = HttpStatus.UNAUTHORIZED

    override fun getError() = "INVALID_CREDENTIALS"
}