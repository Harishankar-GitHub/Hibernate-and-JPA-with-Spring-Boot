package com.jpa.Dao;

import com.jpa.Entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Transactional
public class Person_Repository {

	@PersistenceContext				// Connecting to the database
	private final EntityManager entityManager;

	public List<Person> findAll()
	{
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
		// This is the syntax for creating JPQL (Java Persistence Query Language) namedQuery.
		// find_all_persons is a name. The query for it is defined in Entity Class (Person.class).
	}
	
	public Person findById(int id)	// To fetch records for a particular Id
	{
		return entityManager.find(Person.class, id);
		// There are number of default methods in EntityManager.
		// entityManager.find(entityClass, primaryKey) is one among them.
	}
	
	public Person update(Person person)
	{
		return entityManager.merge(person);
		// To insert or update, entityManager.merge() method is used.
		// If Id is present in person object then it will update the record of that Id.
		// If Id is not present in person object then it will insert it as a new record.
	}
	
	public Person insert(Person person)
	{
		return entityManager.merge(person);
		// To insert or update, entityManager.merge() method is used.
		// If Id is present in person object then it will update the record of that Id.
		// If Id is not present in person object then it will insert it as a new record.
	}
	
	public void delete(int id)
	{
		Person person = findById(id);
		entityManager.remove(person);
		// entityManager.remove() doesn't return anything.
		// That is why return type is void.
	}
}
