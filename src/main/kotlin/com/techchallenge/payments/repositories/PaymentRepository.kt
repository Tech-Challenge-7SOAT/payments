package com.techchallenge.payments.repositories

import com.techchallenge.payments.entities.Payment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface PaymentRepository : MongoRepository<Payment, String> {

    @Query("{ 'orderId' : ?0 }")
    fun findByOrderId(orderId: String): Payment?

    @Query("{ 'orderId' : ?0 }")
    fun updateByOrderId(orderId: String, payment: Payment): Payment
}
