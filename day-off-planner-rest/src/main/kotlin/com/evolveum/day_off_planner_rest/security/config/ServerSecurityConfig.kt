package com.evolveum.day_off_planner_rest.security.config

import com.evolveum.day_off_planner_rest.security.filter.AuthenticationFilter
import com.evolveum.day_off_planner_rest.security.filter.AuthorizationFilter
import com.evolveum.day_off_planner_rest.service.UserService
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ServerSecurityConfig(
        private val userService: UserService,
        private val passwordEncoder: BCryptPasswordEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/admin/*").hasAnyAuthority("ADMIN")
                .antMatchers("/user/resetPassword").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(AuthenticationFilter(authenticationManager(), userService))
                .addFilter(AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout().clearAuthentication(true)
    }
}