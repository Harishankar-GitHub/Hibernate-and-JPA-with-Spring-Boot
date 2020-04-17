package com.junit_implementation;

public class Math {
	
	int sum(int[] numbers)
	{
		int sum = 0;
		for (int i : numbers)
		{
			sum += i;
		}
		return sum;
	}

}
