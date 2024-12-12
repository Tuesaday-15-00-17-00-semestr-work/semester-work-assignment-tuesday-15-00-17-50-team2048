package jspeetr.semPraca.library;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/create")
    void create(@Valid @RequestBody Transactions transaction) {
        transactionRepository.create(transaction);
    }

    @GetMapping("/fetch")
    public List<Transactions> findall() {
        return transactionRepository.findAll();
    }
}