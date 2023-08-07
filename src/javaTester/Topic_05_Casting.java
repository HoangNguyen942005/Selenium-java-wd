package javaTester;

import java.util.Iterator;

import org.testng.annotations.Test;

public class Topic_05_Casting { // Ép kiểu trong Java
	
	public static void main(String[] args) {
		// Ngầm định = ko mất dữ liệu
		
//		byte bNumber = 126;
//		System.out.println(bNumber); 
//		
//		short sNumber = bNumber;
//		System.out.println(sNumber);
//		
//		int iNumber = sNumber;
//		System.out.println(iNumber);
//		
//		long lNumber = iNumber;
//		System.out.println(lNumber);
//		
//		float fNumber = lNumber;
//		System.out.println(fNumber);
//		
//		double dNumber = fNumber;
//		System.out.println(dNumber);
		
		// Tường minh (cast qua, vì từ lớn qua nhỏ khó mở rộng)
		
		double dNumber = 65432178912422d;
		System.out.println(dNumber);
		
		float fNumber = (float) dNumber;
		System.out.println(fNumber);
		
		long lNumber = (long) fNumber;
		System.out.println(lNumber);
		
		int iNumber = (int) lNumber;
		System.out.println(iNumber);
	}
}

