import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Trip {
	private LocalDate date;
	private LocalDate dateOfArrival;
	private LocalTime time;
	private LocalTime timeOfArrival;
	private int dist;
	private int speed;
	private DateTimeFormatter dtf;
	
	public Trip (String date, String time, int dist, int speed) {
		LocalDate date1 = LocalDate.parse(date);
		this.date = date1;
		LocalTime time1 = LocalTime.parse(time);
		this.time = time1;
		this.dist = dist;
		this.speed = speed;
	}
	
	public int getHoursSpent() {
		return (int)(dist / speed);
	}

	public int getMinutesSpent() {
		int minutes = (int)(((((double)dist / (double)speed) - (int)((double)dist / (double)speed)) * 60) + 0.5);
		return minutes;
	}

	public void setTimeOfArrival() {
		int hour = time.getHour() + getHoursSpent();
		int minute = time.getMinute() + getMinutesSpent();
		if (minute >= 60) {
			hour ++;
			minute -= 60;
		}
		
		if (hour > 23) {
			hour -= 24;
		}
		timeOfArrival = LocalTime.of(hour, minute);
	}

	public LocalTime getTimeOfArrival() {
		return timeOfArrival;
	}
	
	public String getTimeOfArrivalFormatted() {
		dtf = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
		return dtf.format(timeOfArrival);
	}
	
	public void setDateOfArrival() {
		LocalDateTime dt = LocalDateTime.of(date, time);
		dt = dt.plusHours(getHoursSpent());
		dt = dt.plusMinutes(getMinutesSpent());
		dateOfArrival = LocalDate.of(dt.getYear(), dt.getMonth(), dt.getDayOfMonth());
	}

	public LocalDate getDateOfArrival() {
		return dateOfArrival;
	}
	
	public String getDateOfArrivalFormatted() {
		dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		return dtf.format(dateOfArrival);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setDate(String date) {
		LocalDate date1 = LocalDate.parse(date);
		this.date = date1;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public void setTime(String time) {
		LocalTime time1 = LocalTime.parse(time);
		this.time = time1;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public String toString() {
		String str = ("Estimated travel time") 
				   + ("\nHours: " + getHoursSpent())
				   + ("\nMinutes: " + getMinutesSpent()) 
				   + ("\nEstimated date of arrival: " + getDateOfArrivalFormatted())
				   + ("\nEstimated time of arrival: " + getTimeOfArrivalFormatted());
		
		return str;
	}
	
}
