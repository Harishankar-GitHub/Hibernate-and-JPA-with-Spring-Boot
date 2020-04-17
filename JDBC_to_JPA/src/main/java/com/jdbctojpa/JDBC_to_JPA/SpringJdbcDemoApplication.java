package com.jdbctojpa.JDBC_to_JPA;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jdbctojpa.JDBC_to_JPA.Entity.Person;
import com.jdbctojpa.JDBC_to_JPA.JDBC.Person_JDBC_DAO;

//@SpringBootApplication
// Commenting the above line because we have to run JpaDemoApplication

public class SpringJdbcDemoApplication implements CommandLineRunner{
// We are implementing CommandLineRunner class.
// We have to implement the methods in CommandLineRunner class.
// CommandLineRunner class is used to execute the code which is in it's methods at the time of application launch
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Person_JDBC_DAO personJdbcDao;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("\n All users -> {}", personJdbcDao.findAll());
		// Logger takes the value of personJdbcDao.findAll() & replaces with 1st occurance of {}
		logger.info("\n User Id 10001 -> {}", personJdbcDao.findById(10001));
		logger.info("\n User with a name -> {}", personJdbcDao.findByName("Jill"));
		logger.info("\n Users with specific name & location -> {}", personJdbcDao.findByNameAndLocation("Harish", "Chennai"));
		logger.info("\n User with specific location -> {}", personJdbcDao.findByLocation("New York"));
		logger.info("\n Deleting 10002 -> No. of records deleted is -> {}", personJdbcDao.deleteById(10002));
		logger.info("\n Inserting 10006 -> {}", personJdbcDao.insert(new Person(10006, "Smith", "Chennai", new Date())));
		logger.info("\n Updating 10003 -> {}", personJdbcDao.update(new Person(10003, "Jill", "Singapore", new Date())));
		
	}

}
