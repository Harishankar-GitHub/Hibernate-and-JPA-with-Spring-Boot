package com.jpa_and_hibernate.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// In Hibernate, if we use Parameterized Constructor, Java wouldn't provide a No Argument Constructor.
// To overcome that, we are defining a No Argument Constructor.
// And in Hibernate, No Argument Constructor is needed.
@ToString(onlyExplicitlyIncluded = true)
public class Review {
	
	@Id
	@GeneratedValue
	@Getter @ToString.Include
	private Long id;
	
	@Enumerated(EnumType.STRING)
	// It is a JPA Annotation.
	// It is to say that, the value of this field comes from the Enum.
	// EnumType.STRING is used to save the Actual values present in Enum instead of Ordinal Values.
	@Getter @Setter @ToString.Include
	private ReviewRating rating;

	@Getter @Setter @ToString.Include
	private String description;
	
	@ManyToOne
	// In @ManyToOne, fetchType is EAGER by default.
	// Anything to One (@OneToOne, @ManyToOne) - fetchType is EAGER by default.
	@Getter @Setter
	private Course course;

	public Review(ReviewRating rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}
}
