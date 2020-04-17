package com.junit_implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;	// This is a Static Import because instead of using Assert.assertTrue, we can use assertTrue directly.

import org.junit.jupiter.api.Test;

class Assert_Methods_Test_Implementation {

//	@Test														// @Test annotation denotes this method contains a unit test
//	void test() {												// This is a default method when we create a Junit Test Case
//		fail("Not yet implemented");					// When fail() is called, Red bar (Failure) is shown. If we comment this line and run this
//																	// method, then Green bar (Success) is shown.
//																	// This is because, according to Junit Framework, Absence of failure is success.
//	}
	
	@Test
	public void test()
	{
		assertEquals(1, 1);
		assertTrue(true);
		assertFalse(false);
//		Other methods :
//				assertNotNull()
//				assertNull()
//				assertArrayEquals(expected, Actual)
	}

}
