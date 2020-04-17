package com.jpa_and_hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;	
	
	@OneToOne(fetch=FetchType.LAZY)
	// fetch=FetchType.LAZY usage - Only when any of the passport columns or passport details are 
	// fetched while fetching Student details, then passport details is retrieved.
	// If this attribute is not used (Or fetch=FetchType.EAGER is used) , then for example, if select * from student is fired, 
	// student details will be fetched and corresponding passport details will also be fetched.
	// If this attribute is used, only student details will be fetched.
	private Passport passport;
	
	
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
	public Long getId() {
		return id;
	}
	
	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
