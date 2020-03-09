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

  public String generateStatement() {
    double totalAmount = 0;
    int frequentRenterPoints = 0;

    StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");

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
}