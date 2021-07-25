package com.jdbc.Dao;

import com.jdbc.Entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Repository
public class Person_DAO {
	
	private final JdbcTemplate jdbcTemplate;

	private static class PersonRowMapper implements RowMapper<Person>
	{
		// Creating this inner class to implement the concept of
		// Custom RowMapper.
		// We have to implement RowMapper Interface and implement
		// its methods.

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

			// In this method, we can customize how rows in ResultSet can be mapped
			// with a column in the class or DTO.
			// This concept can be used if the columns in the table are not matching with the
			// fields in the class or DTO.
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
	
	public List<Person> findAll()					// To fetch all records from table
	{
//		return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
//		 new BeanPropertyRowMapper is a default RowMapper which maps the query result to the Person Class.
//		 new PersonRowMapper() can also be used. It is a Custom RowMapper defined above.
//		 We are using jdbcTemplate.query to fetch more than 1 object.
	}
	
	public Person findById(int id)					// To fetch records for a particular Id
	{
		return jdbcTemplate.queryForObject("select * from person where id = ?", new BeanPropertyRowMapper<>(Person.class), id);
//		 We are using jdbcTemplate.queryForObject to fetch 1 object.
//		 We are passing value of id to the query using new Object[] {id}
//		 because there might be more than 1 arguments also.
	}
	
	public List<Person> findByName(String name)		// To fetch records with specific name
	{
		return jdbcTemplate.query("select * from person where name = ?", new BeanPropertyRowMapper<>(Person.class), name);
	}
	
	public List<Person> findByNameAndLocation(String name, String location)		// To fetch records with specific name and location
	{
		return jdbcTemplate.query("select * from person where name = ? and location = ?", new BeanPropertyRowMapper<>(Person.class), name, location);
	}
	
	public List<Person> findByLocation(String location)		// To fetch records with specific location
	{
		return jdbcTemplate.query("select * from person where location = ?", new BeanPropertyRowMapper<>(Person.class), location);
	}
	
	public int deleteById(int id)		// To delete records for a particular Id
	{
		return jdbcTemplate.update("delete from person where id = ?", id);
//		 For insert, update, delete i.e. for update operation, we are using jdbcTemplate.update method.
//		 jdbcTemplate.update method returns an integer.
//		 RowMapper is not needed here.
	}
	
	public int insert(Person person)		// To insert a new record
	{
		return jdbcTemplate.update("insert into person (id, name, location, birth_date) values (?, ?, ?, ?)",
				person.getId(),
				person.getName(),
				person.getLocation(),
				new Timestamp(person.getBirthDate().getTime()));
	}
	
	public int update(Person person)		// To update a record
	{
		return jdbcTemplate.update("update person set name = ?, location = ?, birth_date = ? where id = ? ",
				person.getName(),
				person.getLocation(),
				new Timestamp(person.getBirthDate().getTime()),
				person.getId());
	}
}
