package com.amzur.controller

import com.amzur.service.MessageProducer
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

@Controller("/send")
class ProducerController {

    @Inject
    MessageProducer messageProducer

    @Get("/{message}")
    String sendMessage(@QueryValue String message) {
        messageProducer.sendMessage(message)
        return "Message Sent: ${message}"
    }
}
