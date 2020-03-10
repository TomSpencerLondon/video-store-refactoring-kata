

import junit.framework.*;

public class VideoStoreTest extends TestCase
{
	public VideoStoreTest (String name) {
		super (name);
	}

	protected void setUp ()  {
		customer = new Customer ("Fred");
	}

	public void testSingleNewReleaseStatement () {
		String title = "The Cell";
		int daysRented = 3;

		customerRentsMovieForPeriod(title, daysRented, Movie.NEW_RELEASE);
		assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", generate());
	}

	private String generate() {
		return customer.generateStatement(new StatementGenerator());
	}

	public void testDualNewReleaseStatement () {
		int daysRented = 3;
		customerRentsMovieForPeriod("The Cell", daysRented, Movie.NEW_RELEASE);
		customerRentsMovieForPeriod("The Tigger Movie", daysRented, Movie.NEW_RELEASE);
		assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", generate());
	}

	public void testSingleChildrensStatement () {
		int daysRented = 3;
		customerRentsMovieForPeriod("The Tigger Movie", daysRented, Movie.CHILDRENS);
		assertEquals ("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", generate());
	}

	public void testMultipleRegularStatement () {
		int daysRented = 1;
		customerRentsMovieForPeriod("Plan 9 from Outer Space", daysRented, Movie.REGULAR);
		int daysRented1 = 2;
		customerRentsMovieForPeriod("8 1/2", daysRented1, Movie.REGULAR);
		int daysRented2 = 3;
		customerRentsMovieForPeriod("Eraserhead", daysRented2, Movie.REGULAR);

		assertEquals ("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", generate());
	}

	private Customer customer;

	private Movie newRelease(String title, int type) {
		return new Movie(title, type);
	}

	private void customerRentsMovieForPeriod(String title, int daysRented, int newRelease) {
		customer.addRental(new Rental(newRelease(title, newRelease), daysRented));
	}
}
