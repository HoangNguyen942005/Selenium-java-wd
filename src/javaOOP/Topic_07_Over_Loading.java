package javaOOP;

public class Topic_07_Over_Loading {
    
	private int firstNumber;
	private int secondNumber;
	
	// Overloading : nạp chồng/ chồng hàm
	// Nếu cùng số lượng tham số phải khác kiểu dữ liệu
	// Nếu khác số lượng tham số thì ko quan tâm kiểu dữ liệu
	// 
	public void sumNumber() {
		System.out.println(this.firstNumber + this.secondNumber);
	}
	
	public void sumNumber(int firstNumber, int secondNumber) {
		System.out.println(firstNumber + secondNumber);
	}
	
	public void sumNumber(float firstNumber, float secondNumber) {
		System.out.println(firstNumber + secondNumber);
	}
	
	public void sumNumber(int firstNumber, float secondNumber) {
		System.out.println(firstNumber + secondNumber);
	}
	
	public static void main(String[] args) {
		

	}

}
