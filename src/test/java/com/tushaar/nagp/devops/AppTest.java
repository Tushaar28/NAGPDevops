package com.tushaar.nagp.devops;

import org.junit.Test;

import junit.framework.TestCase;

public class AppTest extends TestCase 
{
	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	@Test
	public void testAdd() {
		int total = 8;
		int sum = add(4, 4);
		assertEquals(sum, total);
	}

	@Test
	public void testFailedAdd() {
		int total = 9;
		int sum = add(10, 5);
		assertNotSame(sum, total);
	}

	@Test
	public void testSub() {
		int total = 0;
		int sub = sub(4, 4);
		assertEquals(sub, total);
	}
}
