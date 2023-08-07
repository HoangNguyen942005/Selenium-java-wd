package javaException;

public class TryCatchException {

	public static void main(String[] args) {
		int number = 10;

		try {
			// Đúng : chạy hết đoạn code trong try và ko qua catch
			// Sai : Gặp exception => chạy qua catch

			number = number / 0;

		} catch (Exception e) {
			//e.printStackTrace(); // in ra lỗi sai 
			System.out.println("Không thể chia cho không");
		}

		System.out.println(number);

		String[] browserName = { "Chrome", "Firefox", "Safari" };

		try {
			browserName[0] = "Edge Chromium";
			browserName[3] = "IE";
		} catch (Exception e) {
			System.out.println("Browser Name không tồn tại IE");
		}
		
		for (String browser : browserName) {
			System.out.println(browser);
		}
	}

}
