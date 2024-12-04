package com.techchallenge.payments.services

import com.techchallenge.payments.entities.Payment
import com.techchallenge.payments.repositories.PaymentRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureDataMongo
internal class PaymentServiceTest {

    @Autowired
    private lateinit var service: PaymentService
    private val repository: PaymentRepository = mockk()

    private val document = Payment(
        id = "1",
        orderId = "1",
        paymentType = "CREDIT_CARD",
        amount = BigDecimal.ONE
    )

    @BeforeEach
    fun setUp() {
        service = PaymentService(repository)
    }

    @Test
    fun find() {
        every { repository.findByOrderId(document.orderId) } returns Payment(
            id = "1",
            orderId = "1",
            paymentType = "CREDIT_CARD",
            amount = BigDecimal.ONE
        )

        assertDoesNotThrow {
            val result = service.find(document.orderId)
            assertEquals(document, result)
        }
    }

    @Test
    fun save() {
        every { repository.save(any()) } returns document

        assertDoesNotThrow {
            val result = service.save(document)
            assertEquals(document, result)
        }
    }

    @Test
    fun update() {
        every { repository.updateByOrderId(any(), any()) } returns document

        assertDoesNotThrow {
            service.update(document.orderId, document.copy(amount = BigDecimal.TEN))
        }
    }
}
