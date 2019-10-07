package com.evolveum.dayoffplannerrest.filter

import com.evolveum.dayoffplannerrest.data.dto.request.LoginUserDto
import com.evolveum.dayoffplannerrest.utils.SecurityConstants
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

class AuthenticationFilter(private val authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val user = ObjectMapper().readValue(req.inputStream, LoginUserDto::class.java)
        return authManager.authenticate(UsernamePasswordAuthenticationToken(user.username, user.password))
    }

    override fun successfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain, auth: Authentication) {
        val user = auth.principal as UserDetails

        val token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.SECRET.toByteArray()), SignatureAlgorithm.HS512)
                .setSubject(user.username)
                .setIssuedAt(Date())
                .setExpiration(Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .claim(SecurityConstants.AUTHORITIES_KEY, user.authorities.map(GrantedAuthority::getAuthority))
                .compact()

        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token)
    }
}