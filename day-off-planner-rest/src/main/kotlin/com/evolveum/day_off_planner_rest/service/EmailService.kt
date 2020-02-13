package com.evolveum.day_off_planner_rest.service

import com.evolveum.day_off_planner_rest.data.enums.EmailTemplate
import kong.unirest.Unirest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Service
class EmailService(private val templateEngine: SpringTemplateEngine) {

    @Value("\${mailgun.url}")
    private val url: String = ""

    @Value("\${mailgun.api_key}")
    private val apiKey: String = ""

    @Value("\${mailgun.email}")
    private val fromEmail: String = ""

    fun sendMessages(recipients: List<String>, subject: String, template: EmailTemplate, data: Map<String, Any>) {
        recipients.distinct().forEach { recipient -> sendMessage(recipient, subject, template, data) }
    }

    fun sendMessage(recipient: String, subject: String, template: EmailTemplate, data: Map<String, Any>) {
        sendMailGunMessage(Email(recipient, subject, template, data))
    }

    private fun sendMailGunMessage(email: Email) {
        Unirest.post("$url/messages")
                .basicAuth("api", apiKey)
                .queryString("from", fromEmail)
                .queryString("to", email.recipient)
                .queryString("subject", email.subject)
                .queryString("html", email.getMessage())
                .asObjectAsync(MailgunResponse::class.java)
                .thenAccept { response ->
                    response.ifSuccess { logger.info("Successfully sent Mailgun message to {} with subject '{}'", email.recipient, email.subject) }
                    response.ifFailure { logger.error("Failed to send message using Mailgun. Status: {}, message: {}", response.status, response.body.message) }
                }
    }

    private fun Email.getMessage(): String = templateEngine.process(template.templateName, prepareContext())

    private fun Email.prepareContext(): Context = Context().also { it.setVariables(data) }

    private data class Email(val recipient: String, val subject: String, val template: EmailTemplate, val data: Map<String, Any>)

    private data class MailgunResponse(val message: String)

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}