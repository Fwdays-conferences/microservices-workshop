package fwdays.payment.config;

import fwdays.order.web.api.OrderClient;
import fwdays.order.web.api.OrderFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PaymentConfiguration {

    @Bean
    OrderFacade orderFacade(Environment env) {
        return new OrderClient(env.getRequiredProperty("ORDER_SERVICE_URL"));
    }
}
