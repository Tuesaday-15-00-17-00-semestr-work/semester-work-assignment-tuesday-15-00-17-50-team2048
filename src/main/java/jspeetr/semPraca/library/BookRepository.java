package jspeetr.semPraca.library;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private static final Logger log = LoggerFactory.getLogger(BookRepository.class);
    private final JdbcClient jdbcClient;

    public BookRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

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

    public void create(Book book) {
        var updated = jdbcClient.sql("INSERT INTO Books (title, author, isbn, available_copies) VALUES (?, ?, ?, ?)")
                .params(List.of(book.title(), book.author(), book.isbn(), book.available_copies()))
                .update();
        Assert.state(updated == 1, "Failed to create book " + book.title());
    }

    public void update(Book book, int id) {
        var updated = jdbcClient.sql("UPDATE Books SET title = ?, author = ?, available_copies = ? WHERE book_id = ?")
                .params(List.of(book.title(), book.author(), book.available_copies(), book.book_id()))
                .update();
        Assert.state(updated == 1, "Failed to update book " + book.title());
    }

    public void delete(int id) {
        var updated = jdbcClient.sql("DELETE FROM Books WHERE book_id = ?")
                .param(id)  // Ensure parameter matches the column name
                .update();
        Assert.state(updated == 1, "Failed to delete book " + id);
    }
}
