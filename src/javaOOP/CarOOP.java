package javaOOP;

public class CarOOP {
    
	// Thuộc tính
	private String carCompany;
	private String carType;
	private String fuelType;
	private Float mileAge;
	private Double carPrice;
	
	protected CarOOP(String carCompany, String carType, String fuelType, Float mileAge, Double carPrice) {
		this.carCompany = carCompany;
		this.carType = carType;
		this.fuelType = fuelType;
		this.mileAge = mileAge;
		this.carPrice = carPrice;
	}
	
	protected String getCarCompany() {
		return carCompany;
	}

	protected void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}

	protected String getCarType() {
		return carType;
	}

	protected void setCarType(String carType) {
		this.carType = carType;
	}

	protected String getFuelType() {
		return fuelType;
	}

	protected void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	protected Float getMileAge() {
		return mileAge;
	}

	protected void setMileAge(Float mileAge) {
		this.mileAge = mileAge;
	}

	protected Double getCarPrice() {
		return carPrice;
	}

	protected void setCarPrice(Double carPrice) {
		this.carPrice = carPrice;
	}

	protected void showCarInfor() {
		System.out.println("Car company = " + getCarCompany());
		System.out.println("Car type = " + getCarType());
		System.out.println("Car fuel type = " + getFuelType());
		System.out.println("Car mile age = " + getMileAge());
		System.out.println("Car price = " + getCarPrice());
	}

	public static void main(String[] args) {
		// Honda
		CarOOP honda = new CarOOP("Honda", "City", "Petrol", 200f, 50000d);
		honda.showCarInfor();
		
		// Toyota 
		CarOOP toyota = new CarOOP("Toyota", "Civic", "Diesel", 1500f, 40000d);
		toyota.showCarInfor();

	}
}
