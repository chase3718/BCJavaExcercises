
public class ArrivalTimeCalcApp {
	public static void main (String[] args) {
		System.out.println("Welcome to the Arrival Time Calculator\n");
		
		Console in = new Console();
		
		do {
			
			String date = in.getString("Departure date (YYYY-MM-DD): ", true);
			String time = in.getString("Departure time (HH:MM): ", true);
			int dist = in.getInt("Number of miles: ", 0, (int)Double.POSITIVE_INFINITY);
			int speed = in.getInt("Miles per hour: ", 0, (int)Double.POSITIVE_INFINITY);
			
			Trip t = new Trip(date, time, dist, speed);
			
			t.setDateOfArrival();
			t.setTimeOfArrival();
			
			System.out.println();
			
			System.out.println(t.toString());
			
		} while (in.getString("\nContinue? (y/n) ", true, "y", "n").equals("Y"));
		
		System.out.println("\nBye");
	}
}
