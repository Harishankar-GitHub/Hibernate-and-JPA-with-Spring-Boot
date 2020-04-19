package com.jpa_and_hibernate;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa_and_hibernate.entity.FullTimeEmployee;
import com.jpa_and_hibernate.entity.PartTimeEmployee;
import com.jpa_and_hibernate.repository.CourseRepository;
import com.jpa_and_hibernate.repository.EmployeeRepository;
import com.jpa_and_hibernate.repository.StudentRepository;

@SpringBootApplication
public class JpaAndHibernateApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(JpaAndHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
//		Course course = courseRepository.findById(10001L);
//		logger.info("\n Course 10001 -> {}", course);
//		
//		courseRepository.deleteById(10002L);			// If this method has to be tested from Junit, this line has to be commented.
//		
//		courseRepository.save(new Course("Hibernate"));

//		courseRepository.playWithEntityManager();
		
//		studentRepository.saveStudentWithPassport();
		
//		studentRepository.someOperationToUnderstandPersistenceContext();
		
//		courseRepository.addHardcodedReviewsForCourse();
		
//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("5", "Great Hands-on Stuff"));
//		reviews.add(new Review("5", "Hatsoff"));
//		courseRepository.addReviewsForCourse(10003L, reviews );
		
//		studentRepository.insertHardcodedStudentAndCourse();
		
//		studentRepository.insertStudentAndCourse(new Student("Jackkk"), new Course("Microservices in 100 steps"));
		
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jackkk", new BigDecimal("10000")));
		logger.info("All Employees -> {}", employeeRepository.retrieveAllEmployees());
		
		
		logger.info("\n retrieveAllPartTimeEmployees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		logger.info("\n retrieveAllFullTimeEmployees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
		
		}
		
	}
