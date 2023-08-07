package javaTester;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_11_String {

	public static void main(String[] args) {
//		String s1 = "Cat"; // String là tập hợp các ký tự
//		String s2 = s1;
//		String s3 = new String("Cat");
//
//		System.out.println(s1 == s2);   // == dùng cho kiểu dữ liệu nguyên thủy
//		System.out.println(s1 == s3);  // s3 đang khai báo sang 1 vùng nhớ mới hoàn toàn => false
//		System.out.println(s2 == s3);  // tương tự  => false
//		System.out.println(s2.equals(s3));  // true , equals dùng cho kiểu dữ liệu tham chiếu

		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		String schoolName = "Automation Testing";
		String courseName = "AUTOMATION TESTING";
		String schoolAddress = "Ho Chi Minh";

		System.out.println("Độ dài = " + schoolName.length());
		System.out.println("Lấy ra 1 ký tự = " + schoolName.charAt(1));
		System.out.println("Nối 2 chuỗi =  " + schoolName.concat(schoolAddress));
		System.out.println("Nối 2 chuỗi =  " + schoolName + schoolAddress);

		// Tuyệt đối
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tuyệt đối = " + schoolName.equals("Automation Testing"));

		// Multi browser
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tương đối = " + schoolName.equalsIgnoreCase(courseName));

		// startsWith/ endsWith/ contains
		System.out.println("Có bắt đầu bằng 1 ký tự/ chuỗi ký tự = " + schoolName.startsWith("A"));
		System.out.println("Có bắt đầu bằng 1 ký tự/ chuỗi ký tự = " + schoolName.startsWith("Automation"));

		System.out.println("Có bắt đầu bằng 1 ký tự/ chuỗi ký tự = " + schoolName.contains("Automation"));
		System.out.println("Có bắt đầu bằng 1 ký tự/ chuỗi ký tự = " + schoolName.contains("Au"));

		System.out.println("Có kết thúc bằng 1 ký tự/ chuỗi ký tự = " + schoolName.endsWith("Testing"));
		System.out.println("Có kết thúc bằng 1 ký tự/ chuỗi ký tự = " + schoolName.endsWith("Automation"));

		System.out.println("Vị trí của  1 ký tự/ chuỗi ký tự = " + schoolName.indexOf("Automation"));
		System.out.println("Vị trí của  1 ký tự/ chuỗi ký tự = " + schoolName.indexOf("u"));
		System.out.println("Vị trí của  1 ký tự/ chuỗi ký tự = " + schoolName.indexOf("Testing"));

		System.out.println("Tách  1 ký tự/ chuỗi ký tự = " + schoolName.substring(11));
		System.out.println("Tách  1 ký tự/ chuỗi ký tự = " + schoolName.substring(7, 12));

		// Split : tách chuỗi thành 1 cái mảng dựa vào kí tự/ chuỗi kí tự
		String result = "Viewing 48 of 132 results";
		String results[] = result.split(" ");

		System.out.println(results[1]); // in ra số 48

		// Replace : thay thế 1 kí tự/ chuỗi kí tự bằng kí tự/ chuỗi khác
		String productPrice = "$100.00";
		productPrice.replace("$", "");
		System.out.println(productPrice); // in ra 100.00

//		float productPriceF = Float.parseFloat(productPrice);
//		System.out.println(productPriceF);  // in ra 100.0
//		
//		productPrice = String.valueOf(productPriceF);
//		System.out.println(productPrice);

//		String osName = System.getProperty("os.name");
//		System.out.println(osName);
//		// Handle multiple OS : MAC/ Window (Actions - keys - Ctrl/ Command)
//		if (osName.toLowerCase().contains("window")) {
//			Keys key = Keys.CONTROL;
//		} else {
//			Keys key = Keys.COMMAND;
//		}

		// String driverInstanceName = driver.toString();
		// System.out.println(driverInstanceName);
		// Close browser/ driver
		// if(driverInstanceName.contains("internetexplorer")) {
		// sleep cứng thêm 5s sau mới sự kiện chuyển page

		String helloWorld = " \n \t Hello World!     ";
		System.out.println(helloWorld);
		System.out.println(helloWorld.trim());
		
		helloWorld = " ";
		System.out.println("Empty = " + helloWorld.isEmpty());   // fale, ko có ký tự nào mới true
		System.out.println("Blank = " + helloWorld.isBlank());   // true 
		
		// Dynamic locator
		// Đại diện cho 1 chuỗi %s
		// %b %t %d
		String dynamicButtonxpath = "//button[@id='%s']";
		System.out.println("Click to Login Button = " + dynamicButtonxpath.format(dynamicButtonxpath, "login"));
		System.out.println("Click to Search Button = " + dynamicButtonxpath.format(dynamicButtonxpath, "search"));
		System.out.println("Click to Register Button = " + dynamicButtonxpath.format(dynamicButtonxpath, "register"));
		
		
	}
}
