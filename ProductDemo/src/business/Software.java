package business;
public class Software extends Product {

	private String version;

	public Software(String code, String description, double price, String version) {
		super(code, description, price);
		this.version = version;
	}

	public Software(String version) {
		super();
		this.version = version;
	}
	
	public Software() {
		super();
		this.version = "";
	}

	public void setVersion(String v) {
		version = v;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return "Software [version=" + version + ", " + super.toString() + "]";
	}
	
	

}
