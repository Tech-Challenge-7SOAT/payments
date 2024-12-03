<<<<<<< HEAD
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import kotlin.test.assertEquals
import java.math.BigDecimal

@SpringJUnitConfig
@SpringBootTest(classes = [PaymentsApplication::class])
class PaymentServiceTest {

    @MockBean
=======
package com.techchallenge.payments.services

import com.techchallenge.payments.PaymentsApplication
import com.techchallenge.payments.entities.Payment
import com.techchallenge.payments.repositories.PaymentRepository
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureDataMongo
internal class PaymentServiceTest {

    private lateinit var service: PaymentService
>>>>>>> f50d2eb (add BDD tests)
    private lateinit var repository: PaymentRepository

    private lateinit var service: PaymentService

    private val document = Payment(
        id = "1",
        orderId = "1",
        paymentType = "CREDIT_CARD",
        amount = BigDecimal.ONE
    )

    @BeforeEach
<<<<<<< HEAD
    private fun setUp() {
        // Inicializa o serviço com o repositório mockado
        service = PaymentService(repository)

        // Configurações padrão dos mocks
        `when`(repository.findByOrderId(document.orderId)).thenReturn(document)
        `when`(repository.save(document)).thenReturn(document)
        `when`(repository.updateByOrderId(document.orderId, document.copy(amount = BigDecimal.TEN)))
            .thenReturn(document.copy(amount = BigDecimal.TEN))
    }
=======
    fun setUp() {
        repository = mockk()
        service = PaymentService(repository)
    }

    @AfterEach
    fun tearDown() = repository.deleteAll()
>>>>>>> f50d2eb (add BDD tests)

    @Test
    fun find() {
        val result = service.find(document.orderId)
        assertEquals(document, result)
        verify(repository).findByOrderId(document.orderId) // Verifica se o método foi chamado
    }

    @Test
    fun save() {
        val result = service.save(document)
        assertEquals(document, result)
        verify(repository).save(document) // Verifica se o método foi chamado
    }

    @Test
    fun update() {
        val updatedDocument = document.copy(amount = BigDecimal.TEN)
        val result = service.update(document.orderId, updatedDocument)
        assertEquals(BigDecimal.TEN, result.amount)
        verify(repository).updateByOrderId(document.orderId, updatedDocument) // Verifica se o método foi chamado
    }
}


// package com.techchallenge.payments.services

// import com.techchallenge.payments.PaymentsApplication
// import com.techchallenge.payments.entities.Payment
// import com.techchallenge.payments.repositories.PaymentRepository
// import org.junit.jupiter.api.Test

// import org.junit.jupiter.api.Assertions.*
// import org.junit.jupiter.api.BeforeEach
// import org.springframework.beans.factory.annotation.Autowired
// import org.springframework.boot.test.context.SpringBootTest
// import org.springframework.context.annotation.Profile
// import org.springframework.test.context.ContextConfiguration
// import java.math.BigDecimal

// @Profile("test")
// @SpringBootTest(classes = [PaymentsApplication::class])
// class PaymentServiceTest {

//     @Autowired
//     private lateinit var service: PaymentService

//     @Autowired
//     private lateinit var repository: PaymentRepository

//     private val document = Payment(
//         id = "1",
//         orderId = "1",
//         paymentType = "CREDIT_CARD",
//         amount = BigDecimal.ONE
//     )

//     @BeforeEach
//     fun setUp() = repository.deleteAll()

//     @Test
//     fun find() {
//         val result = service.find(document.orderId)
//         assertEquals(document, result)
//     }

//     @Test
//     fun save() {
//         val result = service.save(document)
//         assertEquals(document, result)
//     }

//     @Test
//     fun update() {
//         val result = service.update(document.orderId, document.copy(amount = BigDecimal.TEN))
//         assertEquals(result.amount, BigDecimal.TEN)
//     }
// }
