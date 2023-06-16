package fwdays.delivery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "DELIVERY_ORDER")
@Entity
@Getter
@Setter
public class Order extends BaseEntity {

    @ManyToOne
    private Manager deliveryManager;
}
