package fwdays.order.command;

public record CreateOrderCommand(int bookId, int customerId, int number, double price) {
}
