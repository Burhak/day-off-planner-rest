package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class UnauthorizedException : EvolveumException("Authentication is required") {
    override fun getHttpStatusCode() = HttpStatus.UNAUTHORIZED

    override fun getError() = "UNAUTHORIZED"
}