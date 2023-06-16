package fwdays.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    private Customer customer;

    private boolean payed;

    private boolean completed;

    //TODO do we need it here?
    private boolean delivered;

    private boolean cancelled;

    public double getAmount() {
        //FIXME book price is not available
        //return items.stream().mapToDouble(item -> item.getBook().getPrice() * item.getNumber()).sum();
        return 0;
    }

    public void addItem(OrderItem item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

}
