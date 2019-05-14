import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;

public class RecursionApp {
	public static void main(String[] args) {
		System.out.println("Hello");

		printUntilGone("Hello World");
		divideUntilOdd(1024);

		LocalTime n = LocalTime.now().plusSeconds(1);

		double p = 0;

		while (LocalTime.now().isBefore(n)) {
			p++;
			boolean prime = true;
			for (int i = 2; i < p / 2; i++) {
				if (p % i == 0) {
					prime = false;
					continue;
				}
			}
			if (prime)
				System.out.println(p);
		}

		System.out.println("Goodbye");
	}

	public static void printUntilGone(String str) {
		if (str.length() > 0) {
			System.out.println(str);
			str = str.substring(0, str.length() - 1);
			printUntilGone(str);
		}
	}

	public static void divideUntilOdd(int n) {
		if (n % 2 == 0) {
			System.out.println(n);
			n = n / 2;
			divideUntilOdd(n);
		}
	}
}