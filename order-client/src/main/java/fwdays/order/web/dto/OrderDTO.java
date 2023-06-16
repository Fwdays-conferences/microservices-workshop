package fwdays.order.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private int id;

    private int customerId;

    private boolean payed;

    private boolean completed;
}
