package com.evolveum.dayoffplannerrest.exception

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletResponse


@ControllerAdvice
class EvolveumExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(EvolveumException::class)
    fun handleEvolveumException(exception: Exception, response: HttpServletResponse) {
        if (exception is EvolveumException) {
            response.sendError(exception.getHttpStatusCode().value(), exception.message)
        }
    }
}