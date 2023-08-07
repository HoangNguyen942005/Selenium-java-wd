package javaTester;

import org.testng.annotations.Test;

public class Topic_11_String_Exercise {
	String courseName = "Automation Testing 345 Tutorials Online 789";

	// @Test
	public void TC_01() {
		char courseNameArr[] = courseName.toCharArray();
		int countUpper = 0;
		int countLowser = 0;
		int countNumber = 0;
		for (char character : courseNameArr) {
			// Uppercase
			if (character <= 'Z' && character >= 'A') {
				countUpper++;
			}

			// Lowsercase
			if (character <= 'z' && character >= 'a') {
				countLowser++;
			}

			// Number
			if (character <= '9' && character >= '0') {
				countNumber++;
			}
		}
		System.out.println("Sum of Uppercase = " + countUpper);
		System.out.println("Sum of Lowsercase = " + countLowser);
		System.out.println("Sum of Number = " + countNumber);

	}

	// @Test
	public void TC_02() {
		char courseNameArr[] = courseName.toCharArray();
		int count = 0;
		for (char c : courseNameArr) {
			if (c == 'o') {
				count++;
			}
		}
		System.out.println(count);
	}

	//@Test
	public void TC_03() {
		String courseNameString = courseName.toString();
		// Kiểm tra chuỗi có chứa từ "Testing" hay không
		if (courseNameString.contains("Testing")) {
			System.out.println("Chuỗi có chứa từ Testing");
		}
	}
	
	//@Test
	public void TC_04() {
		String courseNameString = courseName.toString();
		// Kiểm tra chuỗi có bắt đầu bằng "Automation" hay không
		if (courseNameString.startsWith("Automation")) {
			System.out.println("Chuỗi bắt đầu từ Automation");
		}
	}
	
	//@Test
	public void TC_05() {
		String courseNameString = courseName.toString();
		// Kiểm tra chuỗi có bắt đầu bằng "Online" hay không
		if (courseNameString.endsWith("Online")) {
			System.out.println("Chuỗi kết thúc = Online");
		}
		System.out.println("Chuỗi không kết thúc = Online");
	}
	
	//@Test
	public void TC_06() {
		String courseNameString = courseName.toString();
		System.out.println("Vị trí của Tutorials là : " + courseNameString.indexOf("Tutorials"));
	}
	
	//@Test
	public void TC_07() {
		String replaceString = courseName.replace("Online","Offline");
		System.out.println(replaceString);
	}
	
	@Test
	public void TC_08() {
		char courseNameArr[] = courseName.toCharArray();
		int countNumber = 0;
		
		for (char character : courseNameArr) {

			// Number
			if (character <= '9' && character >= '0') {
				countNumber++;
			}
		}
		System.out.println("Sum of Number = " + countNumber);
	}
}
