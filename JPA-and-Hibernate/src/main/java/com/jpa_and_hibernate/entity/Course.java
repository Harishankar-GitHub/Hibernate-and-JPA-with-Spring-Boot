package com.jpa_and_hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Course")
//The table in database is actually created from this Entity Class.
//@Table is used if database table name and this entity class name are different.
@NamedQueries
(
		value = {
					@NamedQuery(name = "query_get_all_courses", query = "select c from Course c"),
					@NamedQuery(name = "query_get_all_courses_join_fetch", query = "select c from Course c JOIN FETCH c.students s"),
					@NamedQuery(name = "query_get_100_Step_courses", query = "select c from Course c where name like '%100 Steps'")
				}
)

@Cacheable
// @Cacheable is used to store the data in Second Level Cache.
// Where can we put @Cacheable ? If a particular data is accessed frequently. And if that data doesn't change frequently.
// We are putting @Cacheable annotation here because, we are considering the Course details doesn't change frequently.
// @Cacheable END

@SQLDelete(sql="update Course set is_deleted = true where id = ?")
// @SQLDelete usage -> While deleting a record, instead of deleting the record permanently,
// the isDeleted column in that record in table is updated with true.
// So while selecting, the deleted records (which is still present in table) should not be retrieved.
// That's why @Where Annotation is used.
// So the select query that is fired will be like -> select * from table where is_deleted = 0; (0 represents false in query)
// NOTE : This @Where Annotation won't be applicable on Native Queries.
// So in Native Queries, we have to manually add the condition like -> select * from table where is_deleted = 0; (0 represents false in query)
@Where(clause="is_deleted = false")
// The above concept (@SQLDelete Annotation & @Where Annotation) is known as Hibernate Soft Deletes.
// What is Hard Delete ? -> If a record is deleted permanently from table -> Normal Delete query.

@NoArgsConstructor(access = AccessLevel.PROTECTED)
// In Hibernate, if we use Parameterized Constructor, Java wouldn't provide a No Argument Constructor.
// To overcome that, we are defining a No Argument Constructor.
// And in Hibernate, No Argument Constructor is needed.
@ToString(onlyExplicitlyIncluded = true)
public class Course {
	
	private static final Logger logger = LoggerFactory.getLogger(Course.class);
	// Logger is made here Static because in Entity class, all the fields will be mapped to tables in database.
	// That's why instead of private Logger logger -> private static Logger logger is used.
	
	@Id						// Used to define this variable as the primary key of the table.
	@GeneratedValue			// Used to Auto Generate values of this variable.
	@Getter @ToString.Include
	private Long id;

	
	@Column(name="name", nullable = false)
	@Getter @Setter @ToString.Include
	private String name;
	// @Column is used if the column in table and the corresponding field or variable in this entity class are different.
	// By putting nullable = false, while creating the table, it will create the column as not null.
	// There are many more attributes like nullable, insertable, updatable, length, unique etc.
	// Do ctrl + click on @Column to see in detail.
	
	
	@OneToMany(mappedBy="course")
	// In @OneToMany, fetchType is LAZY by default.
	// Anything to Many (@OneToMany, @ManyToMany) - fetchType is LAZY by default.
	@Getter private final List<Review> reviews  = new ArrayList<>();
	// private List<Review> reviews; - Should also work.
	
	
	@ManyToMany(mappedBy="courses")
	// Each Course can have many Students enrolled.
	// In @ManyToMany, Any side can be the owning side. In this case, we have considered Student as owning side.
	// So, mappedBy is put in Course.
	@JsonIgnore // Usage - we can ignore a specific field from being returned to the Json Response.
	@Getter private final List<Student> students = new ArrayList<>();
	
	
	@UpdateTimestamp	// It is a Hibernate Annotation. Used to store the last updated timestamp of the row.
	private LocalDateTime lastUpdatedDate;
	@CreationTimestamp	// It is a Hibernate Annotation. Used to store the timestamp of the row when it is created for the 1st time.
	private LocalDateTime createdDate;
	
	private boolean isDeleted;
	
	@PreRemove
	// Once record is deleted, an update query is fired to database to set isDeleted = true.
	// But Entity doesn't know about it.
	// @PreRemove Annotation is used to do any operation before an entity is deleted.
	// So, once a record is deleted, we are setting isDeleted = true in the below method.
	// Without setting the value to the entity also it will work.
	// But @PreRemove is a best practice to ensure that the entity matches the state in the database.
	private void preRemove()
	{
		logger.info("Setting isDeleted to True");
		this.isDeleted = true;
	}
	
	public Course(String name)
	{
		this.name = name;
	}
	
	public void addReview(Review review)
	// We don't want others to set reviews.
	// That's why writing addReview() & removeReview().
	// Actually this method is not needed. Setting Course to review is enough. Even if addReview is used, doesn't matter.
	{
		this.reviews.add(review);
	}
	
	public void removeReview(Review review)
	{
		this.reviews.remove(review);
	}

	public void addStudents(Student student) {
	// Actually this method is not needed. Even if addStudents is used, doesn't matter.
		this.students.add(student);
	}
}
