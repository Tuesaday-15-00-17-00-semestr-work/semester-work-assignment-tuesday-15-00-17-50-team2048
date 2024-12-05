package jspeetr.semPraca.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @GetMapping("")
    List<Book> findall(){
        return bookRepository.findAll();
    }

    @PostMapping("")
    void create(@Valid @RequestBody Book book) {
        bookRepository.create(book);
    }

    /* @GetMapping("/{id}")
    Book findById(@PathVariable int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new RunNotFoundException();
        }
        return book.get();
    }

    //Put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Book book, @PathVariable Integer id) {
        bookRepository.update(book, id);
    }

    //Delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        bookRepository.delete(id);
    }*/
}
