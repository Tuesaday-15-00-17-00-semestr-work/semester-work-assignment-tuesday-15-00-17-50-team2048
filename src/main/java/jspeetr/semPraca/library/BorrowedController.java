package jspeetr.semPraca.library;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowed")
public class BorrowedController {

    private final BorrowedRepository borrowedRepository;

    public BorrowedController(BorrowedRepository borrowedRepository) {
        this.borrowedRepository = borrowedRepository;
    }

    @PostMapping("/create")
    void create(@Valid @RequestBody Borrowed borrowed) {
        borrowedRepository.create(borrowed);
    }

    @GetMapping("/fetch")
    public List<Borrowed> findall() {
        return borrowedRepository.findAll();
    }

    @DeleteMapping("/delete/{borrow_id}")
    public void returnBorrowed(@PathVariable int borrow_id) {
        borrowedRepository.delete(borrow_id);
    }
}