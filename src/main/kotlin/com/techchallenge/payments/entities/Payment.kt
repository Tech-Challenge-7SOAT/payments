package com.techchallenge.payments.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal


@Document
data class Payment(
    @Id
    val id: String,
    val orderId: String,
    val paymentType: String,
    val amount: BigDecimal
) {
}
