import java.util.Vector;

public class Customer {
  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.addElement(rental);
  }

  private String getName() {
    return name;
  }

  public String statement() {
    double totalAmount = 0;
    int frequentRenterPoints = 0;

    StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

    for (Rental rental : this.rentals) {
      double thisAmount = 0;

      // determines the amount for each line
      switch (rental.getMovie().getPriceCode()) {
        case Movie.REGULAR:
          thisAmount += 2;
          if (rental.getDaysRented() > 2)
            thisAmount += (rental.getDaysRented() - 2) * 1.5;
          break;
        case Movie.NEW_RELEASE:
          thisAmount += rental.getDaysRented() * 3;
          break;
        case Movie.CHILDRENS:
          thisAmount += 1.5;
          if (rental.getDaysRented() > 3)
            thisAmount += (rental.getDaysRented() - 3) * 1.5;
          break;
      }

      frequentRenterPoints++;

      if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
              && rental.getDaysRented() > 1)
        frequentRenterPoints++;

      result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;

    }

    result.append("You owed ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");


    return result.toString();
  }


  private String name;
  private Vector<Rental> rentals = new Vector<>();
}