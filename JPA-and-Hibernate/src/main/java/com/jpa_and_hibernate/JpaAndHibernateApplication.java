package com.jpa_and_hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa_and_hibernate.entity.Course;
import com.jpa_and_hibernate.repository.CourseRepository;
import com.jpa_and_hibernate.repository.StudentRepository;

@SpringBootApplication
public class JpaAndHibernateApplication implements CommandLineRunner {

	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;
	
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

		courseRepository.playWithEntityManager();
		
//		studentRepository.saveStudentWithPassport();
		
//		studentRepository.someOperationToUnderstandPersistenceContext();
		
	}

}
