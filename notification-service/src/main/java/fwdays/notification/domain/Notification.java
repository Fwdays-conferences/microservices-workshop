package fwdays.notification.domain;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;

    private String recipient;

    private String email;

    private String title;

    private String text;

    @CreatedDate
    private LocalDateTime created;
}
