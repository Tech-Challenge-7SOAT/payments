package com.techchallenge.payments.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import java.util.concurrent.TimeUnit

@Configuration
class MongoConfig {
    @Bean
    fun mongoClient(@Value("\${spring.data.mongodb.uri}") uri: String): MongoClient {
        val connectionString = ConnectionString(uri)
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .applyToConnectionPoolSettings {
                it.maxConnectionIdleTime(30, TimeUnit.SECONDS)
            }
            .build()
        return MongoClients.create(mongoClientSettings)
    }

    @Bean
    fun mongoTemplate(
        mongoClient: MongoClient,
        @Value("\${spring.data.mongodb.uri}") uri: String
    ): MongoTemplate {
        val parsedUri = ConnectionString(uri)
        return MongoTemplate(mongoClient, parsedUri.database ?: "payments")
    }
}
