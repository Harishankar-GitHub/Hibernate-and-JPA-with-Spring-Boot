package com.jpa_and_hibernate.entity;

import javax.persistence.Embeddable;

@Embeddable
// If I want the Address Fields to be directly present in Student table instead of Address as a separate Entity,
// I can use @Embeddable Annotation here
// and @Embedded Annotation in Student Entity.
public class Address {
	
	private String line1;
	private String line2;
	private String city;
	
	protected Address()
	{
		// Default Constructor is not only needed for Entity classes, but also needed for @Embeddable Class.
	}
	
	public Address(String line1, String line2, String city) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [line1=" + line1 + ", line2=" + line2 + ", city=" + city + "]";
	}
	
}
