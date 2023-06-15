package fwdays.book.web;

import fwdays.book.domain.Book;
import fwdays.book.persistence.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable int id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping
    public void updateBook(Book book) {
        bookRepository.save(book);
    }
}
