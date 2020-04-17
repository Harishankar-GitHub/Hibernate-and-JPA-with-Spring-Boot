//package com.jpa_and_hibernate;
// The above is the default line which has the package
// Which ever class we are testing, that package name should be replaced here.
package com.jpa_and_hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa_and_hibernate.entity.Course;

@SpringBootTest
class JPQL_Test {
	
	// Junit runs between Context Launch and Context Destroy
	
	@Autowired
	EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Test
	public void jpql_Basic() {
//		List resultList = entityManager.createQuery("select c from Course c").getResultList();		// This syntax is also correct
//		Query query = entityManager.createQuery("select c from Course c");									// This syntax is also correct. But everywhere we cannot write the query. So using a named query.
		// The above line should be assigned to a Query variable.
		Query query = entityManager.createNamedQuery("query_get_all_courses");						// NamedQuery is used here.
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_Typed() {
//		TypedQuery<Course> query = entityManager.createQuery("select c from Course c", Course.class);	// This syntax is also correct. But everywhere we cannot write the query. So using a named query.
		// Syntax for above line -> entityManager.createQuery("query", Classname.class);
		// Class name is given to expect the result type as Classname.class.
		// That's why it is assigned to a TypedQuery variable.
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);	// NamedQuery is used here.
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_Where() {
//		TypedQuery<Course> query = entityManager.createQuery("select c from Course c where name like '%100 Steps'", Course.class);	// This syntax is also correct. But everywhere we cannot write the query. So using a named query.
		// TypedQuery with where condition.
		// Condition has to be given in single quotes.
		
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_100_Step_courses", Course.class);	// NamedQuery is used here.
		
		List<Course> resultList = query.getResultList();
		logger.info("select c from Course c where name like '%100 Steps' -> {}", resultList);
	}

}
