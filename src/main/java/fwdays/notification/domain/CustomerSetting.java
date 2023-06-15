package fwdays.notification.domain;

import fwdays.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class CustomerSetting extends BaseEntity {

//	@OneToOne(optional = false, mappedBy = "setting")
//	private Customer customer;

    private boolean notifyByEmail;

    private boolean notifyByPhone;

}
