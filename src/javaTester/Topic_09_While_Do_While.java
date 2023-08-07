package javaTester;

// Class
public class Topic_09_While_Do_While {
     
	// Function
	public static void main(String[] args) {
		int i = 0;
		
		// Block code
		for (i = 0; i < 5; i++) {
			System.out.println("For: " + i);

		}
		
		// i = 5 ko thỏa mãn điều kiện của While (Kiểm tra trước rồi mới chạy)
		while (i < 5) {
			System.out.println("While: " + i);
			i++;
		}

		// Chạy ít nhất 1 lần rồi mới kiếm tra điều kiện
		do {
			System.out.println("Do - While: " + i);
			i++;
		} while (i < 5);
	}
}
