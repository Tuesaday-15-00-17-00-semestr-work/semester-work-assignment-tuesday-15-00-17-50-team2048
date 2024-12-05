package jspeetr.semPraca.library;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    List<Book> findall(){
        return bookRepository.findAll();
    }

    @PostMapping("")
    void create(@Valid @RequestBody Book book) {
        bookRepository.create(book);
    }

    @PutMapping("/{book_id}")
    public void updateBook(@PathVariable int book_id, @RequestBody Book book) {
        bookRepository.update(book, book_id);
    }

    @DeleteMapping("/{book_id}")
    public void deleteBook(@PathVariable int book_id) {
        bookRepository.delete(book_id);
    }
}
