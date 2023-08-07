package javaOOP;

public class Topic_06_Getter_Setter {
	// Kiểm tra/ validate được dữ liệu
	private String personName;
	private int personAge;
	private int personPhone;
	private float personBankAccountAmount;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		if (personAge > 0 && personAge < 60) {
			throw new IllegalArgumentException("Tuổi nhập vào không hợp lệ");
		} else {
			this.personAge = personAge;
		}
	}

	public int getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(int personPhone) {
		if (!String.valueOf(personPhone).startsWith("0")) {
			throw new IllegalArgumentException("Số điện thoại băt đầu bằng: 09 - 03");
		} else if (personPhone < 10 || personPhone > 11) {
			throw new IllegalArgumentException("Số điện thoại phải từ 10 - 11 số");
		} else {
			this.personPhone = personPhone;
		}
	}

	public float getPersonBankAccountAmount() {
		return personBankAccountAmount;
	}

	public void setPersonBankAccountAmount(float personBankAccountAmount) {
		this.personBankAccountAmount = personBankAccountAmount;
	}

	public static void main(String[] args) {

	}

}
