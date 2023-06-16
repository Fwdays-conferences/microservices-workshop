package fwdays.order.web;

import fwdays.order.domain.Order;
import fwdays.order.service.OrderService;
import fwdays.order.web.dto.CreateOrderDTO;
import fwdays.order.web.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final ModelMapper modelMapper;

    @PostMapping("{orderId}")
    public void completeOrder(@PathVariable int orderId) {
        orderService.complete(orderId);
    }

    @DeleteMapping("{orderId}")
    public void cancel(@PathVariable int orderId) {
        orderService.cancel(orderId);
    }

    @GetMapping
    public List<OrderDTO> findOrders() {
        return orderService.findOrders()
                .stream().map(order -> modelMapper.map(order, OrderDTO.class)).toList();
    }

    @GetMapping("{orderId}")
    public OrderDTO findOrderById(@PathVariable int orderId) {
        return modelMapper.map(orderService.findOrderById(orderId), OrderDTO.class);
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.createOrder(createOrderDTO.bookId(), createOrderDTO.number(),
                createOrderDTO.customerId(), createOrderDTO.price());
    }
}
