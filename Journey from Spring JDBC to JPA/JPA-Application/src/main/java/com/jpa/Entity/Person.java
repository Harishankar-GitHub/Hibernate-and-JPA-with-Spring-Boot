package com.jpa.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor        			// No Argument constructor is required in Hibernate

@Entity                 			// Annotation to map a Class to a Table
@Table(name="person")				// Annotation used if Class name & Table name are different
@NamedQuery(name="find_all_persons", query="select p from Person p")
public class Person {
	
	@Id								// Used to define this variable as the primary key of the table
	@GeneratedValue					// Used to Auto-Generate values of this variable
	private int id;
	@Column(name="name")			// Annotation used if Variable name & Column name is different
	private String name;
	private String location;
	private Date birthDate;
	
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
}
