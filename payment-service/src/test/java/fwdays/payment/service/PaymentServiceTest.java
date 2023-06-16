package fwdays.payment.service;

import fwdays.payment.domain.Payment;
import fwdays.payment.persistence.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "ORDER_SERVICE_URL=http://localhost:9000")
class PaymentServiceTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @Test
    void pay_validOrder_success() {
        int orderId = 100;
        paymentService.pay(orderId);

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(1, payments.size());
        assertEquals(orderId, payments.get(0).getOrderId());
    }
}