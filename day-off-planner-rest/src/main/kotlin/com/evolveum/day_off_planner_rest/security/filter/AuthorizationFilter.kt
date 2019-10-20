package com.evolveum.day_off_planner_rest.security.filter

import com.evolveum.day_off_planner_rest.exception.InvalidAccessTokenException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import com.evolveum.day_off_planner_rest.security.SecurityConstants
import com.evolveum.day_off_planner_rest.service.AccessTokenService
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class AuthorizationFilter(
        authManager: AuthenticationManager,
        private val accessTokenService: AccessTokenService
) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val header = req.getHeader(SecurityConstants.HEADER_AUTHORIZATION)

        if (header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                SecurityContextHolder.getContext().authentication = getAuthentication(header.replace(SecurityConstants.TOKEN_PREFIX, ""))
            } catch (e: InvalidAccessTokenException) {
                res.status = HttpStatus.UNAUTHORIZED.value()
                return
            }
        }

        chain.doFilter(req, res)
    }

    private fun getAuthentication(encryptedToken: String): UsernamePasswordAuthenticationToken? {
        // parse the token
        val token = try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.toByteArray())
                    .parseClaimsJws(encryptedToken)
        } catch (e: Exception) {
            throw InvalidAccessTokenException()
        }

        val storedToken = accessTokenService.getAccessToken(token.body.subject)
        if (storedToken == null || encryptedToken != storedToken.token || storedToken.isExpired()) {
            throw InvalidAccessTokenException()
        }

        val authorities = (token.body[SecurityConstants.AUTHORITIES_KEY] as List<*>).map { SimpleGrantedAuthority(it.toString()) }

        return UsernamePasswordAuthenticationToken(token.body.subject, null, authorities)
    }
}