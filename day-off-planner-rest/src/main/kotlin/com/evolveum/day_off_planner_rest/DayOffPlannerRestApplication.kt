package com.evolveum.day_off_planner_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableScheduling
@SpringBootApplication(scanBasePackages = ["com.evolveum.day_off_planner_rest"])
class DayOffPlannerRestApplication {
	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}

fun main(args: Array<String>) {
	runApplication<DayOffPlannerRestApplication>(*args)
}
