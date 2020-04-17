package com.junit_implementation;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Math_Test {
	
	Math math = new Math();							// Creating the object of the class where the method which has to be tested is present.

//	@Test														// @Test annotation denotes this method contains a unit test.
//	void test() {												// This is a default method when we create a Junit Test Case.
//		fail("Not yet implemented");					// When fail() is called, Red bar (Failure) is shown. If we comment this line and run this
//																	// method, then Green bar (Success) is shown.
//																	// This is because, according to Junit Framework, Absence of failure is success.
//	}
	
	@Before													// This annotation is used to execute something before every @Test method.
	public void before()
	{
		System.out.println("Before");
	}
	
	@After														// This annotation is used to execute something after every @Test method.
	public void after()
	{
		System.out.println("After");
	}
	
	@BeforeClass											// This is a Class level method. So the methods under this annotation has to be Static.
	public static void beforeClass()				// The code inside this method will execute before all @Test methods are executed.
	{
		System.out.println("BeforeClass");
	}
	
	@AfterClass												// This is a Class level method. So the methods under this annotation has to be Static.
	public static void afterClass()					// The code inside this method will execute after all @Test methods are executed.
	{
		System.out.println("AfterClass");
	}
	
	// There's no order of executing @Test methods.
	// If there're 3 methods for example, it can execute in any order. (Like 2nd, 1st, 3rd).
	
	@Test
	public void sum_with3numbers()
	{
		System.out.println("Test1");
		int result = math.sum(new int[] {1,2,3});		// Calling the method and storing the result in a variable.
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