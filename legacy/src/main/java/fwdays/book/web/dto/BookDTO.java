package fwdays.book.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookDTO {
    private int id;

    private LocalDateTime createdAt;

    private String name;

    private int year;

    private int pages;

    private double price;

    private int amount;

    private int authorId;
}
