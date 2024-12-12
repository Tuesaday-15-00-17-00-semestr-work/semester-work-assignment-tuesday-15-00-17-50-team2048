package jspeetr.semPraca.library.Repositories;

import jspeetr.semPraca.library.Records.Borrowed;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import java.util.List;

@Repository
public class BorrowedRepository {
    private final JdbcClient jdbcClient;

    public BorrowedRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    //--------------------GET METHODS--------------------------------------------------------------------//
    public List<Borrowed> findAll() {
        return jdbcClient.sql("SELECT * FROM Borrowed")
                .query(Borrowed.class)
                .list();
    }


    //--------------------CREATE METHODS--------------------------------------------------------------------//
    public void create(Borrowed borrowed) {
        var updated = jdbcClient.sql("INSERT INTO Borrowed (title, author, date_of) VALUES (?, ?, ?)")
                .params(List.of(borrowed.title(), borrowed.author(),borrowed.date_of()))
                .update();
        Assert.state(updated == 1, "Failed to borrow book " + borrowed.title());
    }


    //--------------------DELETE METHODS--------------------------------------------------------------------//
    public void delete(int id) {
        var updated = jdbcClient.sql("DELETE FROM Borrowed WHERE borrow_id = ?")
                .param(id)
                .update();
        Assert.state(updated == 1, "Failed to return book " + id);
    }
}
