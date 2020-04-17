package com.jpa_and_hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {	
	
	@Id
	@GeneratedValue
	private Long id;
	private String rating;
	private String description;	
	
	protected Review()
	{
		// In Hibernate, if we use Parameterized Constructor, Java would't provide a No Argument Constructor.
		// To overcome that, we are defining a No Argument Constructor.
		// And in Hibernate, No Argument Constructor is needed.
	}

	public Review(String rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", description=" + description + "]";
	}
	
}
