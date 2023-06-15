package fwdays.payment.service;

import fwdays.order.domain.Order;
import fwdays.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

	public Payment charge(Order order) {
		if (order.getAmount() > order.getCustomer().getBalance()) {
			throw new RuntimeException("Not enough balance for order " + order.getId());
		}
		System.out.println("Charging " + order.getAmount() + " from credit card " + order.getCustomer().getCardNumber());
		System.out.println("Using payment provider " + order.getCustomer().getProvider().getName());

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		order.getCustomer().setBalance(order.getCustomer().getBalance() - order.getAmount());

		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setProvider(order.getCustomer().getProvider());
		payment.setSuccess(true);
		payment.setTransactionId("123");
		payment.setAmount(order.getAmount());

		return payment;

	}

}
