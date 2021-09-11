package com.jpa_and_hibernate.repository;

import com.jpa_and_hibernate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(path="courses")
// By using above annotation, CourseSpringDataRepository is exposed to "http://localhost:8080/courses".
public interface CourseSpringDataRepository extends JpaRepository<Course, Long>{
	
	// The below are the Additional Methods (also known as Query Methods) apart from the Default CRUD methods.
	List<Course> findByName(String name);
	List<Course> findByNameAndId(String name, Long id);
	int countByName(String name);
	List<Course> findByNameOrderByIdDesc(String name);
	Long deleteByName(String name);
	
	// find, retrieve, query are keywords for retrieving data.
	// count & delete are also keywords.
	
	// Additional Methods - END
	
	// If Additional Methods are not sufficient, we can execute different types of queries.
	
	@Query("select c from Course c where name like '%100 Steps'")
	List<Course> coursesWith100StepsInName();
	// JPQL is used.
	
	@Query(value="select * from Course where name like '%100 Steps'", nativeQuery=true)
	List<Course> coursesWith100StepsInNameUsingNativeQuery();
	// Native Query is used.
	
	@Query(name="query_get_100_Step_courses")
	List<Course> coursesWith100StepsInNameUsingNamedQuery();
	// Named Query is used.
}
