package com.evolveum.day_off_planner_rest.security.filter

import com.evolveum.day_off_planner_rest.data.entity.AccessToken
import com.evolveum.day_off_planner_rest.exception.InvalidCredentialsException
import com.evolveum.day_off_planner_rest_api.model.UserLoginApiModel
import com.evolveum.day_off_planner_rest.security.SecurityConstants
import com.evolveum.day_off_planner_rest.service.AccessTokenService
import com.evolveum.day_off_planner_rest.util.ObjectMapper
import com.evolveum.day_off_planner_rest.util.sendResponse
import com.evolveum.day_off_planner_rest.util.date.toDate
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.time.LocalDateTime

class AuthenticationFilter(
        private val authManager: AuthenticationManager,
        private val accessTokenService: AccessTokenService
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val user = ObjectMapper.readValue(req.inputStream, UserLoginApiModel::class.java)
        return authManager.authenticate(UsernamePasswordAuthenticationToken(user.email, user.password))
    }

    override fun successfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain, auth: Authentication) {
        val userDetails = auth.principal as UserDetails

        val issuedAt = LocalDateTime.now()
        val expiresAt = issuedAt.plusSeconds(SecurityConstants.EXPIRATION_TIME_SECONDS)

        val token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SecurityConstants.SECRET.toByteArray()), SignatureAlgorithm.HS512)
                .setSubject(userDetails.username)
                .setIssuedAt(issuedAt.toDate())
                .setExpiration(expiresAt.toDate())
                .claim(SecurityConstants.AUTHORITIES_KEY, userDetails.authorities.map(GrantedAuthority::getAuthority))
                .compact()

        val accessToken = accessTokenService.saveAccessToken(AccessToken(userDetails.username, token, expiresAt, SecurityConstants.TOKEN_TYPE))

        res.sendResponse(HttpStatus.OK.value(), accessTokenService.assembleResponse(accessToken))
    }

    override fun unsuccessfulAuthentication(req: HttpServletRequest, res: HttpServletResponse, exception: AuthenticationException) {
        res.sendResponse(InvalidCredentialsException(), req.servletPath)
    }
}