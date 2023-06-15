package fwdays.order.domain;

import fwdays.book.domain.Book;
import fwdays.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class OrderItem extends BaseEntity {
	@ManyToOne
	private Book book;

	private int number;
}
