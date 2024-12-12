package jspeetr.semPraca.library.Repositories;

import jspeetr.semPraca.library.Records.Book;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {
    private final JdbcClient jdbcClient;

    public BookRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    //--------------------GET METHODS--------------------------------------------------------------------//
    public List<Book> findAll() {
        return jdbcClient.sql("SELECT * FROM Books")
                .query(Book.class)
                .list();
    }

    public Optional<Book> findById(int id) {
        return jdbcClient.sql("SELECT * FROM Books WHERE book_id = ?")
                .param(id)
                .query(Book.class)
                .optional();
    }


    //--------------------CREATE METHODS--------------------------------------------------------------------//
    public void create(Book book) {
        var updated = jdbcClient.sql("INSERT INTO Books (title, author, isbn, available_copies) VALUES (?, ?, ?, ?)")
                .params(List.of(book.title(), book.author(), book.isbn(), book.available_copies()))
                .update();
        Assert.state(updated == 1, "Failed to create book " + book.title());
    }


    //--------------------DELETE METHODS--------------------------------------------------------------------//
    public void delete(int id) {
        var updated = jdbcClient.sql("DELETE FROM Books WHERE book_id = ?")
                .param(id)  // Ensure parameter matches the column name
                .update();
        Assert.state(updated == 1, "Failed to delete book " + id);
    }
}
