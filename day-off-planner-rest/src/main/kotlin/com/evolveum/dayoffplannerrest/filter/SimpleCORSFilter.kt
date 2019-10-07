package com.evolveum.dayoffplannerrest.filter

import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class SimpleCORSFilter : Filter {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse,
                          chain: FilterChain) {

        val response = res as HttpServletResponse
        val request = req as HttpServletRequest
        val originHeader = request.getHeader("Origin")

        //response.setHeader("Access-Control-Allow-Origin", "http://localhost:8888");
        response.setHeader("Access-Control-Allow-Origin", originHeader)
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type")
        LOG.debug("method: " + request.method + ", for URI: " + request.requestURI)

        if (!"OPTIONS".equals(request.method, ignoreCase = true)) {
            chain.doFilter(req, res)
        }
    }

    override fun init(filterConfig: FilterConfig?) {}

    override fun destroy() {}

    companion object {

        private val LOG = LoggerFactory.getLogger(SimpleCORSFilter::class.java)
    }
}