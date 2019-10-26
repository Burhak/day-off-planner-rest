package com.evolveum.day_off_planner_rest.exception

import org.springframework.http.HttpStatus

class WrongParamException(message: String) : EvolveumException(message) {
    override fun getHttpStatusCode() = HttpStatus.BAD_REQUEST

    override fun getError() = "WRONG_PARAM"
}