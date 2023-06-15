package fwdays.web;

import fwdays.book.domain.Book;
import fwdays.book.persistence.BookRepository;
import fwdays.delivery.domain.Manager;
import fwdays.delivery.persistence.ManagerRepository;
import fwdays.order.domain.Customer;
import fwdays.order.domain.Order;
import fwdays.order.persistence.CustomerRepository;
import fwdays.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ShopController {

	private String libraryName = "FwDays Shop";

	private BookRepository bookRepository;

	private CustomerRepository customerRepository;

	private ManagerRepository managerRepository;

	private OrderService orderService;

	@GetMapping("library")
	public String getLibraryName() {
		return libraryName;
	}

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public Book getBook(int id) {
		return bookRepository.findById(id).orElseThrow();
	}

	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	public void updateBook(Book book) {
		bookRepository.save(book);
	}

	public Order createOrder(int bookId, int number, int customerId) {
		return orderService.createOrder(bookId, number, customerId);
	}

	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public List<Customer> findCustomers() {
		return customerRepository.findAll();
	}

	public void addBook(int orderId, int bookId, int number) {
		orderService.addBook(orderId, bookId, number);
	}

	public void completeOrder(int orderId) {
		orderService.complete(orderId);
	}

	public void cancel(int orderId) {
		orderService.cancel(orderId);
	}

	public List<Order> findOrders() {
		return orderService.findOrders();
	}

	public Order findOrderById(int orderId) {
		return orderService.findOrderById(orderId);
	}

	public List<Manager> findManagers() {
		return managerRepository.findAll();
	}

	public void saveManager(Manager manager) {
		managerRepository.save(manager);
	}

}
