package business;

public class PurchaseRequestLineItem {
	private int ID;
	private int purchaseRequestID;
	private int productID;
	private int quantity;
	
	public PurchaseRequestLineItem () {
		
	}

	public PurchaseRequestLineItem(int purchaseRequestID, int productID, int quantity) {
		super();
		this.purchaseRequestID = purchaseRequestID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public PurchaseRequestLineItem(int iD, int purchaseRequestID, int productID, int quantity) {
		super();
		ID = iD;
		this.purchaseRequestID = purchaseRequestID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPurchaseRequestID() {
		return purchaseRequestID;
	}

	public void setPurchaseRequestID(int purchaseRequestID) {
		this.purchaseRequestID = purchaseRequestID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PurchaseRequestLineItem [ID=" + ID + ", purchaseRequestID=" + purchaseRequestID + ", productID="
				+ productID + ", quantity=" + quantity + "]";
	}

}
