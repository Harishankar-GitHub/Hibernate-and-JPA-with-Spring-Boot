package com.jpa_and_hibernate.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa_and_hibernate.entity.Course;
import com.jpa_and_hibernate.entity.Passport;
import com.jpa_and_hibernate.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager entityManager;									// Repository should be able to interact with Database.
																					// That's why auto wiring EntityManager.
	
	public Student findById(Long id)
	{
		return entityManager.find(Student.class, id);		// entityManager.find() is one of the default methods.
	}
	
	public Student save(Student student)
	{
		if (student.getId() == null)
		{
			entityManager.persist(student);						// persist() is used to insert. In other words, save.
		}
		else
		{
			entityManager.merge(student);							// merge() is used to update.
		}
		return student;
	}
	
	public void deleteById(Long id)
	{
		Student student = findById(id);
		entityManager.remove(student);
	}
	
	public void saveStudentWithPassport()
	{
		Passport passport = new Passport("D12345");		// Creating a Passport record.
		entityManager.persist(passport);
		
		Student student = new Student("Mike");				// Creating a Student record.
		student.setPassport(passport);								// Setting the created Passport to this Student.
		entityManager.persist(student);
		// After executing this method, the Passport Id will be 1 and the Student Id will be 2.
		// This is because, the sequence is shared across all entities managed by an entity manager.
	}
	
	public void someOperationToUnderstandPersistenceContext()
	{
		//Database Operation 1 - Retrieve student
		Student student = entityManager.find(Student.class, 20001L);
		//Persistence Context (student)
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		//Persistence Context (student, passport)

		//Database Operation 3 - update passport
		passport.setNumber("E123456");
		//Persistence Context (student, passport++)
		
		//Database Operation 4 - update student
		student.setName("Ranga - Updated");
		//Persistence Context (student++ , passport++)
		
		// If these operations are executed from a method in StudentRepositoryTest.java, it will throw exception because,
		// there's no transaction for that method or for StudentRepositoryTest.java.
		// So, we have written these operations inside this method as this class has @Transactional.
	}
	
	public void insertHardcodedStudentAndCourse()
	{
		Student s = new Student("Jackkk");
		Course c = new Course("Microservices in 100 Steps");
		
		entityManager.persist(s);
		entityManager.persist(c);
		
		s.addCourse(c);
		c.addStudents(s);
		
		entityManager.persist(s);
	}
	
	public void insertStudentAndCourse(Student s, Course c)
	{
		s.addCourse(c);
		c.addStudents(s);
		
		entityManager.persist(s);
		entityManager.persist(c);
		
		// We need to persist both the objects here because Student and Course are in @ManyToMany relationship.
	}
		
																											
}
