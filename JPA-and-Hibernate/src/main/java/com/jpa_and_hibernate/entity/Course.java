package com.jpa_and_hibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Override
	public String toString() {
		return "Course [Name=" + name + "]";
	}
	
}
