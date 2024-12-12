package jspeetr.semPraca.library.Controllers;

import jakarta.validation.Valid;
import jspeetr.semPraca.library.Records.Borrowed;
import jspeetr.semPraca.library.Repositories.BorrowedRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/borrowed")
public class BorrowedController {

    private final BorrowedRepository borrowedRepository;

    public BorrowedController(BorrowedRepository borrowedRepository) {
        this.borrowedRepository = borrowedRepository;
    }

    //--------------------GET METHODS--------------------------------------------------------------------//
    @GetMapping("/fetch")
    public List<Borrowed> findall() {
        return borrowedRepository.findAll();
    }

    //--------------------CREATE METHODS--------------------------------------------------------------------//
    @PostMapping("/create")
    void create(@Valid @RequestBody Borrowed borrowed) {
        borrowedRepository.create(borrowed);
    }

    //--------------------DELETE METHODS--------------------------------------------------------------------//
    @DeleteMapping("/delete/{borrow_id}")
    public void returnBorrowed(@PathVariable int borrow_id) {
        borrowedRepository.delete(borrow_id);
    }
}