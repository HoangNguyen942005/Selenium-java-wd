package javaTester;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Topic_08_For_Each {
     WebDriver driver;
	@Test
	public void TC_01_For() {
		
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
		}
		
		// Vế 1: biến tạm dùng để tăng giá trị lên sau mỗi lần duyệt
		// Dùng để so sánh vs tổng giá trị sau mỗi lần duyệt
		// Vế 2: So sánh vs tổng
		// Vế 3: Tăng i lên 1 đơn vị sau khi chạy vào thân vòng for
		
		// Lần 1 :
		// i = 0
		// 0 < 5 : đúng
		// In 0 ra
		// i++:  tăng i lên 1 đơn vị (i=1)
		
		 // Lần 2 :
		// i = 1
		// 1 < 5 : đúng
		// In 1 ra
		// i++:  tăng i lên 1 đơn vị (i=2)
		
		// Lần 3 :
		// i = 2
		// 2 < 5 : đúng
		// In 2 ra
		// i++:  tăng i lên 1 đơn vị (i=3)
		
		// Lần 6 :
		// i = 5
		// 5 < 5 : sai
		// Không được vào thân for, thoát khỏi
		
		// Array
		String[] cityName = { "Ha Noi", "Ho Chi Minh", "Da Nang", "Can Tho" }; // kích thước = 4
		
		// Array/ List/ Set/ Queue (index)
		// 0
		
		// For kết hợp if : thỏa mãn điều kiện mới action
		// biến đếm - dùng điều kiện để filter
		for (int i = 0; i < cityName.length; i++) { // duyệt qua vòng lặp
			if (cityName[i].equals("Da Nang")) {
				// Action
				System.out.println("Do action!");
				break; // tìm thấy giá trị r thì thoát khỏi vòng lặp, ko quan tâm giá trị sau
			}
		}
		
		// Dùng để chạy qua hết tất cả giá trị
		for (String city : cityName) {
			System.out.println(city);
		}
	   // For - each dùng cho : 
	   // Java Collection
	   // Class: ArrayList/ LinkedList/ Vector/...
	   // Interface : List/ Set/ Queue
		
		List<String> cityAddress = new ArrayList<String>();
		System.out.println(cityAddress.size());  // in khi chưa add tỉnh mới
		
		// Compile (Coding)
		cityAddress.add("Quang Ninh");
		cityAddress.add("Quang Nam");
		cityAddress.add("Quang Binh");
		System.out.println(cityAddress.size());  // in khi thêm các tỉnh mới vào
		
		// Runtime (Runing)
		for (String city : cityName) {
			cityAddress.add(city);		// chạy qua 3 cityAddress mới thêm, và add thêm các city khai báo ở trên
		}
	    System.out.println(cityAddress.size()); // in khi chạy qua tất cả các tỉnh
	    
	    // in ra hết tất cả các tỉnh đã có và vừa add
	    for (String cityAdd : cityAddress) {
			System.out.println(cityAdd);
		}
	    
	    // Java Generic
	    List<WebElement> links = driver.findElements(By.xpath(""));
	    
	    // Xử lý dữ liệu/ get text/ value/ css/ attribute
	    for (WebElement link : links) {
			// Không dùng thân for - each để : 
	    	// Chuyển page
	    	// Refresh DOM/ HTML
	    	// Ko còn tồn tại
	    	// Fail -> StableElemtExeption
		}
  }
}
