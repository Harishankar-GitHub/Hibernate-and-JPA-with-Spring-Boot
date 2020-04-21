//package com.jpa_and_hibernate;
// The above is the default line which has the package
// Which ever class we are testing, that package name should be replaced here.
package com.jpa_and_hibernate.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa_and_hibernate.entity.Course;
import com.jpa_and_hibernate.entity.Passport;
import com.jpa_and_hibernate.entity.Student;

@SpringBootTest
class CriteriaQueryTest {
	
	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Test
	public void criteria_query_basic() {
		// Writing the below JPQL Query using Criteria Query.
		// select c from Course c
		
		// Steps - Start
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	// This Course.class is to define the result type.
		
		// 2. Define roots for the tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);		// This Course.class is to define from where the data has to be fetched.
		
		// 3. Define the predicates etc using Criteria Builder.
		
		// 4. Add predicates etc to Criteria Query.
		
		// 5. Build the TypedQuery using EntityManager & Criteria Query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		// Steps - End
		
		List<Course> resultList = query.getResultList();
		
		logger.info("\nCriteria Query -> {} \n", resultList);
		
	}
	
	@Test
	public void all_courses_having_100_steps() {
		// Writing the below JPQL Query using Criteria Query.
		// select c from Course c where name like '%100 Steps'
		
		// Steps - Start
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for the tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define the predicates etc using Criteria Builder.
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 Steps");
		// Syntax - cb.like(rootname.get("column name"), pattern))
		
		// 4. Add predicates etc to Criteria Query.
		cq.where(like100Steps);
		
		// 5. Build the TypedQuery using EntityManager & Criteria Query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		// Steps - End
		
		List<Course> resultList = query.getResultList();
		
		logger.info("\n Courses like '%100 Steps' -> {}\n", resultList);
		
	}
	
	@Test
	public void all_courses_without_students() {
		// Writing the below JPQL Query using Criteria Query.
		// select c from Course c where c.students is empty
		
		// Steps - Start
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	// This Course.class is to define the result type.
		
		// 2. Define roots for the tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);		// This Course.class is to define from where the data has to be fetched.
		
		// 3. Define the predicates etc using Criteria Builder.
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		
		// 4. Add predicates etc to Criteria Query.
		cq.where(studentsIsEmpty);
		
		// 5. Build the TypedQuery using EntityManager & Criteria Query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		// Steps - End
		
		List<Course> resultList = query.getResultList();
		
		logger.info("\n Courses like '%100 Steps' -> {}\n", resultList);
		
	}
	
	@Test
	public void join() {
		// Writing the below JPQL Query using Criteria Query.
		// select c from Course c join c.students s
		
		// Steps - Start
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	// This Course.class is to define the result type.
		
		// 2. Define roots for the tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);		// This Course.class is to define from where the data has to be fetched.
		
		// 3. Define the predicates etc using Criteria Builder.
		Join<Object, Object> join = courseRoot.join("students");
		
		// 4. Add predicates etc to Criteria Query.
		
		
		// 5. Build the TypedQuery using EntityManager & Criteria Query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		// Steps - End
		
		List<Course> resultList = query.getResultList();
		
		logger.info("\n Course Join Students -> {}\n", resultList);
		
	}
	
	@Test
	public void left_join() {
		// Writing the below JPQL Query using Criteria Query.
		// select c from Course c left join c.students s
		
		// Steps - Start
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object.
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);	// This Course.class is to define the result type.
		
		// 2. Define roots for the tables which are involved in the query.
		Root<Course> courseRoot = cq.from(Course.class);		// This Course.class is to define from where the data has to be fetched.
		
		// 3. Define the predicates etc using Criteria Builder.
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		// 4. Add predicates etc to Criteria Query.
		
		
		// 5. Build the TypedQuery using EntityManager & Criteria Query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		// Steps - End
		
		List<Course> resultList = query.getResultList();
		
		logger.info("\n Course Left Join Students -> {}\n", resultList);
		
	}
	
}
