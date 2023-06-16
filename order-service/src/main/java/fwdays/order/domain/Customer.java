package fwdays.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @OneToMany
    private List<Order> orders;

    private boolean notifyByEmail;

    private boolean notifyByPhone;

}
