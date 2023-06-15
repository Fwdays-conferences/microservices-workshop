package fwdays.service;

import fwdays.order.domain.Order;
import fwdays.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrder_validParams_orderCreated() {
        int bookId = 1;
        int customerId = 1;
        Order order = orderService.createOrder(bookId, 1, customerId);
    }

    @Test
    void deliverOrder_validOrder_orderDelivered() {
    }

}
