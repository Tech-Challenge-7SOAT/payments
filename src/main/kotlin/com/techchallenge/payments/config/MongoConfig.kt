package com.techchallenge.payments.config

import com.mongodb.client.MongoClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfig {
    @Bean
    fun mongoTemplate(
        mongoClient: MongoClient,
        @Value("\${spring.data.mongodb.uri}") database: String
    ): MongoTemplate {
        return MongoTemplate(mongoClient, database)
    }
}
