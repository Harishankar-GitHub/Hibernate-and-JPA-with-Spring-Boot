package com.jpa;

import com.jpa.Dao.Person_Repository;
import com.jpa.Entity.Person;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
// We are implementing CommandLineRunner class.
// We have to implement the methods in CommandLineRunner class.
// CommandLineRunner class is used to execute the code which is in it's methods at the time of application launch.

	private static final Logger LOGGER = LoggerFactory.getLogger(JpaApplication.class);
	private static final String SEPARATOR = "-----------------------------------------------------------------------------";

	private final Person_Repository person_repository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) {

		printSeparator();
		LOGGER.info("\n User Id 10001 -> {}", person_repository.findById(10001));
		printSeparator();

		LOGGER.info("\n Inserting 10006 -> {}", person_repository.insert(new Person("Smith", "Chennai", new Date())));
		printSeparator();

		LOGGER.info("\n Updating 10003 -> {}", person_repository.update(new Person(10003, "Jill", "Singapore", new Date())));
		person_repository.delete(10002);
		// We can't put this in logger.info because this method doesn't return anything.
		// So we can't print anything.
		printSeparator();

		LOGGER.info("All Users -> {}", person_repository.findAll());
		printSeparator();
	}

	private void printSeparator() {
		System.out.println(SEPARATOR);
	}
}
