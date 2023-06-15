package fwdays.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import fwdays.domain.Book;
import fwdays.domain.Customer;
import fwdays.domain.Manager;
import fwdays.domain.Order;
import fwdays.repository.BookRepository;
import fwdays.repository.CustomerRepository;
import fwdays.repository.ManagerRepository;
import fwdays.service.OrderService;

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
