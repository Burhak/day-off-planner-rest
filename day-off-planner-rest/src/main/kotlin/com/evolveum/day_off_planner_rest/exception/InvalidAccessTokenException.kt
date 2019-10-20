package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class InvalidAccessTokenException : EvolveumException("Provided access token is invalid") {
    override fun getHttpStatusCode() = HttpStatus.UNAUTHORIZED

    override fun getError() = "INVALID_ACCESS_TOKEN"
}