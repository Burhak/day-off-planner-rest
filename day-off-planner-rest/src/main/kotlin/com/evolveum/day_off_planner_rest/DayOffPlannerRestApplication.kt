package com.evolveum.day_off_planner_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication(scanBasePackages = ["com.evolveum.day_off_planner_rest"])
class DayOffPlannerRestApplication {
	@Bean
	fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(11)
}

fun main(args: Array<String>) {
	runApplication<DayOffPlannerRestApplication>(*args)
}
