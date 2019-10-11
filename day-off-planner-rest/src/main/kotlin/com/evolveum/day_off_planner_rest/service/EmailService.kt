package com.evolveum.day_off_planner_rest.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Service
import java.util.concurrent.LinkedBlockingQueue
import javax.mail.Message
import javax.mail.internet.InternetAddress

@Service
class EmailService(private val mailSender: JavaMailSender) {

    private val messageQueue = LinkedBlockingQueue<Message>()

    fun sendMessage(recipient: String, subject: String, messageText: String) {
        val message = mailSender.createMimeMessage().apply {
//            setFrom(sender)
            setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient))
            setSubject(subject)
            setContent(messageText, "text/html; charset=utf-8")
        }

        mailSender.send(message)
    }

    fun sendSimpleMessage(recipient: String, subject: String, messageText: String) {
        val message = SimpleMailMessage().apply {
//            setFrom(getSender().address)
            setTo(recipient)
            setSubject(subject)
            setText(messageText)
        }
        mailSender.send(message)
    }

    private fun getSender() = InternetAddress.getLocalAddress((mailSender as JavaMailSenderImpl).session)
}