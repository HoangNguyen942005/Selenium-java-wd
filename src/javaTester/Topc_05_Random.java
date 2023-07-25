package javaTester;

import java.util.Random;

public class Topc_05_Random {

	public static void main(String[] args) {
		// utilities = tiện ích
		// tạo giá trị random dùng hàm java
		// Data Type : Class/ Interface/ Collection/ String/ Float/...
		Random rand = new Random();
	  System.out.println(rand.nextInt());
	  // tạo random cho email
	  System.out.println("automation" + rand.nextInt(99999) + "gmail.com");

	}

}
