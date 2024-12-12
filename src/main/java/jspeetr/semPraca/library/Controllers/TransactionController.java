package jspeetr.semPraca.library.Controllers;

import jakarta.validation.Valid;
import jspeetr.semPraca.library.Repositories.TransactionRepository;
import jspeetr.semPraca.library.Records.Transactions;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    //--------------------GET METHODS--------------------------------------------------------------------//
    @GetMapping("/fetch")
    public List<Transactions> findall() {
        return transactionRepository.findAll();
    }

    //--------------------CREATE METHODS--------------------------------------------------------------------//
    @PostMapping("/create")
    void create(@Valid @RequestBody Transactions transaction) {
        transactionRepository.create(transaction);
    }
}