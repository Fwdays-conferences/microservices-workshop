package fwdays.order.web.api;

import fwdays.order.web.dto.OrderDTO;

import java.util.List;

public interface OrderFacade {

    List<OrderDTO> findAll();

    OrderDTO findOne(int orderId);
}
