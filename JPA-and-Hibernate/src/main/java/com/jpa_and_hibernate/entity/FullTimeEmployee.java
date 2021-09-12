package com.jpa_and_hibernate.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FullTimeEmployee extends Employee {

	private BigDecimal salary;
	
	public FullTimeEmployee(String name, BigDecimal salary)
	{
		super(name);
		this.salary = salary;
	}
}
