package business;

public class Book extends Product {

	private String author;

	public Book(String code, String description, double price, String author) {
		super(code, description, price);
		this.author = author;
	}

	public Book(String author) {
		super();
		this.author = author;
	}

	public Book() {
		this.author = "";
	}

	public void setAuthor(String a) {
		author = a;
	}

	public String getAuthor() {
		return author;
	}
	
	@Override
	public String toString() {
		return "Book [author=" + author + ", " + super.toString() + "]";
	}

}
