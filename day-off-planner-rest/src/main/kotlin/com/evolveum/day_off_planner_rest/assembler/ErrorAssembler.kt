package com.evolveum.day_off_planner_rest.assembler

import com.evolveum.day_off_planner_rest.exception.EvolveumException
import com.evolveum.day_off_planner_rest_api.model.ErrorApiModel
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ErrorAssembler

fun EvolveumException.assemble(path: String): ErrorApiModel = ErrorApiModel()
        .timestamp(LocalDateTime.now())
        .status(getHttpStatusCode().value())
        .error(getHttpStatusCode().reasonPhrase)
        .message(message ?: getError())
        .path(path)