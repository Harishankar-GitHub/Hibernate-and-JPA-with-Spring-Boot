package com.jpa_and_hibernate.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
// If I want the Address Fields to be directly present in Student table instead of Address as a separate Entity,
// I can use @Embeddable Annotation here
// and @Embedded Annotation in Student Entity.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// Default Constructor is not only needed for Entity classes, but also needed for @Embeddable Class.
@AllArgsConstructor
@ToString
public class Address {
	private String line1;
	private String line2;
	private String city;
}
