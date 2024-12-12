package jspeetr.semPraca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SemPracaApplication {

	private static final Logger log = LoggerFactory.getLogger(SemPracaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SemPracaApplication.class, args);
	}
}
