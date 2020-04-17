package com.jdbctojpa.JDBC_to_JPA.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jdbctojpa.JDBC_to_JPA.Entity.Person;

@Repository
public class Person_JDBC_DAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person>
	{
		// Creating this inner class to implement the concept of
		// Custom RowMapper.
		// We have to implement RowMapper Interface and implement
		// its methods.

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			// In this method, we can customize how 1 row in ResultSet can be mapped
			// with a column in the class where we are going to map.
			// This concept can be used if the columns in table are not matching to the
			// variables in class.
			// In this case columns in Person Table & Person Class are same. So no issues. 
			// rowNum is number of rows. (Usually not used that much)
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}
		
	}
	
	public List<Person> findAll()			// To fetch all records from table
	{
		return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
		// new BeanPropertyRowMapper is a default RowMapper which maps the query result to the Person Class.
		// new PersonRowMapper() can also be used. It is a Custom RowMapper defined above.
		// We are using jdbcTemplate.query to fetch more than 1 object.
	}
	
	public Person findById(int id)		// To fetch records for a particular Id
	{
		return jdbcTemplate.queryForObject("select * from person where id = ?", new Object[]  {id},
				new BeanPropertyRowMapper<Person>(Person.class));
		// We are using jdbcTemplate.queryForObject to fetch 1 object.
		// We are passing value of id to the query using new Object[] {id} 
		// because there might be more than 1 arguments also.
	}
	
	public List<Person> findByName(String name)		// To fetch records with specific name
	{
		return jdbcTemplate.query("select * from person where name = ?", new Object[] {name},
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public List<Person> findByNameAndLocation(String name, String location)		// To fetch records with specific name and location
	{
		return jdbcTemplate.query("select * from person where name = ? and location = ?", new Object[] {name, location},
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public List<Person> findByLocation(String location)		// To fetch records with specific location
	{
		return jdbcTemplate.query("select * from person where location = ?", new Object[] {location},
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public int deleteById(int id)		// To delete records for a particular Id
	{
		return jdbcTemplate.update("delete from person where id = ?", new Object[]  { id });
		// For insert, update, delete i.e. for update operation, we are using jdbcTemplate.update method.
		// jdbcTemplate.update method returns an integer.
		// RowMapper is not needed here.
	}
	
	public int insert(Person person)		// To insert a new record
	{
		return jdbcTemplate.update("insert into person (id, name, location, birth_date) "
				+  "values (?, ?, ?, ?)", 
				new Object[]
						{
								person.getId(), person.getName(), person.getLocation(),
								new Timestamp(person.getBirthDate().getTime())
						}
				);
	}
	
	public int update(Person person)		// To update a record
	{
		return jdbcTemplate.update("update person set name = ?, location = ?, birth_date = ? "
				+ "where id = ? ", 
				new Object[]
						{
								person.getName(), person.getLocation(),
								new Timestamp(person.getBirthDate().getTime()),
								person.getId()
						}
				);
	}

}
