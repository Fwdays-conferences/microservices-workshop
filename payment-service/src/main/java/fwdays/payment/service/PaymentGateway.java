package fwdays.payment.service;

import fwdays.order.web.dto.OrderDTO;
import fwdays.payment.domain.Customer;
import fwdays.payment.domain.Payment;
import fwdays.payment.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentGateway {

    private final CustomerRepository customerRepository;

    public Payment charge(OrderDTO order) {
        int customerId = order.getCustomerId();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        if (order.getAmount() > customer.getBalance()) {
            throw new RuntimeException("Not enough balance for order " + order.getId());
        }
        System.out.println("Charging " + order.getAmount() + " from credit card " + customer.getCardNumber());
        System.out.println("Using payment provider " + customer.getProvider().getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        customer.setBalance(customer.getBalance() - order.getAmount());

        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setProvider(customer.getProvider());
        payment.setSuccess(true);
        payment.setTransactionId("123");
        payment.setAmount(order.getAmount());

        return payment;

    }

}
