package com.amzur.service


import com.amzur.entity.User
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@KafkaListener(groupId = "example-consumer")
class MessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class)

    @Topic("demo-topic")
    void receiveMessage(def user) {
        LOG.info("Received Message: ${user}")
        
    }
}

