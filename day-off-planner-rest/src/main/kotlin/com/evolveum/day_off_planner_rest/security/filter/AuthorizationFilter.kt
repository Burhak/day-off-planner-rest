package com.evolveum.day_off_planner_rest.security.filter

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import com.evolveum.day_off_planner_rest.security.SecurityConstants
import io.jsonwebtoken.Jwts
import javax.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class AuthorizationFilter(authManager: AuthenticationManager) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val header = req.getHeader(SecurityConstants.HEADER_AUTHORIZATION)

        if (header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            SecurityContextHolder.getContext().authentication = getAuthentication(req)
        }

        chain.doFilter(req, res)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val header = request.getHeader(SecurityConstants.HEADER_AUTHORIZATION)

        // parse the token
        val token = Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET.toByteArray())
                .parseClaimsJws(header.replace(SecurityConstants.TOKEN_PREFIX, ""))

        val authorities = (token.body[SecurityConstants.AUTHORITIES_KEY] as List<*>)
                .map { SimpleGrantedAuthority(it.toString()) }

        return UsernamePasswordAuthenticationToken(token.body.subject, null, authorities)
    }
}