package com.jpa_and_hibernate.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartTimeEmployee extends Employee {

	private BigDecimal hourlyWage;
	
	public PartTimeEmployee(String name, BigDecimal hourlyWage)
	{
		super(name);
		this.hourlyWage = hourlyWage;
	}
}
