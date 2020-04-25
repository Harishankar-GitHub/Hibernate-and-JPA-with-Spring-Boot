package com.jpa_and_hibernate.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Embedded
	// If I want the Address Fields to be directly present in Student table instead of Address as a separate Entity,
	// I can use @@Embedded Annotation here
	// and @Embedded Annotation in Address Entity.
	private Address address;
	
	@OneToOne(fetch=FetchType.LAZY)
	// fetch=FetchType.LAZY usage - Only when any of the passport columns or passport details are 
	// fetched while fetching Student details, then passport details is retrieved.
	// If this attribute is not used (Or fetch=FetchType.EAGER is used) , then for example, if select * from student is fired, 
	// student details will be fetched and corresponding passport details will also be fetched.
	// If this attribute is used, only student details will be fetched.
	private Passport passport;
	
	@ManyToMany	// Each Student can enroll many Courses.
	@JoinTable(
							name="STUDENT_COURSE",
							joinColumns=@JoinColumn(name="STUDENT_ID"),
							inverseJoinColumns=@JoinColumn(name="COURSE_ID")
					)
	// @JoinTable is used to create a Join Table.
	// name attribute is used to define Join Table Name.
	// joinColumn & inverseJoinColumn attributes are used to define the column name in the Join Table.
	private List<Course> courses = new ArrayList<>();
	
	
	protected Student()
	{
		// In Hibernate, if we use Parameterized Constructor, Java would't provide a No Argument Constructor.
		// To overcome that, we are defining a No Argument Constructor.
		// And in Hibernate, No Argument Constructor is needed.
	}
	
	public Student(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}
	
	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourse(Course course)
	// Actually this method is not needed.
	{
		this.courses.add(course);
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
