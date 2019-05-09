import java.util.*;

public class ExceptionTestingApp {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a number less than 10: ");
		boolean tryAgain = true;
		while (tryAgain) {
			try {
				int num = sc.nextInt();
				if (num >= 10) {
					throw new InputMismatchException();
				}
				tryAgain = false;
			} catch (InputMismatchException ime) {
				System.out.println("Enter a valid integer: ");
			}
			sc.nextLine();
		}
		System.out.println("end");
	}
}
