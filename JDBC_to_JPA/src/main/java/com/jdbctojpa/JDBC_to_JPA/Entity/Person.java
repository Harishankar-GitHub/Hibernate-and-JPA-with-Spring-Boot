package com.jdbctojpa.JDBC_to_JPA.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity							// Annotation to map a Class to a Table
//@Table(name="person")	// Annotation used if Class name & Table name are different
@NamedQuery(name="find_all_persons", query="select p from Person p")
public class Person {
	
	@Id								// Used to define this variable as the primary key of the table
	@GeneratedValue			// Used to Auto Generate values of this variable
	private int id;
	@Column(name="name")	// Annotation used if Variable name & Column name is different
	private String name;
	private String location;
	private Date birthDate;
	
	public Person()				// No Argument constructor required in Hibernate
	{
		// We write a default constructor because we have used BeanPropertyRowMapper in Person_JDBC_DAO class.
		// If this is not there, it will throw NoSuchMethod Exception.
		// If we have used parameterized constructor then Java would'nt give this default constructor.
		// That's the reason we have written this default constructor.
	}
	
	public Person(int id, String name, String location, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}
	
	public Person(String name, String location, Date birthDate) {
		// Writing this constructor because Id is Auto Generated
		super();
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "\n Person [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}
	
}
