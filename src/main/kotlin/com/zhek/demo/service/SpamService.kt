package com.zhek.demo.service

import com.zhek.demo.model.dto.WordDto
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SpamService(
    private val kafkaService: KafkaService,
    @Value()
) {

    var send = false

    fun run() {
        send = true
        spam()
    }

    fun stop() {
        send = false
        spam()
    }

    @Scheduled(fixedDelay = 1000)
    fun spam() {
        while (send) {
            val word = RandomStringUtils.randomAlphanumeric(STRING_LENGTH)
            kafkaService.sendWord(WordDto(word))
        }
    }

    companion object {
        private const val STRING_LENGTH = 100
    }
}
