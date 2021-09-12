package com.jdbc;

import com.jdbc.Dao.Person_DAO;
import com.jdbc.Entity.Person;
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
public class JdbcApplication implements CommandLineRunner {
// We are implementing CommandLineRunner class.
// We have to implement the methods in CommandLineRunner class.
// CommandLineRunner class is used to execute the code which is in it's methods at the time of application launch.

	private static final String SEPARATOR = "-----------------------------------------------------------------------------";

	private final Person_DAO person_dao;

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Override
	public void run(String... args) {

		printSeparator();
		log.info("\n All users -> {}", person_dao.findAll());
		// Logger takes the value of person_dao.findAll() & replaces with 1st occurrence of {}.
		 printSeparator();

		log.info("\n User Id 10001 -> {}", person_dao.findById(10001));
		printSeparator();

		log.info("\n User with a name -> {}", person_dao.findByName("Jill"));
		printSeparator();

		log.info("\n Users with specific name & location -> {}", person_dao.findByNameAndLocation("Harish", "Chennai"));
		printSeparator();

		log.info("\n User with specific location -> {}", person_dao.findByLocation("New York"));
		printSeparator();

		log.info("\n Deleting 10002 -> No. of records deleted is -> {}", person_dao.deleteById(10002));
		printSeparator();

		log.info("\n Inserting 10006 -> {}", person_dao.insert(new Person(1006, "Smith", "Chennai", new Date())));
		printSeparator();

		log.info("\n Updating 10003 -> {}", person_dao.update(new Person(10003, "Jill", "Singapore", new Date())));
		printSeparator();
	}

	private void printSeparator() {
		System.out.println(SEPARATOR);
	}
}
