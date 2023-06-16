package fwdays.book.web;

import fwdays.book.domain.Book;
import fwdays.book.persistence.BookRepository;
import fwdays.book.web.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @GetMapping
    public List<BookDTO> getBooks() {
        return bookRepository.findAll()
                .stream().map(book -> modelMapper.map(Book.class, BookDTO.class)).toList();
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable int id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) {
        return modelMapper.map(bookRepository.save(
                modelMapper.map(bookDTO, Book.class)), BookDTO.class);
    }

    @PutMapping
    public void updateBook(Book book) {
        bookRepository.save(book);
    }
}
