package javaOOP;

public class SinhVien {

	private int masinhvien;
	private String hovaten;
	private float diemthuchanh;
	private float diemlythuyet;
	
//	protected SinhVien(int masinhvien, String hovaten, Float diemthuchanh, Float diemlythuyet) {
//		
//		this.masinhvien = masinhvien;
//		this.hovaten = hovaten;
//		this.diemthuchanh = diemthuchanh;
//		this.diemlythuyet = diemlythuyet;
//	}

	protected int getMasinhvien() {
		return masinhvien;
	}

	protected void setMasinhvien(int masinhvien) {
		this.masinhvien = masinhvien;
	}

	protected String getHovaten() {
		return hovaten;
	}

	protected void setHovaten(String hovaten) {
		this.hovaten = hovaten;
	}

	protected float getDiemthuchanh() {
		return diemthuchanh;
	}

	protected void setDiemthuchanh(float diemthuchanh) {
		this.diemthuchanh = diemthuchanh;
	}

	protected float getDiemlythuyet() {
		return diemlythuyet;
	}

	protected void setDiemlythuyet(float diemlythuyet) {
		this.diemlythuyet = diemlythuyet;
	}
	
	protected Float getAveragePoint() {
		return (this.diemlythuyet + this.diemthuchanh *2) / 3;
	}
    
	protected void showStudentInfo() {
		System.out.println("Mã sinh viên : " + getMasinhvien());
		System.out.println("Họ và tên : " + getHovaten());
		System.out.println("Điểm lý thuyết : " + getDiemlythuyet());
		System.out.println("Điểm thực hành : " + getDiemthuchanh());
		System.out.println("Điểm trung bình : " + getAveragePoint());
		
	}
	 
	public static void main(String[] args) {
		SinhVien firstStudent = new SinhVien();
		firstStudent.setMasinhvien(200001);
		firstStudent.setHovaten("Lê Thị Bống");
		firstStudent.setDiemthuchanh(7.5f);
		firstStudent.setDiemlythuyet(8.3f);
		firstStudent.showStudentInfo();

	}

}
