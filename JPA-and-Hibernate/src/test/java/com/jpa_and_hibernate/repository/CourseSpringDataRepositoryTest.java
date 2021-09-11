// package com.jpa_and_hibernate;
// The above is the default line which has the package
// Which ever class we are testing, that package name should be replaced here.
package com.jpa_and_hibernate.repository;

import com.jpa_and_hibernate.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CourseSpringDataRepositoryTest {
	
	@Autowired
	CourseSpringDataRepository r;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseSpringDataRepositoryTest.class);
	
	@Test
	public void findById_coursePresent()
	{
		Optional<Course> courseOptional = r.findById(10001L);
		// In the latest version of Spring Data JPA, findById returns an Optional and not a Course Object.
		// Optional can be used to check whether Course is present.
//		l.info("\n findById_coursePresent -> {}", courseOptional.isPresent());
		assertTrue(courseOptional.isPresent());	// This line is to test with assertTrue.
		// Optional avoids the null value.
		// If no course with 10001L, then courseOptional will be a proper object but it won't have a course.
		// So, courseOptional.isPresent() will return false.
	}
	
	@Test
	public void findById_courseNotPresent()
	{
		Optional<Course> courseOptional = r.findById(20001L);
//		l.info("\n findById_courseNotPresent -> {}", courseOptional.isPresent());
		assertFalse(courseOptional.isPresent());	// This line is to test with assertFalse.
	}
	
	@Test
	public void playingAroundWithSpringDataRepository()
	{
//		Course c = new Course("Microservices in 100 Steps");
//		r.save(c);
//		c.setName("Microservices in 100 Steps - updated");
//		r.save(c);

		logger.info("\n\n Courses -> {}", r.findAll());
		logger.info("\n\n Count -> {}", r.count());
	}
	
	@Test
	public void sort()
	{
		Sort sd = Sort.by(Sort.Direction.DESC, "name");
		logger.info("\n\n  Descending Sorted Courses -> {} \n", r.findAll(sd));
		
		Sort sa = Sort.by(Sort.Direction.ASC, "name");
		logger.info("\n\n  Ascending Sorted Courses -> {} \n", r.findAll(sa));
	}
	
	@Test
	public void pagination()
	{
		PageRequest p = PageRequest.of(0, 3);
		// Creating a PageRequest.
		// Page Number starts with 0;
		// 3 is the number of records per page.
		Page<Course> firstPage = r.findAll(p);
		// Finding all the records and assigning it to a Page.
		logger.info("\n\n First Page -> {} \n\n", firstPage.getContent());
		
		Pageable pageable = firstPage.nextPageable();
		// Using firstPage.nextPageable() to get the next page.
		Page<Course> secondPage = r.findAll(pageable);
		// // Finding all the records and assigning it to a Page.
		logger.info("\n\n Second Page -> {} \n\n", secondPage.getContent());
		
		// We have various methods in Page.
		// We can check those by typing "firstPage."
	}
	
	@Test
	public void findUsingName()	// Custom Queries using Spring Data JPA Repository
	{
		logger.info("\n\n FindByName -> {} \n\n", r.findByName("JPA in 50 Steps"));
	}
}
