import java.util.Formatter;
import java.util.Scanner;

public class FormExApp {

	public static void main(String[] args) {
		//System.out.printf("My name is: %s%n", "joe");
		
		
  		StringBuilder sbuf = new StringBuilder();
		Formatter fmt = new Formatter(sbuf);
		fmt.format("PI = %f%n", Math.PI);
		System.out.print(sbuf.toString());


/*
		System.out.printf("%2$s", 32, "Hello"); // prints: "Hello"
		
		System.out.println();
		
		System.out.printf("%d", 93); // prints 93
		
		System.out.println();
		
		System.out.printf("|%20d|", 93); // prints: |                  93|
		
		System.out.println();
		
		System.out.printf("|%-20d|", 93); // prints: |93                  |
		
		System.out.println();
		
		System.out.printf("|%020d|", 93); // prints: |00000000000000000093|
		
		System.out.println();
		
		System.out.printf("|%+20d|", 93); // prints: |                 +93|
		
		System.out.println();
		
		System.out.printf("|% d|", 93); // prints: | 93| 
		
		System.out.println();
		
		System.out.printf("|% d|", -36); // prints: |-36|
		
		System.out.println();
		
		System.out.printf("|%,d|", 10000000); // prints: |10,000,000|
		
		System.out.println();
		
		System.out.printf("|%(d|", -36); // prints: |(36)|
		
		System.out.println();
		
		System.out.printf("|%o|", 93); // prints: 135 (Octal)
		
		System.out.println();
		
		System.out.printf("|%x|", 93); // prints: 5d (Hex)
		
		System.out.println();
		
		System.out.printf("|%#o|", 93); // prints: 0135
		
		System.out.println();
		
		System.out.printf("|%#x|", 93); // prints: 0x5d
		
		System.out.println();
		
		System.out.printf("|%#X|", 93); // prints: 0X5D
		
		System.out.printf("|%s|", "Hello World"); // prints: "Hello World"
		
		System.out.println();
		
		System.out.printf("|%30s|", "Hello World"); // prints: | Hello World|
		
		System.out.println();
		
		System.out.printf("|%-30s|", "Hello World"); // prints: |Hello World |
		
		System.out.println();
		
		System.out.printf("|%.5s|", "Hello World"); // prints: |Hello|
		
		System.out.println();
		
		System.out.printf("|%30.5s|", "Hello World"); // |                         Hello|
		
		System.out.println();
		
		System.out.printf("%30.5s %030d", "Hello World", 42);
*/
/*
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter an Integer: ");
		long num = sc.nextLong();
		System.out.printf("%-15s%-15s%-15s\n", "Number", "Squared", "Cubed");
		System.out.printf("%-15s%-15s%-15s\n", "======", "=======", "=====");
		for(long i = 1; i <= num; i++) {
			System.out.printf("%-15d%-15d%-15d\n", i, i*i, i*i*i);
		}
		
		sc.close();
*/
	}

}
