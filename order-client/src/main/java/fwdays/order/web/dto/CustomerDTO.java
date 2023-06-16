package fwdays.order.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDTO {
    private int id;

    private LocalDateTime createdAt;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String cardNumber;

    private double balance;

    private int providerId;
}
