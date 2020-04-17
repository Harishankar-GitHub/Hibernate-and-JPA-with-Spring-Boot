//package com.jpa_and_hibernate;
// The above is the default line which has the package
// Which ever class we are testing, that package name should be replaced here.
package com.jpa_and_hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.jpa_and_hibernate.entity.Course;

@SpringBootTest
class Native_Queries_Test {
	
	// Junit runs between Context Launch and Context Destroy
	
	@Autowired
	EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Native Queries
	//There might be some situation where we might need Native Queries.
	//		1. For setting some tuning parameters.
	//		2. To use a database feature which is not supported by JPA.
	//		3. To do a mass Update (This can't be done using JPA). In JPA, we have to get the row, update the row.
	//
	// NOTE : Whenever we are using a Native Query, we are not making use of Persistence Context.
	// If we have the entities directly present in Persistence Context, then we need to make sure we refresh them.
	// So that we get the latest data from the database.
		
	@Test
	public void native_queries_Basic() {
		Query query = entityManager.createNativeQuery("select * from course", Course.class);
		// If we don't put Course.class, it will print the result as objects.
		// If we put Course.class, it will print proper values.
		List resultList = query.getResultList();
		logger.info("select * from course -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_positional_parameter() {							// Positional Parameters
		Query query = entityManager.createNativeQuery("select * from course where id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("select * from course where id = ? -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_names_parameter() {				// Named Parameters
		Query query = entityManager.createNativeQuery("select * from course where id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("select * from course where id = ? -> {}", resultList);
	}
	
	@Test
	@Transactional
	public void native_queries_to_mass_update()
	{
		Query query = entityManager.createNativeQuery("update course set last_updated_date = sysdate()", Course.class);
		int noOfRowsUpdated = query.executeUpdate();		// For updating or inserting or deleting, we use executeUpdate().
		logger.info("Number of rows updated -> {}", noOfRowsUpdated);
	}

}
