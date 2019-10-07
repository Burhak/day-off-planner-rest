package com.evolveum.dayoffplannerrest.exception

import org.springframework.http.HttpStatus

abstract class EvolveumException : RuntimeException {

    constructor(): super()
    constructor(message: String): super(message)

    abstract fun getHttpStatusCode(): HttpStatus

    abstract fun getError(): String
}