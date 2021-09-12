package com.jpa;

import com.jpa.Dao.Person_Repository;
import com.jpa.Entity.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@SpringBootApplication
@Slf4j
public class JpaApplication implements CommandLineRunner {
// We are implementing CommandLineRunner class.
// We have to implement the methods in CommandLineRunner class.
// CommandLineRunner class is used to execute the code which is in its methods at the time of application launch.

	private static final String SEPARATOR = "-----------------------------------------------------------------------------";

	private final Person_Repository person_repository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) {

		printSeparator();
		log.info("\n User Id 10001 -> {}", person_repository.findById(10001));
		printSeparator();

		log.info("\n Inserting 10006 -> {}", person_repository.insert(new Person("Smith", "Chennai", new Date())));
		printSeparator();

		log.info("\n Updating 10003 -> {}", person_repository.update(new Person(10003, "Jill", "Singapore", new Date())));
		person_repository.delete(10002);
		// We can't put this in logger.info because this method doesn't return anything.
		// So we can't print anything.
		printSeparator();

		log.info("All Users -> {}", person_repository.findAll());
		printSeparator();
	}

	private void printSeparator() {
		System.out.println(SEPARATOR);
	}
}
