package com.jpa_and_hibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Course")			
//The table in database is actually created from this Entity Class.
//@Table is used if database table name and this entity class name are different.
@NamedQueries
(
		value = {
						@NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
						@NamedQuery(name = "query_get_100_Step_courses", query = "select c from Course c where name like '%100 Steps'")
					}
)
@Cacheable
// @Cacheable is used to store the data in Second Level Cache.
// Where can we put @Cacheable ? If a particular data is accessed frequently. And if that data doesn't change frequently.
// We are putting @Cacheable annotation here because, we are considering the Course details doesn't change frequently.
public class Course {	
	
	@Id								// Used to define this variable as the primary key of the table
	@GeneratedValue			// Used to Auto Generate values of this variable
	private Long id;
	
	
	@Column(name="name", nullable = false)			
	private String name;											
	// @Column is used if the column in table and the corresponding field or variable in this entity class are different.
	// By putting nullable = false, while creating the table, it will create the column as not null.
	// There are many more attributes like nullable, insertable, updatable, length, unique etc.
	// Do ctrl + click on @Column to see in detail.
	
	
	@OneToMany(mappedBy="course")
	// In @OneToMany, fetchType is LAZY by default.
	// Anything to Many (@OneToMany, @ManyToMany) - fetchType is LAZY by default.
	private List<Review> reviews  = new ArrayList<>();
	// private List<Review> reviews; - Should also work.
	
	
	@ManyToMany(mappedBy="courses")
	// Each Course can have many Students enrolled.
	// In @ManyToMany, Any side can be the owning side. In this case, we have considered Student as owning side.
	// So, mappedBy is put in Course.
	@JsonIgnore // Usage - we can ignore a specific field from being returned back in the Json Response. 
	private List<Student> students = new ArrayList<>();
	
	
	@UpdateTimestamp		// It is a Hibernate Annotation. Used to store the last updated timestamp of the row.
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp	// It is a Hibernate Annotation. Used to store the timestamp of the row when it is created for the 1st time.
	private LocalDateTime createdDate;
	
	
	protected Course()
	{
		// In Hibernate, if we use Parameterized Constructor, Java would't provide a No Argument Constructor.
		// To overcome that, we are defining a No Argument Constructor.
		// And in Hibernate, No Argument Constructor is needed.
	}
	
	public Course(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public void addReview(Review review)
	// We don't want others to set reviews.
	// That's why writing addReview() & removeReview().
	// Actually this method is not needed. Setting Course to review is enough. Even if addReview is used, doesn't matter.
	{
		this.reviews.add(review);
	}
	
	public void removeReview(Review review)
	{
		this.reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student student) {
	// Actually this method is not needed. Even if addStudents is used, doesn't matter.
		this.students.add(student);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

//	@Override
//	public String toString() {
//		return "Course [Name=" + name + "]";
//	}
	
	
	
}
