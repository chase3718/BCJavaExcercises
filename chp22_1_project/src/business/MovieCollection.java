package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class MovieCollection {
	private List<Movie> movies;
	
	public MovieCollection() {
		movies = new ArrayList<>();
	}
	
	public void add(String title, int year, double rating) {
		movies.add(new Movie(title, year, rating));
	}

	public void add(Movie m) {
		movies.add(m);
	}

	public List<Movie> filterMovies(Predicate<Movie> condition) {
		List<Movie> filteredMovies = new ArrayList<>();
		for (Movie m : movies) {
			if (condition.test(m)) {
				filteredMovies.add(m);
			}

		}
		return filteredMovies;
	}

	public double getLowestRating() {
		double low = movies.stream().map(c -> c.getRating()).reduce(5.1, Math::min);
		return low;
	}

	public double getHighestRating() {
		double hi = movies.stream().map(c -> c.getRating()).reduce(0.0, Math::max);
		return hi;
	}

	public double getAverageRating() {
		double avg = movies.stream().map(c -> c.getRating()).reduce(0.0, (a, b) -> a + b);
		avg = avg / movies.size();
		return avg;
	}

	public int getSize() {
		return movies.size();
	}

	public void listMovies() {
		movies.stream().forEach(c -> System.out.println(c));
	}

	public Collection<Movie> getMovies() {
		return movies;
	}
}
