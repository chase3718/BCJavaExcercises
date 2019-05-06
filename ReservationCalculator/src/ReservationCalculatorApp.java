import java.time.LocalDate;
import java.time.YearMonth;

public class ReservationCalculatorApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the Reservation Calculator App\n");

		Console in = new Console();

		do {
			Reservation myRes = new Reservation();

			int INYEAR = in.getInt("Enter the arrival year: ");
			int INMONTH = in.getInt("Enter the arrival month (1-12): ", 0, 13);
			YearMonth currentMonth = YearMonth.of(INYEAR, INMONTH);
			int daysInMonth = currentMonth.lengthOfMonth();
			int INDAY = in.getInt("Enter the arrival day (1-" + daysInMonth + "): ", 0, daysInMonth + 1);

			System.out.println();

			int OUTYEAR = in.getInt("Enter the departure year: ", INYEAR - 1, (int) Double.POSITIVE_INFINITY);
			int OUTMONTH;
			if (INYEAR == OUTYEAR) {
				OUTMONTH = in.getInt("Enter the departure month (" + INMONTH + "-12): ", INMONTH - 1, 13);
			} else {
				OUTMONTH = in.getInt("Enter the departure month (1-12): ", 0, 13);
			}
			currentMonth = YearMonth.of(OUTYEAR, OUTMONTH);
			daysInMonth = currentMonth.lengthOfMonth();
			int OUTDAY;
			if (INYEAR == OUTYEAR && INMONTH == OUTMONTH) {
				OUTDAY = in.getInt("Enter the arrival day (" + (INDAY + 1) + "-" + daysInMonth + "): ", INDAY,
						daysInMonth + 1);
			} else {
				OUTDAY = in.getInt("Enter the arrival day (1-" + daysInMonth + "): ", 0, daysInMonth + 1);
			}

			System.out.println();

			LocalDate arrivalDate = LocalDate.of(INYEAR, INMONTH, INDAY);
			LocalDate departureDate = LocalDate.of(OUTYEAR, OUTMONTH, OUTDAY);

			myRes.setArrivalDate(arrivalDate);
			myRes.setDepartureDate(departureDate);

			System.out.println("Arrival Date: " + myRes.getArrivalDateFormatted());
			System.out.println("Departure Date: " + myRes.getDepartureDateFormatted());
			System.out.println("Price: " + myRes.getPricePerNightFormatted() + " per night");
			System.out.println("Total Price: " + myRes.getTotalPriceFormatted() + " for " + myRes.getNumberOfNights());
			System.out.println();

		} while (in.getString("Continue? (y/n) ", true, "y", "n").equals("Y"));

	}
}
