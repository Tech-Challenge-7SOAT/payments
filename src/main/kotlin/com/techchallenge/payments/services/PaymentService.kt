package com.techchallenge.payments.services

import com.techchallenge.payments.entities.Payment
import com.techchallenge.payments.repositories.PaymentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PaymentService(private val repository: PaymentRepository) {
    fun find(orderId: String) = repository.findByOrderId(orderId)
    fun save(payment: Payment) = repository.save(payment)
    fun update(orderId: String, payment: Payment) = repository.updateByOrderId(orderId, payment)
}
