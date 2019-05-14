//package ui;
//
//import java.util.List;
//
//import business.Movie;
//import business.MovieCollection;
//import util.Console;
//
//public class MovieRatingsApp {
//	private static MovieCollection collection = new MovieCollection();
//	public static void main(String[] args) {
//		System.out.println("Welcome to the Movie Ratings App\n");
//		
//		displayMenu();
//		int choice = Console.getInt("Choose a menu option: ", 0, 7);
//		while (choice != 6) {
//			if (choice == 1) {
//				addMovies();
//			} else if (choice == 2) {
//				System.out.println("Movies rated 4.0 or higher");
//				System.out.println("--------------------------");
//				List<Movie> temp = collection.filterMovies(c -> c.getRating() >= 4);
//				MovieCollection tempMc = new MovieCollection();
//				for (Movie m: temp) {
//					tempMc.add(m);
//				}
//				tempMc.listMovies();
//			} else if (choice == 3) {
//				System.out.println("Most recent movie");
//				System.out.println("-----------------");
//				System.out.println(collection.filterMovies(c -> ));
//			}
//			displayMenu();
//			choice = Console.getInt("Choose a menu option: ", 0, 7);
//		}
//	}
//	
//	public static void addMovies() {
//		int num = Console.getInt("How many movies would you like to add: ", 0, 10);
//		for (int i = 1; i <= num; i ++) {
//			System.out.println("Movie #" + i);
//			System.out.println("----------");
//			String title = Console.getString("Enter title: ", true);
//			int year = Console.getInt("Enter year: ", 1900, 2020);
//			double rating = Console.getDouble("Enter rating between 1 and 5: ", 1, 5);
//			collection.add(title, year, rating);
//		}
//	}
//	
//	public static void displayMenu() {
//		System.out.println("--------------------------");
//		System.out.println("What do you you want to do");
//		System.out.println("--------------------------");
//		System.out.println("1 - Enter a movie");
//		System.out.println("2 - View top rated movie");
//		System.out.println("3 - View most recent movie");
//		System.out.println("4 - View all movies");
//		System.out.println("5 - View ratings");
//		System.out.println("6 - Quit app");
//	}
//}

package ui;

import java.time.LocalDate;
import java.util.List;

import business.Movie;
import business.MovieCollection;
import util.Console;

public class MovieRatingsApp {

	/*
	 * This app depends on the classes Movie and MovieCollection,
	 * which you have to write.  This main method gives you 
	 * some sample movies to use to add to your collection.
	 * Feel free to add some of your favorites to this list!
	 * Sean Blessing - May 2019
	 */
	public static void main(String[] args) {
		//initialize stuff
		MovieCollection mc = new MovieCollection();
		mc.add(new Movie("Avengers Infinity War", 2018, 4.0));
		mc.add(new Movie("Star Wars Episode 5 - Empire Strikes Back", 1980, 5.0));
		mc.add(new Movie("Sixteen Candles", 1984, 4.5));
		mc.add(new Movie("Happy Gilmore", 1996, 4.3));
		mc.add(new Movie("The Jerk", 1979, 3.9));
		mc.add(new Movie("Superbad", 2007, 3.9));
		mc.add(new Movie("Back to the Future Part III", 1990, 2.1));
		mc.add(new Movie("Entourage", 2015, 1.5));
		
		System.out.println("Welcome to the Movie Ratings application!\n");
		int choice = 0;
		
		while (choice!=6) {
			displayMenu();
		
			choice = Console.getInt("Choose a menu option:  ", 1, 6);
		
			List<Movie> filteredMovies;
			switch (choice) {
				case 1:		// Enter a movie
					int nbr = Console.getInt("How many movies do you want to enter?  ", 0, 11);
					for (int i = 1; i <= nbr; i++) {
						Console.println("Movie # "+i);
						Console.println("===========");
						String title = Console.getString("Enter title:  ");
						int year = Console.getInt("Enter year:  ", 1900, LocalDate.now().getYear());
						double rating = Console.getDouble("Enter rating:  ", 1.0, 5.0);
						Movie m = new Movie(title, year, rating);
						mc.add(m);
					}
					break;
				case 2:		// View top rated movies
					filteredMovies = mc.filterMovies(m -> m.getRating()>=4.0);
					Console.println("Movies rated 4.0 or higher");
					Console.println("==========================");
					filteredMovies.stream().forEach(System.out::println);
					break;
				case 3:		// View most recent movies (movies released within last 10 years)
					int yearRange = LocalDate.now().getYear() - 10;
					filteredMovies = mc.filterMovies(m -> m.getYear()>=yearRange);
					Console.println("Movies released in last 10 years");
					Console.println("================================");
					filteredMovies.stream().forEach(System.out::println);
					break;
				case 4:
					Console.println("All movies");
					Console.println("==========");
					mc.getMovies().stream().forEach(System.out::println);
					break;
				case 5:		// View ratings
					Console.println("Movie rating data");
					Console.println("=================");
					Console.println("# of movies:  "+mc.getSize());
					Console.println("lowest rating:  "+mc.getLowestRating());
					Console.println("highest rating:  "+mc.getHighestRating());
					Console.println("average rating:  "+mc.getAverageRating());
					break;
				case 6:
					Console.println("Goodbye!");
					break;
			}
		
		}
		

	}

	public static void displayMenu() {
        Console.println("-----------------------");
        Console.println("What do you want to do?");
        Console.println("-----------------------");
        Console.println("1 - Enter a movie");
        Console.println("2 - View top rated movies");
        Console.println("3 - View most recent movies");
        Console.println("4 - View all movies");
        Console.println("5 - View ratings");
        Console.println("6 - Quit application");
        
        Console.println();
	}
	
}