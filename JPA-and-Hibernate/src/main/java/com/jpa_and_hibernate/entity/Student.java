package com.jpa_and_hibernate.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// In Hibernate, if we use Parameterized Constructor, Java wouldn't provide a No Argument Constructor.
// To overcome that, we are defining a No Argument Constructor.
// And in Hibernate, No Argument Constructor is needed.
@ToString(onlyExplicitlyIncluded = true)
public class Student {
	
	@Id
	@GeneratedValue
	@Getter @ToString.Include
	private Long id;
	
	@Column(nullable = false)
	@Getter @Setter @ToString.Include
	private String name;
	
	@Embedded
	// If I want the Address Fields to be directly present in Student table instead of Address as a separate Entity,
	// I can use @@Embedded Annotation here
	// and @Embedded Annotation in Address Entity.
	@Getter	@Setter
	private Address address;
	
	@OneToOne(fetch=FetchType.LAZY)
	// fetch=FetchType.LAZY usage - Only when any of the passport columns or passport details are 
	// fetched while fetching Student details, then passport details is retrieved.
	// If this attribute is not used (Or fetch=FetchType.EAGER is used) , then for example, if select * from student is fired, 
	// student details will be fetched and corresponding passport details will also be fetched.
	// If this attribute is used, only student details will be fetched.
	@Getter @Setter
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
	@Getter
	private List<Course> courses;
	
	public Student(String name)
	{
		this.name = name;
		this.courses = new ArrayList<>();
	}

	public void addCourse(Course course)
	// Actually this method is not needed.
	{
		this.courses.add(course);
	}
}
