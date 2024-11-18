package com.techchallenge.payments.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DynamoConfig {

    @Value("\${amazon.dynamodb.endpoint}")
    private val amazonDynamoDBEndpoint: String? = null

    @Value("\${amazon.aws.accesskey}")
    private val amazonAWSAccessKey: String? = null

    @Value("\${amazon.aws.secretkey}")
    private val amazonAWSSecretKey: String? = null

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        val amazonDynamoDB
            : AmazonDynamoDB = AmazonDynamoDBClient(amazonAWSCredentials())

        amazonDynamoDBEndpoint?.let {
            amazonDynamoDB.setEndpoint(it)
        }

        return amazonDynamoDB
    }

    @Bean
    fun amazonAWSCredentials(): AWSCredentials {
        return BasicAWSCredentials(
            amazonAWSAccessKey, amazonAWSSecretKey
        )
    }
}
