package com.techchallenge.payments.controllers

import com.techchallenge.payments.entities.Payment
import com.techchallenge.payments.services.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PaymentController(private val service: PaymentService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(ex.message, HttpStatus.NOT_FOUND)

    @GetMapping("/{orderId}")
    fun find(@PathVariable("orderId") orderId: String) = service.find(orderId)

    @PostMapping("/")
    fun save(@RequestBody request: Payment) = service.save(request)

    @PutMapping("/{orderId}")
    fun update(@PathVariable("orderId") orderId: String, @RequestBody request: Payment) =
        service.update(orderId, request)
}
