package com.evolveum.day_off_planner_rest.util

import com.evolveum.day_off_planner_rest.assembler.assemble
import com.evolveum.day_off_planner_rest.exception.EvolveumException
import com.evolveum.day_off_planner_rest.security.SecurityConstants
import javax.servlet.http.HttpServletResponse

fun HttpServletResponse.sendResponse(statusCode: Int, response: Any) {
    status = statusCode
    addHeader(SecurityConstants.HEADER_CONTENT_TYPE, SecurityConstants.CONTENT_TYPE)
    ObjectMapper.writeValue(writer, response)
}

fun HttpServletResponse.sendResponse(exception: EvolveumException, path: String) {
    sendResponse(exception.getHttpStatusCode().value(), exception.assemble(path))
}