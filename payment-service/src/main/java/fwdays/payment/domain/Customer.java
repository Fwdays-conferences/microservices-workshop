package fwdays.payment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table
@Entity
public class Customer extends BaseEntity {
    private String cardNumber;

    private double balance;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "provider_id")
    private PaymentProvider provider;

    @OneToMany
    private List<Payment> payments;
}
