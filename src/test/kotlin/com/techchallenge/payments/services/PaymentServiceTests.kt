import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PaymentServiceTest {

    @Mock
    private lateinit var repository: PaymentRepository

    @InjectMocks
    private lateinit var service: PaymentService

    @Test
    fun `find should return payment when orderId exists`() {
        val orderId = "12345"
        val payment = Payment(orderId, amount = 100.0)

        `when`(repository.findByOrderId(orderId)).thenReturn(payment)

        val result = service.find(orderId)

        assertNotNull(result)
        assertEquals(payment, result)
        verify(repository, times(1)).findByOrderId(orderId)
    }

    @Test
    fun `save should persist payment`() {
        val payment = Payment(orderId = "12345", amount = 200.0)

        `when`(repository.save(payment)).thenReturn(payment)

        val result = service.save(payment)

        assertNotNull(result)
        assertEquals(payment, result)
        verify(repository, times(1)).save(payment)
    }

    @Test
    fun `update should modify payment based on orderId`() {
        val orderId = "12345"
        val payment = Payment(orderId, amount = 150.0)

        `when`(repository.updateByOrderId(orderId, payment)).thenReturn(payment)

        val result = service.update(orderId, payment)

        assertNotNull(result)
        assertEquals(payment, result)
        verify(repository, times(1)).updateByOrderId(orderId, payment)
    }
}
