
public class matrixApp {

	public static void main(String[] args) {
		String bin = "";
		int x;
		for (int y = 0; y < 1000000; y++) {
			for (int i = 0; i < 100; i++) {
				x = (int) (Math.random() * 2);
				bin += x;
			}
			System.out.println(bin);
			bin = "";
		}

	}

}
