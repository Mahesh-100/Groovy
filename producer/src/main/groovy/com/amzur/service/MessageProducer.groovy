package com.amzur.service

import com.amzur.entity.User
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface MessageProducer {

    @Topic("demo-topic")
    def sendMessage(def user)
}