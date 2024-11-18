package com.techchallenge.payments.repositories

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration


@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = [
    "amazon.dynamodb.endpoint=http://localhost:8000/",
    "amazon.aws.accesskey=test1",
    "amazon.aws.secretkey=test231"
])
internal class PaymentRepositoryIT(
    private val dynamoDBMapper: DynamoDBMapper,
    private val amazonDynamoDB: AmazonDynamoDB
) {

}
