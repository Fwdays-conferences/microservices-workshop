package fwdays.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Customer extends BaseEntity {
	private String name;

	private String address;

	private String phone;

	private String email;

	private String cardNumber;

	private double balance;

	@ManyToOne
	@JoinColumn(name = "provider_id")
	private PaymentProvider provider;

	@OneToMany
	private List<Order> orders;

	@OneToMany
	private List<Payment> payments;

//	@OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "customer")
//	private CustomerSetting setting;

	private boolean notifyByEmail;

	private boolean notifyByPhone;

}
