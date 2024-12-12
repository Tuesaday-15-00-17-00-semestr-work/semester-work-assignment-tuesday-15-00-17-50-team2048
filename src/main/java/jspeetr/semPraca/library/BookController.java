package jspeetr.semPraca.library;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/fetch")
    List<Book> findall(){
        return bookRepository.findAll();
    }

    @GetMapping("/{book_id}")
    Optional<Book> findById(@PathVariable int book_id){
        return bookRepository.findById(book_id);
    }

    @PostMapping("/create")
    void create(@Valid @RequestBody Book book) {
        bookRepository.create(book);
    }

    @DeleteMapping("/delete/{book_id}")
    public void deleteBook(@PathVariable int book_id) {
        bookRepository.delete(book_id);
    }
}
