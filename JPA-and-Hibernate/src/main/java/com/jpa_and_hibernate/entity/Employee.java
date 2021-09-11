package com.jpa_and_hibernate.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
// SINGLE_TYPE Annotation code & Explanation - START ------------------------------------------------------------------------------------
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// @Inheritance annotation is used to define Inheritance Relationships.
// Even if we don't specify InheritanceType.SINGLE_TABLE, the default strategy is SINGLE_TABLE only.
// Usage of SINGLE_TABLE - All Sub Classes i.e., Employee, PartTimeEmployee & FullTimeEmployee data will be stored in 1 table (Employee).
// When we run this, in Employee table will have additional column - DTYPE (Distinguished Type)
// If we want to change the default name, below is the annotation for that.
@DiscriminatorColumn(name="EmployeeType")
//SINGLE_TYPE Annotation code & Explanation - END ------------------------------------------------------------------------------------------


//TABLE_PER_CLASS Annotation code & Explanation - START ---------------------------------------------------------------------------------
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//// Usage of TABLE_PER_CLASS - Each Concrete Class will have its own table.
//// In this case, PartTimeEmployee will be a table & FullTimeEmployee will be a table.
//// Employee Class is Abstract. If it is not abstract, then an Employee table will also be created which is of no use.
//// While retrieving, UNION Query is fired.
//TABLE_PER_CLASS Annotation code & Explanation - END -------------------------------------------------------------------------------------


//JOINED Annotation code & Explanation - START ------------------------------------------------------------------------------------------------
//@Inheritance(strategy=InheritanceType.JOINED)
//// Usage of JOINED - All Sub Classes including Parent Class will have tables created.
//// Common columns will be present in Parent Class table.
//// Other columns will be present in Sub Class Tables respectively.
////While retrieving, JOIN Query is fired.
//JOINED Annotation code & Explanation - END ----------------------------------------------------------------------------------------------------

//@MappedSuperclass Annotation code & Explanation - START -----------------------------------------------------------------------------------
//@MappedSuperclass
//// There's no inheritance hierarchy at all.
//// It is just defining some stuff in this class which is common between the sub-classes.
//// If @MappedSuperclass is used, then @Entity cannot be used. i.e., Employee Class cannot be used as an Entity.
//@MappedSuperclass Annotation code & Explanation - END -----------------------------------------------------------------------------------
public abstract class Employee {
	// We have made Employee Class Abstract because, we don't want anybody to create object for Employee Class.
	
	@Id						// Used to define this variable as the primary key of the table
	@GeneratedValue			// Used to Auto Generate values of this variable
	private Long id;

	@Column(name="name", nullable = false)			
	private String name;
	
	protected Employee()
	{
		// In Hibernate, if we use Parameterized Constructor, Java would't provide a No Argument Constructor.
		// To overcome that, we are defining a No Argument Constructor.
		// And in Hibernate, No Argument Constructor is needed.
	}
	
	public Employee(String name)
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
		return "Employee [id=" + id + ", name=" + name + "]";
	}
}
