package fwdays.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class CustomerSetting extends BaseEntity {

	@OneToOne(optional = false, mappedBy = "setting")
	private Customer customer;

	private boolean notifyByEmail;

	private boolean notifyByPhone;

}
