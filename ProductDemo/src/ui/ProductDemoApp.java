package ui;
import business.Book;
import business.Product;
import business.Software;

public class ProductDemoApp {

	public static void main(String[] args) {

		System.out.println("Hello World");
		
		Software timbo = new Software();
		timbo.setCode("Timbo123");
		timbo.setDescription("The most advanced software of all time.");
		timbo.setPrice(0);
		timbo.setVersion("v2.10.14");
		
		Book crimbo = new Book("James P. Sullivan");
		crimbo.setCode("Crimbo135");
		crimbo.setDescription("The greatest story ever told");
		crimbo.setPrice(3.50);

		Book gimbo = new Book("Gimbo654", "It's allright", 5.95, "Gimbo Paulson");
		
		Product kimbo = gimbo;

		System.out.println(timbo);
		
		System.out.println(crimbo.getClass());

		System.out.println(gimbo);
		
		System.out.println(kimbo);
		
		System.out.println(gimbo.equals(kimbo));
		
		System.out.println(gimbo.hashCode());
	}

}
