// package com.jpa_and_hibernate;
// The above is the default line which has the package
// Which ever class we are testing, that package name should be replaced here.
package com.jpa_and_hibernate.repository;

import com.jpa_and_hibernate.entity.Course;
import com.jpa_and_hibernate.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
class JPQL_Test {
	
	// Junit runs between Context Launch and Context Destroy
	
	@Autowired
	EntityManager entityManager;
	
	private static final Logger logger = LoggerFactory.getLogger(JPQL_Test.class);
		
	@Test
	public void jpql_Basic() {
//		List resultList = entityManager.createQuery("select c from Course c").getResultList();		// This syntax is also correct
//		Query query = entityManager.createQuery("select c from Course c");							// This syntax is also correct. But everywhere we cannot write the query. So using a named query.
		// The above line should be assigned to a Query variable.
		Query query = entityManager.createNamedQuery("query_get_all_courses");						// NamedQuery is used here.
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_Typed() {
//		TypedQuery<Course> query = entityManager.createQuery("select c from Course c", Course.class);			// This syntax is also correct. But everywhere we cannot write the query. So using a named query.
		// Syntax for above line -> entityManager.createQuery("query", Classname.class);
		// Class name is given to expect the result type as Classname.class.
		// That's why it is assigned to a TypedQuery variable.
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);	// NamedQuery is used here.
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_Where() {
//		 TypedQuery<Course> query = entityManager.createQuery("select c from Course c where name like '%100 Steps'", Course.class);	// This syntax is also correct. But everywhere we cannot write the query. So using a named query.
//		 TypedQuery with where condition.
//		 Condition has to be given in single quotes.
		
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_100_Step_courses", Course.class);	// NamedQuery is used here.
		
		List<Course> resultList = query.getResultList();
		logger.info("select c from Course c where name like '%100 Steps' -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_without_students() {
	
		TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\n\n select c from Course where c.Students is empty -> {} \n\n", resultList);
	}
	
	@Test
	public void jpql_courses_with_atleast_2_students() {
	
		TypedQuery<Course> query = entityManager.createQuery("select c from Course c where size(c.students) > 1", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("\n\n select c from Course c where size(c.students) > 1 -> {} \n\n", resultList);
	}
	
	@Test
	public void jpql_courses_ordered_by_students() {
	
		TypedQuery<Course> query = entityManager.createQuery("select c from Course c order by size(c.students)", Course.class);
		// Default is ascending.
		List<Course> resultList = query.getResultList();
		logger.info("\n\n select c from Course c order by size(c.students) -> {} \n\n", resultList);
	}
	
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
	
//		TypedQuery<Passport> query = entityManager.createQuery("select p from Passport p where p.number like '%1234%'", Passport.class);
//		List<Passport> resultList = query.getResultList();
//		logger.info("\n\n Passports like '%1234%' -> {} \n\n", resultList);
		
		// Can be retrieved from Student Class as well.
		TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("\n\n Passports like '%1234%' -> {} \n\n", resultList);
		
		// We can use few more functions such as like, between, is null, upper, lower, trim, length etc.
	}
	
	@Test
	public void join()
	{
		Query q = entityManager.createQuery("select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = q.getResultList();
		// The list has list of array of objects.
		// In 1 array, Course Object and Student Object will be present.
		// Therefore, the list will have list of array of objects.
		logger.info("JOIN -> {}", resultList.size());
		
		for (Object[] o : resultList)
		{
			// In 1 iteration, o will have 1 array which will have 1 Course Object & 1 corresponding Student Object.
			logger.info("\n\n Course -> {} \n\n Student -> {} \n\n", o[0], o[1]);
		}
	}
	
	@Test
	public void left_join()
	{
		Query q = entityManager.createQuery("select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = q.getResultList();
		// The list has list of array of objects.
		// In 1 array, Course Object and Student Object will be present.
		// Therefore, the list will have list of array of objects.
		logger.info("LEFT JOIN -> {}", resultList.size());
		
		for (Object[] o : resultList)
		{
			// In 1 iteration, o will have 1 array which will have 1 Course Object & 1 corresponding Student Object.
			logger.info("\n\n Course -> {} \n\n Student -> {} \n\n", o[0], o[1]);
		}
	}
	
	@Test
	public void cross_join()
	{
		Query q = entityManager.createQuery("select c, s from Course c, Student  s"); // This is the CROSS JOIN Syntax.
		List<Object[]> resultList = q.getResultList();
		// The list has list of array of objects.
		// In 1 array, Course Object and Student Object will be present.
		// Therefore, the list will have list of array of objects.
		logger.info("CROSS JOIN -> {}", resultList.size());
		
		for (Object[] o : resultList)
		{
			// In 1 iteration, o will have 1 array which will have 1 Course Object & 1 corresponding Student Object.
			logger.info("\n\n Course -> {} \n\n Student -> {} \n\n", o[0], o[1]);
		}
	}
}
