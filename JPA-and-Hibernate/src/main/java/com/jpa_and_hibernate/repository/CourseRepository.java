package com.jpa_and_hibernate.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa_and_hibernate.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager entityManager;									// Repository should be able to interact with Database.
																					// That's why auto wiring EntityManager.
	
	public Course findById(Long id)
	{
		return entityManager.find(Course.class, id);			// entityManager.find() is one of the default methods.
	}
	
	public Course save(Course course)
	{
		if (course.getId() == null)
		{
			entityManager.persist(course);							// persist() is used to insert. In other words, save.
		}
		else
		{
			entityManager.merge(course);							// merge() is used to update.
		}
		return course;
	}
	
	public void deleteById(Long id)
	{
		Course course = findById(id);
		entityManager.remove(course);
	}
	
	public void playWithEntityManager()
	{
		//-----------------------------------------------------------------------------------------------------------------------------------------
		Course course1 = new Course("Web Services in 100 Steps");
//		entityManager.persist(course1);
		Course course2 = new Course("Hibernate in 100 Steps");
//		entityManager.persist(course2);
		
		entityManager.flush();
		// flush() is used to send the operations to database. In other words, the changes till this line are saved in database.
		
		course1.setName("Web Services in 100 Steps - updated");
		// In this method, course1 is persisted by Entity Manager.
		// After persisting, there's a change in course1 which will also be saved.
		// The reason is, this method is inside @Transactional. So, even after persisting, Entity Manager will track the course
		// and save the changes.
		
//		entityManager.detach(course2);
		// if detach(object_name) is used, then Entity Manager will no longer keep a track of that object.
		// Changes made to that object after detach(object_name) will not be reflected in database.
		// entityManager.clear() can also be used. clear() clears everything in Entity Manager.
		// In this case, if clear() is used, both course1 & course2 will be cleared from Entity Manager.
		
		course2.setName("Hibernate in 100 Steps - updated");
		
//		entityManager.refresh(course1);
		// When refresh() is used, the changes made to the object will be rolled back.
		// The data for this object will be fetched from database.
		// So that, whichever data was present earlier, that will be present now.
		//-----------------------------------------------------------------------------------------------------------------------------------------
		//In the above block, the concepts of persist(), flush(), detach(), clear(), refresh() are implemented. Select the block and uncomment it to execute.
		//-----------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		//-----------------------------------------------------------------------------------------------------------------------------------------
//		Course course1 = new Course("Web Services in 100 Steps");
//		
////		course1.setName(null);
//		entityManager.persist(course1);
//		entityManager.flush();
		//-----------------------------------------------------------------------------------------------------------------------------------------
		// In the above block, the concept for @Column(nullable = false) is implemented. Select the block and uncomment it to execute.
		//-----------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		//-----------------------------------------------------------------------------------------------------------------------------------------
//		Course course1 = new Course("Web Services in 100 Steps");		// Inserting a new course to check created timestamp.
//		entityManager.persist(course1);
//		
//		Course course2 = findById(10001L);
//		course2.setName("JPA in 100 Steps - updated");							// Fetching an existing course & updating to check updated timestamp.
		//-----------------------------------------------------------------------------------------------------------------------------------------
		// In the above block, the concept for @UpdateTimestamp & @CreationTimestamp is implemented. Select the block and uncomment it to execute.
		//-----------------------------------------------------------------------------------------------------------------------------------------
		
		
		
	}
																											
		
																											
}
