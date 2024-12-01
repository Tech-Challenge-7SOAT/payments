package com.techchallenge.payments.services

import com.techchallenge.payments.entities.Payment
import com.techchallenge.payments.repositories.PaymentRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.math.BigDecimal

class PaymentServiceTest(
    private val service: PaymentService,
    private val repository: PaymentRepository
) {

    private val document = Payment(
        id = "1",
        orderId = "1",
        paymentType = "CREDIT_CARD",
        amount = BigDecimal.ONE
    )

    @BeforeEach
    fun setUp() = repository.deleteAll()

    @Test
    fun find() {
        val result = service.find(document.orderId)
        assertEquals(document, result)
    }

    @Test
    fun save() {
        val result = service.save(document)
        assertEquals(document, result)
    }

    @Test
    fun update() {
        val result = service.update(document.orderId, document.copy(amount = BigDecimal.TEN))
        assertEquals(result.amount, BigDecimal.TEN)
    }
}
