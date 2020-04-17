package com.jdbctojpa.JDBC_to_JPA;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jdbctojpa.JDBC_to_JPA.Entity.Person;
import com.jdbctojpa.JDBC_to_JPA.JPA.Person_JPA_Repository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{
// We are implementing CommandLineRunner class.
// We have to implement the methods in CommandLineRunner class.
// CommandLineRunner class is used to execute the code which is in it's methods at the time of application launch
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Person_JPA_Repository person_JPA_Repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("\n User Id 10001 -> {}", person_JPA_Repository.findById(10001));
		
		logger.info("\n Inserting 10006 -> {}", person_JPA_Repository.insert(new Person("Smith", "Chennai", new Date())));
		logger.info("\n Updating 10003 -> {}", person_JPA_Repository.update(new Person(10003, "Jill", "Singapore", new Date())));
		person_JPA_Repository.delete(10002);
		// We can't put this in logger.info because this method doesn't return anything.
		// So we can't print anything.
		logger.info("All Users -> {}", person_JPA_Repository.findAll());
		
	}

}
