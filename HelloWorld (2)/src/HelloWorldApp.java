import java.util.Scanner;
public class HelloWorldApp {
	public static void main (String[] args) {
		String name = "";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your name");
		name = sc.next();
		System.out.println("Hello " + name);
	}
}

