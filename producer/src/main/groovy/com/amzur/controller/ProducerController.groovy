package com.amzur.controller

import com.amzur.entity.User
import com.amzur.service.MessageProducer
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject

@Controller("/send")
class ProducerController {

    @Inject
    MessageProducer messageProducer


    private Map<Integer, User> users = [:]
    private int nextUserId = 1
    @Post
    String sendMessage(@Body User user) {
        int userId=nextUserId++
        users[userId]=user


        messageProducer.sendMessage(users)
        return "Message Sent: ${user}"
    }
}
