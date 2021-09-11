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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class PerformanceTuningTest {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	EntityManager em;
	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceTuningTest.class);
		
	@Test
	@Transactional
	public void creatingNPlusOneProblem() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
		// Instead of getResultList(), we can use several other methods.
		// One of them is getSingleResult(), which can be used if we know that the query will return only 1 record.

		for (Course c : courses)
		{
			logger.info("\n Course -> {} Students -> {} \n", c, c.getStudents());
		}
		
		// If we execute this method, 4 queries will be fired.
		// 1 query to fetch all courses. (We have 3 records in data.sql)
		// And 3 queries to fetch students for each course.
		// We have 3 courses. So 3 queries to fetch students.
		// If we have 10 records in course table for example.
		// Then 1 query to fetch all courses.
		// And 10 queries to fetch students for each course.
		// This causes N+1 Problem.
		// To overcome this problem, below methods have the solution.
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem_EntityGraph() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		// Created a graph.
		entityGraph.addSubgraph("students");
		// Adding a subgraph.
		
		List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).
				setHint("javax.persistence.loadgraph", entityGraph).getResultList();
		
		for (Course c : courses)
		{
			logger.info("\n EntityGraph -> Course -> {} Students -> {} \n", c, c.getStudents());
		}
		
		// Course & Student are in @ManyToMany relationship.
		// So Anything to Many (OneToMany & ManyToMany) is Lazy Fetch by default.
		// If we make it to Eager Fetch, then each time while retrieving courses, student details will also be retrieved.
		// If there are large volume of data, this might cause performance issue.
		// So, if the need to fetch Courses along with Students is not very frequent, then we can keep the entity as Lazy Fetch by default.
		// And we can create a Graph and pass it in a setHint along with the query.
		// By doing this, only when this query is executed, Courses will be fetched along with students.
		// In other places, if we retrieve course, only course details will be retrieved.
		// Hence, the N+1 Problem is solved.
		// If we execute this method, just 1 query is fired.
	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblem_JoinFetch() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class).getResultList();

		for (Course c : courses)
		{
			logger.info("\n\n JoinFetch -> Course -> {} Students -> {} \n\n", c, c.getStudents());
		}
		
		// JOIN FETCH is also one of the solutions for N+1 Problem.
		// JOIN FETCH is different from JOIN.
		// JOIN FETCH does a select * from both A & B tables and join both tables. (For example, A JOIN FETCH B)
		// JOIN does a select * from A table and join both tables. (For example, A JOIN B)
		// Hence, the N+1 Problem is solved.
		// If we execute this method, just 1 query is fired.
	}
}
