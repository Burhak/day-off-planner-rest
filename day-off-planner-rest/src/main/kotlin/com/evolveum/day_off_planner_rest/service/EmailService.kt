package com.evolveum.day_off_planner_rest.service

import kong.unirest.Unirest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Service
import java.util.concurrent.LinkedBlockingQueue
import javax.mail.Message
import javax.mail.internet.InternetAddress

@Service
class EmailService(private val mailSender: JavaMailSender) {

    @Value("\${mailgun.url}")
    private val url: String = ""

    @Value("\${mailgun.api_key}")
    private val apiKey: String = ""

    private val messageQueue = LinkedBlockingQueue<MessageInfo>()

    fun sendMessage(recipient: String, subject: String, messageText: String) {
        sendMailgunMessage(MessageInfo(recipient, subject, messageText))
    }

    private fun sendMimeMessage(messageInfo: MessageInfo) {
        val message = mailSender.createMimeMessage().apply {
            setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageInfo.recipient))
            setSubject(messageInfo.subject)
            setContent(messageInfo.messageText, "text/html; charset=utf-8")
        }

        mailSender.send(message)
    }

    private fun sendSimpleMessage(messageInfo: MessageInfo) {
        val message = SimpleMailMessage().apply {
            setTo(messageInfo.recipient)
            setSubject(messageInfo.subject)
            setText(messageInfo.messageText)
        }
        mailSender.send(message)
    }

    private fun sendMailgunMessage(messageInfo: MessageInfo) {
        Unirest.post("$url/messages")
                .basicAuth("api", apiKey)
                .queryString("from", "evolveum.mail.bot@gmail.com")
                .queryString("to", messageInfo.recipient)
                .queryString("subject", messageInfo.subject)
                .queryString("text", messageInfo.messageText)
                .asObjectAsync(MailgunResponse::class.java)
                .thenAccept { response ->
                    response.ifSuccess { logger.info("Successfully sent Mailgun message to {} with subject '{}'", messageInfo.recipient, messageInfo.subject) }
                    response.ifFailure { logger.error("Failed to send message using Mailgun. Status: {}, message: {}", response.status, response.body.message) }
                }
    }

    private data class MessageInfo(val recipient: String, val subject: String, val messageText: String)

    private class MailgunResponse(val message: String)

    private fun getSender() = InternetAddress.getLocalAddress((mailSender as JavaMailSenderImpl).session)

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}