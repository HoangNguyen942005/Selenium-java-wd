package javaOOP;
     // final class : ko cho kế thừa nhưng cho phép khởi tạo (new)
     // abstract class : cho kế thừa nhưng ko cho phép khởi tạo
     // public final (abstract) class .....
public class Topic_04_Non_Access_Modifier {
    // Static : Variable/ Method (Biến/ Hàm)
	// Biến static truy cập trực tiếp trong 1 hàm static
	// Dùng cho tất cả các instance/ object
	// Phạm vi cho toàn bộ system sử dụng nó
	// Có thể ghi đè được (Override)
	static String browserName = "Chrome";
	
	// Non static
	String severName = "Chrome";
	
	final String colorCar; // --> phải gán giá trị cho nó
	final String colorCars = "Red"; // tạo final thì là 1 hằng số, không thể thay đổi 
	
	public static void main(String[] args) {
		System.out.println(browserName);
		
		browserName = "Firefox"; // Override lại được static
		
		System.out.println(severName); // --> Non static nên ko truy cập vào được
		
		Topic_04_Non_Access_Modifier topic = new Topic_04_Non_Access_Modifier();
		System.out.println(topic.severName);
	
		topic.clickToElement("Button");
		
		senKeyToElement("Link");
	}
	// Non static
    public void clickToElement(String elementName) {
    	System.out.println(elementName);
    }
    
    // Static
    public static void senKeyToElement(String elementName) {
    	System.out.println(elementName);
    }
    
    // Final Method : ko cho phép ghi đè ở các class khác 
    public final void setCarName() {
    	
    }
}
