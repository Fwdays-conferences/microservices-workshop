package fwdays.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Notification extends BaseEntity {
	private String recipient;

	private String email;

	private String title;

	private String text;

	private LocalDateTime created = LocalDateTime.now();
}
