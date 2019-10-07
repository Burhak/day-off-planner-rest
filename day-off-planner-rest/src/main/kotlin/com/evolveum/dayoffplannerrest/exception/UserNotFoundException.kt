package com.evolveum.dayoffplannerrest.exception

import org.springframework.http.HttpStatus

class UserNotFoundException(message: String) : EvolveumException(message) {
    override fun getHttpStatusCode() = HttpStatus.NOT_FOUND

    override fun getError() = "USER_NOT_FOUND"

}