package com.example.readRent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.readRent.Entities.Book;
import com.example.readRent.Entities.Role;
import com.example.readRent.Entities.User;
import com.example.readRent.Repositories.BookRepository;
import com.example.readRent.Repositories.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class ReadRentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadRentApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(
			final UserRepository userRepository,
			final BookRepository bookRepository) {
		return args -> {
			User adminAccount = userRepository.findByRole(Role.ADMIN);
			if (adminAccount == null) {
				User user = new User();
				user.setFirstname("Chandler");
				user.setLastname("Bing");
				user.setEmail("admin@gmail.com");
				user.setRole(Role.ADMIN);
				user.setPassword(new BCryptPasswordEncoder().encode("admin"));
				userRepository.save(user);
			}

			Book book = new Book();
			book.setAvailabilityStatus(true);
			book.setTitle("Book 1");
			book.setGenre("action");
			book.setAuthor("Arthur");
			bookRepository.save(book);
		};
	}
}
