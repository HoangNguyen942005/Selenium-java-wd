package javaTester;

import java.util.Iterator;

public class Topic_03_Operator {
	public static void main(String[] args) {
		int number = 10;
		
		number += 5;
		System.out.println(number);
		
		// 15/5 = 3
		System.out.println(number / 5);
		
		// 15%6 = 2 dư 3
		System.out.println(number % 6);
		
		System.out.println(number++);
		System.out.println(number--);
		
		// number++ : in ra number trước rồi + 1
		// ++ number : tăng number lên 1 trước rồi mới in ra
		
		for (int i = 0; i < 3; ++i) {
			System.out.println(i);
		}
	}
}

