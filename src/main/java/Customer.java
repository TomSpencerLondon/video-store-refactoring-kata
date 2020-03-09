import java.util.Vector;

public class Customer {

  private final Tariff tariff = new Tariff();
  private final LoyaltyPlan loyaltyPlan = new LoyaltyPlan();
  private String name;
  private Vector<Rental> rentals = new Vector<>();



  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.addElement(rental);
  }

  private String getName() {
    return name;
  }

  public String generateStatement() {
    double totalAmount = 0;
    int frequentRenterPoints = 0;

    StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

    for (Rental rental : this.rentals) {
      double rentalPrice = tariff.calculatePrice(rental);

      frequentRenterPoints += loyaltyPlan.calculatePoints(rental);

      result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(rentalPrice).append("\n");
      totalAmount += rentalPrice;

    }

    result.append("You owed ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");


    return result.toString();
  }


  private class Tariff {

    private double calculatePrice(Rental rental) {
      double rentalPrice = 0;

      // determines the amount for each line
      switch (rental.getMovie().getPriceCode()) {
        case Movie.REGULAR:
          rentalPrice += 2;
          if (rental.getDaysRented() > 2)
            rentalPrice += (rental.getDaysRented() - 2) * 1.5;
          break;
        case Movie.NEW_RELEASE:
          rentalPrice += rental.getDaysRented() * 3;
          break;
        case Movie.CHILDRENS:
          rentalPrice += 1.5;
          if (rental.getDaysRented() > 3)
            rentalPrice += (rental.getDaysRented() - 3) * 1.5;
          break;
      }
      return rentalPrice;
    }
  }

  private class LoyaltyPlan {

    private int calculatePoints(Rental rental) {
      int frequentRenterPoints = 0;
      frequentRenterPoints++;

      if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
              && rental.getDaysRented() > 1)
        frequentRenterPoints++;
      return frequentRenterPoints;
    }
  }
}