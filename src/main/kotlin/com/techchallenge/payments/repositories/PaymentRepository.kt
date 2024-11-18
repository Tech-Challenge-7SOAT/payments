package com.techchallenge.payments.repositories

import com.techchallenge.payments.entities.Payment
import org.springframework.data.repository.CrudRepository

interface PaymentRepository : CrudRepository<Payment, String> {

    fun findByOrderId(orderId: String): Payment?

    fun existsByOrderId(orderId: String): Boolean
}
