package fwdays.order.web.api;

import fwdays.order.web.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
public class OrderClient implements OrderFacade {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String baseUrl;

    @Override
    //FIXME complete
    public List<OrderDTO> findAll() {
        return restTemplate.getForObject(baseUrl + "/orders", List.class);
    }

    @Override
    public OrderDTO findOne(int orderId) {
        return restTemplate.getForObject(baseUrl + "/orders/" + orderId, OrderDTO.class);
    }
}
