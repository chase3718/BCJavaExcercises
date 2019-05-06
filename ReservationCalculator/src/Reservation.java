import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class Reservation {
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	private static Double NIGHTLY_RATE = 145.0;
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	
	public String getArrivalDateFormatted() {
		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		return dtf.format(arrivalDate);
	}
	
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public LocalDate getDepartureDate() {
		return departureDate;	
	}
	
	public String getDepartureDateFormatted() {
		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		return dtf.format(departureDate);
	}
	
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	
	public int getNumberOfNights() {
		return (int)ChronoUnit.DAYS.between(arrivalDate, departureDate);
	}
	
	public String getPricePerNightFormatted() {
		return currencyFormat.format(NIGHTLY_RATE);
	}
	
	public String getTotalPrice() {
		return "" + getNumberOfNights() * NIGHTLY_RATE;
	}
	
	public String getTotalPriceFormatted() {
		return currencyFormat.format(Double.parseDouble(getTotalPrice()));
	}
}

	
	
