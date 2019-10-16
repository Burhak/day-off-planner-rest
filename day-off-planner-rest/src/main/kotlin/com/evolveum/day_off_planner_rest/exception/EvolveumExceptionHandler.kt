package com.evolveum.day_off_planner_rest.exception

import org.hibernate.exception.ConstraintViolationException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class EvolveumExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(EvolveumException::class)
    fun handleEvolveumException(exception: Exception, response: HttpServletResponse) {
        if (exception is EvolveumException) {
            response.sendError(exception.getHttpStatusCode().value(), exception.message ?: exception.getError())
        }
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(exception: Exception, response: HttpServletResponse) {
        val alreadyUsedException = AlreadyUsedException("")
        response.sendError(alreadyUsedException.getHttpStatusCode().value(), alreadyUsedException.getError())
    }
}