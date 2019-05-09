package business;

import java.text.NumberFormat;

public class Product {
	private int ID;
    private int vendorID;
    private String partNumber;
    private String name;
    private double price;
    private String unit;
    private String photoPath;
    
    public Product () {
    	
    }
    
	public Product(int vendorID, String partNumber, String name, double price, String unit, String photoPath) {
		super();
		this.vendorID = vendorID;
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photoPath;
	}

	public Product(int iD, int vendorID, String partNumber, String name, double price, String unit, String photoPath) {
		super();
		ID = iD;
		this.vendorID = vendorID;
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photoPath;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}
	
	public String getPriceFormatted() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(price);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return "Product [ID=" + ID + ", vendorID=" + vendorID + ", partNumber=" + partNumber + ", name=" + name
				+ ", price=" + price + ", unit=" + unit + ", photoPath=" + photoPath + "]";
	}
  
}
