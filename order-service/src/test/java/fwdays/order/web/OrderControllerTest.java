package fwdays.order.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import fwdays.order.domain.Order;
import fwdays.order.web.dto.CreateOrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    static final ObjectMapper MAPPER = new ObjectMapper();

    @BeforeEach
    void setup() {
        MAPPER.findAndRegisterModules();
    }

    @Test
    void createAndCompleteOrder_success() throws Exception {
        int customerId = 1;
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1, 1, customerId, 100);
        ResultActions actions = mockMvc.perform(post("/orders")
                .content(MAPPER.writeValueAsBytes(createOrderDTO))
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        MvcResult orderResult = actions.andReturn();
        String orderBody = orderResult.getResponse().getContentAsString();
        Order order = MAPPER.readValue(orderBody, Order.class);

        // Complete order
        actions = mockMvc.perform(post("/orders/{orderId}", order.getId()));
        actions.andExpect(status().isOk());

        // Verify order status
        actions = mockMvc.perform(get("/orders/{orderId}", order.getId()));
        actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.completed", equalTo(true)))
                .andExpect(jsonPath("$.payed", equalTo(true)))
                .andExpect(jsonPath("$.delivered", equalTo(true)))
                .andExpect(jsonPath("$.customer.id", equalTo(customerId)));

    }
}