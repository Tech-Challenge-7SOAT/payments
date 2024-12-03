package com.techchallenge.payments.services

import com.techchallenge.payments.PaymentsApplication
import com.techchallenge.payments.entities.Payment
import com.techchallenge.payments.repositories.PaymentRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ContextConfiguration
import java.math.BigDecimal

@Profile("test")
@SpringBootTest(classes = [PaymentsApplication::class])
class PaymentServiceTest {

    @Autowired
    private lateinit var service: PaymentService

    @Autowired
    private lateinit var repository: PaymentRepository

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
