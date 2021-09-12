package com.jpa_and_hibernate;

import com.jpa_and_hibernate.entity.Course;
import com.jpa_and_hibernate.entity.FullTimeEmployee;
import com.jpa_and_hibernate.entity.PartTimeEmployee;
import com.jpa_and_hibernate.entity.Review;
import com.jpa_and_hibernate.entity.ReviewRating;
import com.jpa_and_hibernate.entity.Student;
import com.jpa_and_hibernate.repository.CourseRepository;
import com.jpa_and_hibernate.repository.EmployeeRepository;
import com.jpa_and_hibernate.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class JpaAndHibernateApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaAndHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Course course = courseRepository.findById(10001L);
		log.info("\n Course 10001 -> {}", course);

//		courseRepository.deleteById(10002L);			// If this method has to be tested from Junit, this line has to be commented.

		courseRepository.save(new Course("Hibernate"));

		courseRepository.playWithEntityManager();

		studentRepository.saveStudentWithPassport();
		
		studentRepository.someOperationToUnderstandPersistenceContext();
		
		courseRepository.addHardcodedReviewsForCourse();
		
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review(ReviewRating.FIVE, "Great Hands-on Stuff"));
		reviews.add(new Review(ReviewRating.FIVE, "Hatsoff"));
		courseRepository.addReviewsForCourse(10003L, reviews );
		
		studentRepository.insertHardcodedStudentAndCourse();
		
		studentRepository.insertStudentAndCourse(new Student("Jackkk"), new Course("Microservices in 100 steps"));
		
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jackkk", new BigDecimal("10000")));
		log.info("All Employees -> {}", employeeRepository.retrieveAllEmployees());


		log.info("\n retrieveAllPartTimeEmployees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		log.info("\n retrieveAllFullTimeEmployees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
	}
}
