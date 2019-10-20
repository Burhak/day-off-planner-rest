package com.evolveum.day_off_planner_rest.security.config

import com.evolveum.day_off_planner_rest.security.filter.AuthenticationFilter
import com.evolveum.day_off_planner_rest.security.filter.AuthorizationFilter
import com.evolveum.day_off_planner_rest.service.AccessTokenService
import com.evolveum.day_off_planner_rest.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.logout.LogoutFilter

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ServerSecurityConfig(
        private val userService: UserService,
        private val accessTokenService: AccessTokenService,
        private val passwordEncoder: BCryptPasswordEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/*").hasAnyAuthority("ADMIN")
                .antMatchers("/user/resetPassword").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(AuthorizationFilter(authenticationManager(), accessTokenService), LogoutFilter::class.java)
                .addFilterBefore(AuthenticationFilter(authenticationManager(), accessTokenService), AuthorizationFilter::class.java)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .addLogoutHandler { _, _, authentication -> accessTokenService.deleteAccessToken(authentication.name) }
                .logoutSuccessHandler { _, response, _ -> response.status = HttpStatus.OK.value() }
    }
}