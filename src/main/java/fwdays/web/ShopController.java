package fwdays.web;

import fwdays.book.domain.Book;
import fwdays.book.persistence.BookRepository;
import fwdays.delivery.domain.Manager;
import fwdays.delivery.persistence.ManagerRepository;
import fwdays.order.domain.Customer;
import fwdays.order.domain.Order;
import fwdays.order.persistence.CustomerRepository;
import fwdays.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ShopController {

	private String libraryName = "FwDays Shop";

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private OrderService orderService;

	@GetMapping("library")
	public String getLibraryName() {
		return libraryName;
	}

	@GetMapping("books")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("books/{id}")
	public Book getBook(@PathVariable int id) {
		return bookRepository.findById(id).orElseThrow();
	}

	@PostMapping(path = "books")
	public Book saveBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	public void updateBook(Book book) {
		bookRepository.save(book);
	}

	@PostMapping("orders/{bookId}/{number}/{customerId}")
	public Order createOrder(@PathVariable int bookId, @PathVariable int number, @PathVariable int customerId) {
		return orderService.createOrder(bookId, number, customerId);
	}

	@PostMapping("customers")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@GetMapping
	public List<Customer> findCustomers() {
		return customerRepository.findAll();
	}

	public void addBook(int orderId, int bookId, int number) {
		orderService.addBook(orderId, bookId, number);
	}

	@PostMapping("orders/{orderId}")
	public void completeOrder(@PathVariable int orderId) {
		orderService.complete(orderId);
	}

	public void cancel(int orderId) {
		orderService.cancel(orderId);
	}

	@GetMapping("orders")
	public List<Order> findOrders() {
		return orderService.findOrders();
	}

	@GetMapping("orders/{orderId}")
	public Order findOrderById(@PathVariable int orderId) {
		return orderService.findOrderById(orderId);
	}

	public List<Manager> findManagers() {
		return managerRepository.findAll();
	}

	public void saveManager(Manager manager) {
		managerRepository.save(manager);
	}

}
