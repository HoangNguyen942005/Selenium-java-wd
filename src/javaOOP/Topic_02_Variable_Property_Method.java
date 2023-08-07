package javaOOP;

public class Topic_02_Variable_Property_Method {

	// Tạo biến (Variable)
	// Access Modifier (private, public, ...)
	// Tên biến (Variable Name)
	// Giá trị (variable Value)
	private String studentName = "Automation FC"; // Biến toàn cục (Global variable)

	// Access Modifier : default
	int studentID = 100056;

	// Static variable : dùng và gán lại được
	public static String studentAddress = "Ha Noi";  // dùng trong 1 hàm static
	
	// Dùng trong phạm vi class này (cho tất cả instance/ object dùng)
	private static String studentPhone = "12345";
	
	// Final variable : Không cho phép gán lại/ ko override lại
	final String country = "Viet Nam"; // cố định dữ liệu, không được phép thay đổi trong quá trình chạy
	
	// Static final variable : hằng số (Constant)
	// Nó ko được ghi đè
	// Có thể truy cập rộng rãi cho tất cả instance/ thread
	static final float PI_NUMBER = 3.142342f;
	
	// Hàm (Method) - static, thực thi chương trình từ các biến đã khai báo
	public static void main(String[] args) {
		String studentName = "Automation FC";  // lấy từ biến toàn cục ra khai báo để sử dụng
		if (studentName.startsWith("Automation")) {
			int number = 100;
		}
		studentAddress = "Da Nang";

	}

	// Contructor
	public Topic_02_Variable_Property_Method() {
		// Local variable - biến cục bộ : contructor
		// Nó sẽ ưu tiền hàm cục bộ trước
		// Nếu muốn truy cập vào hàm toàn cục, thì dùng this
		String studentName = "Automation FC";
		
		
	}

	// Hàm (Method) - non static
	public void display() {
		// Local Variable - biến cục bộ : hàm/ block code/ constructor
		String studentName = "Automation FC";
	}

}
