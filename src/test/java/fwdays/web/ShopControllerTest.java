package fwdays.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fwdays.book.web.dto.BookDTO;
import fwdays.order.domain.Order;
import fwdays.order.web.dto.CustomerDTO;
import fwdays.payment.domain.PaymentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
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

@SpringBootTest //(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ShopControllerTest {
	@Autowired
	MockMvc mockMvc;

	static final ObjectMapper MAPPER = new ObjectMapper();

	@BeforeEach
	void setup() {
		MAPPER.registerModule(new JavaTimeModule());
	}

	@Test
	void createAndCompleteOrder_success() throws Exception {
		// Create book
		BookDTO book = new BookDTO();
		book.setName("Microservice development");

		ResultActions actions = mockMvc.perform(
				post("/books").content(MAPPER.writeValueAsBytes(book)).contentType(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		MvcResult bookResult = actions.andReturn();
		String body = bookResult.getResponse().getContentAsString();
		book = MAPPER.readValue(body, BookDTO.class);

		// Create customer
		CustomerDTO customer = new CustomerDTO();
		customer.setEmail("customer@gmail.com");
		customer.setName("John Stones");
		PaymentProvider provider = new PaymentProvider();
		provider.setName("Paypal");
		provider.setCommission(10);
		//FIXME use valid provider
		customer.setProviderId(0);

		actions = mockMvc.perform(
				post("/customers").content(MAPPER.writeValueAsBytes(customer)).contentType(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		MvcResult customerResult = actions.andReturn();
		String responseBody = customerResult.getResponse().getContentAsString();
		customer = MAPPER.readValue(responseBody, CustomerDTO.class);

		// Create order
		actions = mockMvc.perform(post("/orders/{bookId}/1/{customerId}", book.getId(), customer.getId()));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

		MvcResult orderResult = actions.andReturn();
		String orderBody = orderResult.getResponse().getContentAsString();
		Order order = MAPPER.readValue(orderBody, Order.class);

		// Complete order
		actions = mockMvc.perform(post("/orders/{orderId}", order.getId()));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

		// Verify order status
		actions = mockMvc.perform(get("/orders/{orderId}", order.getId()));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.completed", equalTo(true)))
				.andExpect(jsonPath("$.payed", equalTo(true)))
				.andExpect(jsonPath("$.delivered", equalTo(true)))
				.andExpect(jsonPath("$.customer.id", equalTo(customer.getId())));

	}

}
