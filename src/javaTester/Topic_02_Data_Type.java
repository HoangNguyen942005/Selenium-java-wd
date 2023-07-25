package javaTester;

public class Topic_02_Data_Type {
    // Khai báo ở đây là biến Global 
	// Biến Global không cần khai báo dữ liệu vẫn có thể sử dụng được
	static int number;  // có giá trị mặc định = ...
	
	String address = "Ha Noi";
	
	public static void main(String[] args) {
		// Khai báo bên trong là biến local
		// Trong hàm chứa static, muốn gọi trực tiếp thì phải có static khai báo ở global
		// Biến local khi khai báo bắt buộc phải khởi tạo dữ liệu, ko có giá trị mặc định 
        System.out.println(number);
        
        // Muốn sử dụng address ở trên mà không dùng static thì phải khởi tạo
        // 1 giá trị mới cho nó
        Topic_02_Data_Type topic = new Topic_02_Data_Type();
        System.out.println(topic.address); // --> in được data theo topic đã khởi tạo
	}

}
