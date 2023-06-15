package fwdays.notification.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationDTO {
    private int customerId;

    //For example, Delivery, Change order status
    //private String notificationType;

    //private String email;

    private String title;

    private String text;

    private LocalDateTime created;
}
