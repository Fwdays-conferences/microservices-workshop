package fwdays.payment.persistence;

import fwdays.payment.domain.PaymentProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentProviderRepository extends JpaRepository<PaymentProvider, Integer> {
}
