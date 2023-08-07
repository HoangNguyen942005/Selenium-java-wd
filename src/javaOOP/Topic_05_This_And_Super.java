package javaOOP;

public class Topic_05_This_And_Super {
	private int firstNumber;
	private int secondNumber;

	// tạo hàm public khởi tạo (contructor)
	public Topic_05_This_And_Super(int firstNumber, int secondNumber) {
		firstNumber = firstNumber;
		secondNumber = secondNumber; // false
		// dùng this để truy cập đến biến global
		// this chỉ được dùng trong contructor
		// contructor luôn phải trùng tên class
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
	}

	public void sumNumber() {
		System.out.println(firstNumber + secondNumber);
	}
	
	public void showNumber() {
		this.sumNumber();
	}

	public static void main(String[] args) {
		Topic_05_This_And_Super topic = new Topic_05_This_And_Super(15, 7);
		topic.sumNumber();

	}
	
}
