package fwdays.order.web;

import fwdays.order.domain.Order;
import fwdays.order.service.OrderService;
import fwdays.order.web.dto.CreateOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("{orderId}")
    public void completeOrder(@PathVariable int orderId) {
        orderService.complete(orderId);
    }

    @DeleteMapping("{orderId}")
    public void cancel(@PathVariable int orderId) {
        orderService.cancel(orderId);
    }

    @GetMapping
    public List<Order> findOrders() {
        return orderService.findOrders();
    }

    @GetMapping("{orderId}")
    public Order findOrderById(@PathVariable int orderId) {
        return orderService.findOrderById(orderId);
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.createOrder(createOrderDTO.bookId(), createOrderDTO.number(),
                createOrderDTO.customerId(), createOrderDTO.price());
    }
}
