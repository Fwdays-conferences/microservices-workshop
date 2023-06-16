package fwdays.payment.service;

import fwdays.order.web.dto.OrderDTO;
import fwdays.payment.domain.Payment;
import fwdays.payment.persistence.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = "ORDER_SERVICE_URL=http://localhost:9000")
@AutoConfigureWireMock(port = 9000)
@AutoConfigureJsonTesters
class PaymentServiceTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    JacksonTester<OrderDTO> orderTester;

    @Test
    void pay_validOrder_success() throws IOException {
        int orderId = 100;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(1);
        orderDTO.setId(orderId);
        orderDTO.setAmount(200);
        stubFor(get(urlEqualTo("/orders/" + orderId))
                .willReturn(aResponse().withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(orderTester.write(orderDTO).getJson())));

        paymentService.pay(orderId);

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(1, payments.size());
        assertEquals(orderId, payments.get(0).getOrderId());

        verify(1, getRequestedFor(urlMatching("/orders/" + orderId)));
    }
}