package fwdays.payment;

import fwdays.payment.domain.Customer;
import fwdays.payment.domain.PaymentProvider;
import fwdays.payment.persistence.CustomerRepository;
import fwdays.payment.persistence.PaymentProviderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class PaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    ApplicationListener<ContextRefreshedEvent> applicationListener(CustomerRepository customerRepository,
                                                                   PaymentProviderRepository paymentProviderRepository) {
        return (event) -> {
            PaymentProvider provider = new PaymentProvider();
            provider.setName("Paypal");
            provider.setCommission(1);
            paymentProviderRepository.save(provider);

            Customer customer = new Customer();
            customer.setId(1);
            customer.setCardNumber("123");
            customer.setBalance(10000);
            customer.setProvider(provider);
            customerRepository.save(customer);
            //TODO create payment provider
        };
    }
}
