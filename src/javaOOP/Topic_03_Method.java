package javaOOP;

public class Topic_03_Method {
    
	void showCarName() {
		System.out.println("Honda");
	}
	
	static void showCarColor() {
		System.out.println("Red");
	}
	
	public static void main(String[] args) {
		// Gọi vào trong 1 hàm static khác được
		showCarColor();
		
	//	showCarName(); // ko gọi được vì ko có static
		
		// Muốn gọi thì phải  qua instance/ object
		Topic_03_Method obj = new Topic_03_Method();
		obj.showCarName();

	}

}
