package com.junit_implementation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Math_Test {
	
	private Math math;

//	@Test														// @Test annotation denotes this method contains a unit test.
//	void test() {												// This is a default method when we create a Junit Test Case.
//		fail("Not yet implemented");							// When fail() is called, Red bar (Failure) is shown. If we comment this line and run this
//																// method, then Green bar (Success) is shown.
//																// This is because, according to Junit Framework, Absence of failure is success.
//	}

	@BeforeAll
	// This is a Class level method. So the methods under this annotation has to be Static.
	// The code inside this method will execute before all @Test methods are executed.
	static void beforeAll() {
		System.out.println("BeforeAll");
	}

	@AfterAll
	// This is a Class level method. So the methods under this annotation has to be Static.
	// The code inside this method will execute after all @Test methods are executed.
	static void afterAll() {
		System.out.println("AfterAll");
	}

	@BeforeEach
	// This annotation is used to execute something before every @Test method.
	void setUp() {
		System.out.println("BeforeEach");
		math = new Math();						// Creating the object of the class where the method to be tested is present.
	}

	@AfterEach
	// This annotation is used to execute something after every @Test method.
	void tearDown() {
		System.out.println("AfterEach");
	}

	// There's no order of executing @Test methods.
	// If there are 3 methods for example, it can execute in any order. (Like 2nd, 1st, 3rd).
	
	@Test
	public void sum_with3numbers()
	{
		System.out.println("Test1");
		int result = math.sum(new int[] {1,2,3});					// Calling the method and storing the result in a variable.
		assertEquals(6, result); 							// assertEquals(expected, actual) is one of the default methods in Junit.
	}
	
	@Test
	public void sum_with1number()
	{
		System.out.println("Test2");
//		int result = math.sum(new int[] {3});
//		assertEquals(3, result);
		assertEquals(3, math.sum(new int[] {3}));	// Shortcut for inline is (alt + shift + i).
															// We have to keep the cursor on result and do it.
															// Once we do it, the above 2 lines of code will change to a single line.
	}
}