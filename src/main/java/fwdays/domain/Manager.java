package fwdays.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Manager extends BaseEntity {

	private String name;

	private String email;

	private String phone;

	@OneToMany(mappedBy = "deliveryManager")
	private List<Order> orders;
}
