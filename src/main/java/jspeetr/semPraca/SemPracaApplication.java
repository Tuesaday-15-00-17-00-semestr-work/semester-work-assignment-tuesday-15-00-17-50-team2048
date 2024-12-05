package jspeetr.semPraca;

import jspeetr.semPraca.library.Book;
import jspeetr.semPraca.library.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SemPracaApplication {

	private static final Logger log = LoggerFactory.getLogger(SemPracaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SemPracaApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BookRepository bookRepository) {
		return args -> {
			Book book = new Book(1,"First Book", "Myslef", "5464544489449", 1023);
			bookRepository.create(book);
		};
	}
}
