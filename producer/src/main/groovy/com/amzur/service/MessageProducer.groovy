package com.amzur.service

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface MessageProducer {

    @Topic("demo-topic")
    void sendMessage(String message)
}