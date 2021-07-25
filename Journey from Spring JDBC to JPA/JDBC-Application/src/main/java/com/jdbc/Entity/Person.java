package com.jdbc.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@AllArgsConstructor
public class Person
{
	private int id;
	private String name;
	private String location;
	private Date birthDate;
	
	public Person()			// No Argument constructor is required in Hibernate
	{
		// We write a default constructor because we have used BeanPropertyRowMapper in Person_DAO class.
		// If this is not there, it will throw NoSuchMethod Exception.
		// If we have used parameterized constructor then Java wouldn't give this default constructor.
		// That's the reason we have written this default constructor.
	}
}
