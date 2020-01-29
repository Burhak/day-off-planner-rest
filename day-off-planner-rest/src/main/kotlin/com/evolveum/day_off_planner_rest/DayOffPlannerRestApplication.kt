package com.evolveum.day_off_planner_rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.templatemode.TemplateMode
import java.nio.charset.StandardCharsets


@EnableScheduling
@SpringBootApplication(scanBasePackages = ["com.evolveum.day_off_planner_rest"])
class DayOffPlannerRestApplication {

	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

	@Bean
	fun htmlTemplateResolver(context: ApplicationContext): SpringResourceTemplateResolver =
		SpringResourceTemplateResolver().apply {
			setApplicationContext(context)
			prefix = "classpath:/mail/"
			suffix = ".html"
			templateMode = TemplateMode.HTML
			characterEncoding = StandardCharsets.UTF_8.name()
			isCacheable = false
			checkExistence = true
		}

	@Bean
	fun templateEngine(context: ApplicationContext): SpringTemplateEngine =
		SpringTemplateEngine().apply {
			setTemplateResolver(htmlTemplateResolver(context))
			addDialect(Java8TimeDialect())
		}

}

fun main(args: Array<String>) {
	runApplication<DayOffPlannerRestApplication>(*args)
}
