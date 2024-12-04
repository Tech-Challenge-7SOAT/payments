package com.techchallenge.payments.controllers

import com.techchallenge.payments.entities.Payment
import com.techchallenge.payments.repositories.PaymentRepository
import com.techchallenge.payments.services.PaymentService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.kotlin.anyOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import java.math.BigDecimal


@AutoConfigureDataMongo
@WebMvcTest(PaymentController::class)
internal class PaymentControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var paymentService: PaymentService

    @Test
    fun find() {
        `when`(paymentService.find("1")).thenReturn(Payment(
            id = "1",
            orderId = "1",
            paymentType = "CREDIT_CARD",
            amount = BigDecimal.ONE
        ))

        mockMvc.get("/1")
            .andExpect { status { isOk() } }
    }

    @Test
    fun findThrowException() {
        `when`(paymentService.find("1")).thenThrow(NoSuchElementException("Payment not found"))

        mockMvc.get("/1")
            .andExpect { status { isNotFound() } }
    }

    @Test
    fun save() {
        `when`(paymentService.save(anyOrNull())).thenReturn(Payment(
            id = "1",
            orderId = "1",
            paymentType = "CREDIT_CARD",
            amount = BigDecimal.ONE
        ))

        mockMvc.post("/") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "id": "1",
                    "orderId": "1",
                    "paymentType": "CREDIT_CARD",
                    "amount": 1
                }
            """.trimIndent()
        }.andExpect { status { isOk() } }
    }

    @Test
    fun update() {
        `when`(paymentService.update(anyOrNull(), anyOrNull())).thenReturn(Payment(
            id = "1",
            orderId = "1",
            paymentType = "CREDIT_CARD",
            amount = BigDecimal.ONE
        ))

        mockMvc.put("/1") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "id": "1",
                    "orderId": "1",
                    "paymentType": "CREDIT_CARD",
                    "amount": 1
                }
            """.trimIndent()
        }.andExpect { status { isOk() } }
    }
}
