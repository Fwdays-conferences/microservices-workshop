package fwdays.order.web.dto;

public record CreateOrderDTO(int bookId, int number, int customerId, double price) {
}
