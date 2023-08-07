package javaTester;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_06_Exercise {
	WebDriver driver;
	Scanner scanner = new Scanner(System.in);
	
	// Nhược điểm của if - else
	// Khó để đọc code (readable)
	// Ko check trùng lặp
	
	//@Test
	public void TC_01() {
		int number = scanner.nextInt(); // nhập số khi chạy TC
		
		if (number % 2 == 0) {
			System.out.println("Số: " + number + " là số chẵn");
		} else {
			System.out.println("Số: " + number + " là số lẻ");
		}
	}
	
	//@Test
	public void TC_02() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();
		
		if (numberA >= numberB) {
			System.out.println(numberA + " lớn hơn hoặc bằng " + numberB);
		} else {
			System.out.println(numberA + " nhỏ hơn " + numberB);
		}
	}
	
	//@Test
	public void TC_03() {
		String firstStudent = scanner.nextLine();
		String secondStudent = scanner.nextLine();
		
		if (firstStudent.equals(secondStudent)) {
			System.out.println("Hai sinh viên giống tên nhau");
		} else {
			System.out.println("Hai sinh viên khác tên nhau");
		}
	}
	
	//@Test
	public void TC_04() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();
		int numberC = scanner.nextInt();
		
		if (numberA > numberB && numberA > numberC) {
			System.out.println(numberA + " là số lớn nhất");
		} else if (numberB > numberC) {
			System.out.println(numberB + " là số lớn nhất");
		} else {
			System.out.println(numberC + " là số lớn nhất");
		}
	}
	
	//@Test
	public void TC_05() {
		int numberA = scanner.nextInt();
		
		if (10 < numberA && numberA < 100) {
			System.out.println(numberA + " nằm trong khoảng");
		} else {
			System.out.println(numberA + " nằm ngoài khoảng");
		} 
	}
	
	//@Test
	public void TC_06() {
		float studentPoint = scanner.nextFloat();
		
		if (studentPoint <=10 && studentPoint >=8.5) {
			System.out.println("Hệ số A");
		} else if (studentPoint < 8.5 && studentPoint >= 7.5) {
			System.out.println("Hệ số B");
		} else if (studentPoint < 7.5 && studentPoint >= 5) {
			System.out.println("Hệ số C");
		} else if (studentPoint < 5 && studentPoint >= 0) {
			System.out.println("Hệ số D");
		} else {
			System.out.println("Vui lòng nhập lại điểm");
		}
	}
	
	@Test
	public void TC_07() {
		int month = scanner.nextInt();
		
		// 1, 3, 5, 7, 8, 10, 12
		if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12 ) {
			System.out.println("Tháng này có 31 ngày");
		} else if (month==2) {
			System.out.println("Tháng này có 28 ngày");
		} else if(month==4 || month==6 || month==9 || month==11){
			System.out.println("Tháng này có 30 ngày");
		}
	}
}
