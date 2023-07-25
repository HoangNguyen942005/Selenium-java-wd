package javaTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Topic_02_Data_Type_2 {
    // Primitive type/ Value type : Kiểu dữ liệu nguyên thủy 
	
	// Số nguyên : số không có phần thập phân
	byte bNumber;
	
	short sNumber;
	
	int iNumber = 65000; // **
	
	long lNumber = 65000; //**
	
	// Số thực : số có phần thập phân
	float fNumber = 99.9f;  //**
	
	double dNumber = 15.5d;
	
	char cChar;
	
	boolean bStatus;  // **--> trả về True hoặc False 
	
	// Reference type : kiểu dữ liệu tham chiếu
	
	// String : chuỗi
	String address = "Ha Noi";
		
	// Array : mảng
	String[] studentAddress = {address, "Da Nang", "Sài Gòn"};
	Integer[] studentNumber = {15, 20, 25};
	
	// Class : lớp
	Topic_02_Data_Type topic;
	
	// Interface
	WebDriver driver;
	
	// Object
	Object aObject;
	
	// Collection
	// List/ Set/ Queue/ Map
	List<WebElement> homePageLinks = driver.findElements(By.cssSelector("")); // lưu trung được 
	Set<String> allWindows = driver.getWindowHandles();  // ko lưu trùng
	List<String> productName = new ArrayList<String>();
	
	public static void main(String[] args) {
	
		
		
	}
}
