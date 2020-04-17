package com.jpa_and_hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Passport {	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String number;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy = "passport")
	// mappedBy usage - By using this, we can say Student has Passport.
	// If mappedBy is not used, Student has Passport and Passport has Student which is not proper mapping.
	// Same data will be stored in multiple places.  i.e., Same data will be stored in Student and Passport.
	// So now, we have used MappedBy. So owning side is Student. i.e., Student has Passport. So mappedBy is put on the other side. i.e., Passport.
	// NOTE : In Passport side, Student won't be created. Because, Passport is not owning side. Student is owning side. So Passport can be created from Student side.
	// Then why @OneToOne here because, we can navigate to Student from Passport. This is called Bidirectional Relationship.
	private Student student;
	
	protected Passport()
	{
		// In Hibernate, if we use Parameterized Constructor, Java would't provide a No Argument Constructor.
		// To overcome that, we are defining a No Argument Constructor.
		// And in Hibernate, No Argument Constructor is needed.
	}
	
	public Passport(String name)
	{
		this.number = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Passport [id=" + id + ", number=" + number + "]";
	}
	
}
