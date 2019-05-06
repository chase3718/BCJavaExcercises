
public class RotateLeftApp {
	public static void main (String[] args) {
		System.out.println("Welcome to the Rotate Left App");
		
		Console in = new Console();
		
		String[] input = in.getString("Enter any number of integers separated by commas (Doubles will be truncated): ").split(",");
		for (int i = 0; i < input.length; i++) {
			input[i] = input[i].trim();
		}
		
		Integer[] list = new Integer[input.length];
		for (int i = 0; i < list.length; i++) {
			list[i] = (int)(Double.parseDouble(input[i]));
		}
		System.out.println(toString(rotateLeft(list)));
	}
	
	public static Integer[] rotateLeft(Integer[] in) {
		Integer[] prev = in.clone();
		in[0] = prev[prev.length-1];
		for (int i = 1; i < in.length; i++) {
			in[i] = prev[i-1];
		}
		return in;
	}
	
	public static String toString(Integer[] str) {
		String s = "";
		for (int i = 0; i < str.length; i++) {
			s += str[i] + " ";
		}
		return s;
	}
}
