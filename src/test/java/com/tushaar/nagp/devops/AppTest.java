package com.tushaar.nagp.devops;

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;
import junit.framework.TestCase;

public class AppTest extends TestCase 
{
	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}
	
	public String getMessage(String message) {
		if(message.length() == 0)
			return "Empty message";
		return message;
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
	
	@Test
	public void testMessage() {
		String result = getMessage("");
		assertEquals(result, "Empty message");
	}
	
	@Test
	public void testMessage1() {
		String result = getMessage("Hello");
		assertEquals(result, "Hello");
	}
	
	@Test
	public void testMessage2() {
		String result = getMessage("Hello");
		assertNotEquals(result, "Empty message");
	}
}
