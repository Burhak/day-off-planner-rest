package com.evolveum.day_off_planner_rest.security.filter

import com.evolveum.day_off_planner_rest.assembler.toUserLoginResponseApiModel
import com.evolveum.day_off_planner_rest_api.model.UserLoginApiModel
import com.evolveum.day_off_planner_rest.service.UserService
import com.evolveum.day_off_planner_rest.security.SecurityConstants
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

class AuthenticationFilter(
        private val authManager: AuthenticationManager,
        private val userService: UserService
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val user = ObjectMapper().readValue(req.inputStream, UserLoginApiModel::class.java)
        return authManager.authenticate(UsernamePasswordAuthenticationToken(user.email, user.password))
    }

    override fun successfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain, auth: Authentication) {
        val userDetails = auth.principal as UserDetails

        val token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.SECRET.toByteArray()), SignatureAlgorithm.HS512)
                .setSubject(userDetails.username)
                .setIssuedAt(Date())
                .setExpiration(Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .claim(SecurityConstants.AUTHORITIES_KEY, userDetails.authorities.map(GrantedAuthority::getAuthority))
                .compact()

        res.addHeader(SecurityConstants.HEADER_AUTHORIZATION, SecurityConstants.TOKEN_PREFIX + token)
        res.addHeader(SecurityConstants.HEADER_CONTENT_TYPE, SecurityConstants.CONTENT_TYPE)

        ObjectMapper().writeValue(res.writer, userService.getUserByEmail(userDetails.username).toUserLoginResponseApiModel(token))
    }
}