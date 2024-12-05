package jspeetr.semPraca.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class BookJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BookJsonDataLoader.class);
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    public BookJsonDataLoader(BookRepository bookRepository, ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.findAll().isEmpty()) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/books.json")) {
                List<Book> books = objectMapper.readValue(inputStream, new TypeReference<List<Book>>() {});
                log.info("Reading {} books from JSON data and saving to the database.", books.size());
                books.forEach(bookRepository::create);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Books are already loaded in the database.");
        }
    }
}