//package com.jpa_and_hibernate;
// The above is the default line which has the package
// Which ever class we are testing, that package name should be replaced here.
package com.jpa_and_hibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.jpa_and_hibernate.entity.Course;
import com.jpa_and_hibernate.entity.Review;

@SpringBootTest
class CourseRepositoryTest {
	
	// Junit runs between Context Launch and Context Destroy
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Test
	public void findById_Basic() {
//		logger.info("Test is running");
		Course course = courseRepository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}
	
	
	@Test
	@DirtiesContext																// The deleteById_Basic() modifies (Inserts or Updates or Deletes) the table state.
	public void deleteById_Basic()											// By using @DirtiesContext annotation, Spring Boot will reset the data
	{																						// after executing this method.
		courseRepository.deleteById(10002L);
		assertNull(courseRepository.findById(10002L));
	}
	
	@Test
	@DirtiesContext
	public void save_basic()
	{
		// To check save() operation, doing the below steps.
		
		// 1. Getting a course
		Course course = courseRepository.findById(10001L);
		// 2. Checking the value initially
		assertEquals("JPA in 50 Steps", course.getName());
		// 3. Updating the course name
		course.setName("JPA in 50 Steps - Updated");
		// 4. Saving the course
		courseRepository.save(course);
		// 5. Getting the course again
		Course course1 = courseRepository.findById(10001L);
		// 6. Checking the value again
		assertEquals("JPA in 50 Steps - Updated", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager()
	{
		courseRepository.playWithEntityManager();
	}
	
	@Test
	@Transactional
	public void retrieveReviewsForCourse()
	{
		Course course1 = courseRepository.findById(10001L);
		logger.info("\n Testing retrieveReviewsForCourse() -> {}", course1.getReviews());
		
	}
	
	@Test
	@Transactional
	public void retrieveCourseForReview()
	{
		Review review1 = em.find(Review.class, 50001L);
		logger.info("\n Testing retrieveCourseForReview() -> {}", review1.getCourse());
		
	}

}
