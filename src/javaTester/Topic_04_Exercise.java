package javaTester;

import java.util.Iterator;

import org.testng.annotations.Test;

public class Topic_04_Exercise {
	
	@Test
	public void swapNumber() {
		
		int a = 5;
		int b = 6;
		
		a = a + b;    // a = 11
		b = a - b;    // b = 11 - 6 = 5
		a = a - b;    // a = 11 - 5 = 6
		
		System.out.println(a);
		System.out.println(b);
	}
}

