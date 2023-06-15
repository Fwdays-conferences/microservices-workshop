package fwdays.book.domain;

import fwdays.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Book extends BaseEntity {
	private String name;

	@Column(name = "publishingYear")
	private int year;

	private int pages;

	private double price;

	private int amount;

	@ManyToOne(cascade = CascadeType.ALL)
	private Person author;
}
