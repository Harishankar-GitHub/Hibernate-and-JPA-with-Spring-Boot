package com.jpa_and_hibernate.repository;

import com.jpa_and_hibernate.entity.Employee;
import com.jpa_and_hibernate.entity.FullTimeEmployee;
import com.jpa_and_hibernate.entity.PartTimeEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {
	
	@Autowired														// Repository should be able to interact with Database.
	EntityManager entityManager;									// That's why auto wiring EntityManager.
	
	public void insert(Employee e)
	{
		entityManager.persist(e);
	}
	
	public List<Employee> retrieveAllEmployees()					// If @Entity is put on Employee Class, this method will work.
	{
		return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
	}
	
	public List<PartTimeEmployee> retrieveAllPartTimeEmployees()
	{
		return entityManager.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}
	
	public List<FullTimeEmployee> retrieveAllFullTimeEmployees()
	{
		return entityManager.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}
}
