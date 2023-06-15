package fwdays.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Book extends BaseEntity {
	private String name;

	private int year;

	private int pages;

	private double price;

	private int amount;

	@OneToOne
	private Person author;
}
