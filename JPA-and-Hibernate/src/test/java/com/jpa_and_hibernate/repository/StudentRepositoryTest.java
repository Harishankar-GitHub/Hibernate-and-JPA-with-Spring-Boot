// package com.jpa_and_hibernate;
// The above is the default line which has the package
// Which ever class we are testing, that package name should be replaced here.
package com.jpa_and_hibernate.repository;

import com.jpa_and_hibernate.entity.Address;
import com.jpa_and_hibernate.entity.Course;
import com.jpa_and_hibernate.entity.Passport;
import com.jpa_and_hibernate.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Slf4j
class StudentRepositoryTest {
	
	// Junit runs between Context Launch and Context Destroy
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	EntityManager entityManager;
	
	@Test
	public void someTest() {
		studentRepository.someOperationToUnderstandPersistenceContext();
	}
		
	@Test
	@Transactional	
	// Since we have put fetch=FetchType.LAZY in Student Class, Passport details won't be fetched unless requested.
	// Once first line in below method is executed, the transaction is finished. This is the reason for putting @Transactional here.
	// Now the transaction is available for the whole method.
	// And now the passport details will be fetched as it's requested in 3rd line of the method.
	public void retrieveStudentAndPassportDetails() {
		Student student = entityManager.find(Student.class, 20001L);
		log.info("Student details -> {}", student);
		log.info("Passport details -> {}", student.getPassport());
	}
	
	@Test
	@Transactional
	public void setAddress() {
		Student student = entityManager.find(Student.class, 20001L);
		student.setAddress(new Address("No 101", "Some Street", "Bangalore"));
		entityManager.flush();
		log.info("Student details -> {}", student);
		log.info("Passport details -> {}", student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent()
	{
		Passport passport = entityManager.find(Passport.class, 40001L);
		log.info("Passport Details -> {}", passport);
		log.info("Student Details -> {}", passport.getStudent());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndCourses()
	{
		Student s = entityManager.find(Student.class, 20001L);
		log.info("\n Testing retrieveStudentAndCourses() -> Student details -> {}", s);
		log.info("\n Testing retrieveStudentAndCourses() -> {}", s.getCourses());
	}
	
	@Test
	@Transactional
	public void retrieveCourseAndStudents()
	{
		Course c = entityManager.find(Course.class, 10001L);
		log.info("\n Testing retrieveCourseAndStudents() -> Course details -> {}", c);
		log.info("\n Testing retrieveCourseAndStudents() -> {}", c.getStudents());
	}
}
