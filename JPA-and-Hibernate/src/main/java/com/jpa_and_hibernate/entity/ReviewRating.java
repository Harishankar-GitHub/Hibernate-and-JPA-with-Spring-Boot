package com.jpa_and_hibernate.entity;

public enum ReviewRating {
	
	ONE, TWO, THREE, FOUR, FIVE
	// In database, these values will be stored as Ordinal.
	// i.e., the Ordinal value of ONE is 1.
	//		   the Ordinal value of TWO is 2 and so on.
	// The Ordinal value is based on the Position.
	// ONE is not mapped to 1.
	// ONE is in 1st position. So Ordinal Value of ONE is 1.
	// If the values are like ABC, DEF, GHI etc.
	// The Ordinal Value of ABC -> 1, DEF -> 2, GHI -> 3.
	// If we don't want to use Ordinal Value & if we want to use the Actual values present in Enum,
	// then we can use a property in @Enumerated Annotation.
	// Property -> EnumType.STRING (This property is already used in Review Entity)
}
