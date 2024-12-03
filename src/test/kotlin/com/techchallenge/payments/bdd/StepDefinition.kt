package com.techchallenge.payments.bdd

import com.techchallenge.payments.entities.Payment
import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.math.BigDecimal

class StepDefinition {

    private lateinit var response: Response
    private lateinit var paymentResponse: Payment

    private val ENDPOINT_API: String = "http://localhost:8080/"

    @Dado("que eu tenha um pagamento registrado")
    fun `que eu tenha um pagamento registrado`() {
        val request = Payment(id = "1", orderId = "1", paymentType = "CREDIT", amount = BigDecimal.ONE)

        paymentResponse = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .post(ENDPOINT_API)
            .then()
            .extract()
            .`as`(Payment::class.java)
    }

    @Quando("eu finalizar a compra com cartão de crédito")
    fun `eu finalizar a compra com cartao de credito`() {
        val request = Payment(id = "1", orderId = "1", paymentType = "CREDIT", amount = BigDecimal.ONE)

        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .post(ENDPOINT_API)
    }

    @Entao("a compra é efetuada com sucesso")
    fun `a compra e efetuada com sucesso`() = response.then().statusCode(HttpStatus.OK.value())

    @Quando("eu finalizar a compra com boleto")
    fun `eu finalizar a compra com boleto`() {
        val request = Payment(id = "1", orderId = "1", paymentType = "BANK_SLIP", amount = BigDecimal.ONE)

        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .post(ENDPOINT_API)
    }

    @Entao("a compra é efetuada com sucesso usando boleto")
    fun `a compra e efetuada com sucesso usando boleto`() = response.then().statusCode(HttpStatus.OK.value())

    @Quando("eu finalizar a compra com débito")
    fun `eu finalizar a compra com debito`() {
        val request = Payment(id = "1", orderId = "1", paymentType = "DEBIT", amount = BigDecimal.ONE)

        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .post(ENDPOINT_API)
    }

    @Entao("a compra é efetuada com sucesso usando débito")
    fun `a compra e efetuada com sucesso usando debito`() = response.then().statusCode(HttpStatus.OK.value())

    @Quando("eu buscar o pagamento usando o id do pedido")
    fun `eu buscar o pagamento usando o id pedido`() {
        response = given()
            .`when`()
            .get(ENDPOINT_API + "1")
    }

    @Entao("o pagamento é encontrado")
    fun `o pagamento e encontrado`() = response.then().statusCode(HttpStatus.OK.value())

    @Quando("eu atualizar o pagamento usando o id do pedido")
    fun `eu atualizar o pagamento usando o id pedido`() {
        val request = Payment(id = "1", orderId = "1", paymentType = "CREDIT", amount = BigDecimal.ONE)

        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .put(ENDPOINT_API + "1")
    }

    @Entao("o pagamento é atualizado com sucesso")
    fun `o pagamento e atualizado`() = response.then().statusCode(HttpStatus.OK.value())
}
