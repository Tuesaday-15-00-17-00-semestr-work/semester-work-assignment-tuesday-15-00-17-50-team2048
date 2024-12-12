package jspeetr.semPraca.library;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class TransactionRepository {
    private final JdbcClient jdbcClient;

    public TransactionRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Transactions> findAll() {
        return jdbcClient.sql("SELECT * FROM Transactions")
                .query(Transactions.class)
                .list();
    }

    public void create(Transactions transactions) {
        var updated = jdbcClient.sql("INSERT INTO Transactions (user_id, book_id, actions, date_of) VALUES (?, ?, ?, ?)")
                .params(List.of(transactions.user_id(), transactions.book_id(), transactions.actions(), transactions.date_of()))
                .update();
        Assert.state(updated == 1, "Failed to create transaction for book " + transactions.book_id());
    }
}
