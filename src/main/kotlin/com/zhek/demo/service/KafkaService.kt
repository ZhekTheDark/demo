package com.zhek.demo.service

import com.zhek.demo.model.dto.WordDto
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service

@Service
class KafkaService(
    private val wordTemplate: KafkaTemplate<String, WordDto>
) : Logging {

    fun sendWord(word: WordDto) {
        doSend(null, word, wordTemplate)
    }

    private fun <T> doSend(key: String?, message: T, template: KafkaTemplate<String, T>) {
        val completableFuture = if (key == null) {
            template.sendDefault(message)
        } else {
            template.sendDefault(key, message)
        }
        completableFuture.whenComplete { t: SendResult<String?, T>, u: Throwable? ->
            if (u != null) {
                logger.error("Failed to send event")
            } else {
                val record: ProducerRecord<String?, *> = t.producerRecord
                logger.trace(
                    "Event send successfully: '${record.topic()}, ${
                        (record.value() as T).toString().take(500)
                    }'"
                )
            }
        }
    }
}
