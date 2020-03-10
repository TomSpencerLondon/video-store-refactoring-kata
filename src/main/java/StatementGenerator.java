import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class StatementGenerator {
  public String generate(String name, Vector<Rental> rentals, PricePlan plan) {
    double totalAmount = 0;
    int frequentRenterPoints = 0;

    StringBuilder result = new StringBuilder("Rental Record for " + name + "\n");

    List<PricedRental> priced = rentals.stream().map(plan).collect(Collectors.toList());

    for (PricedRental pricedRental : priced) {
      double rentalPrice = pricedRental.price;

      frequentRenterPoints += pricedRental.points;

      result.append("\t").append(pricedRental.title).append("\t").append(rentalPrice).append("\n");
      totalAmount += rentalPrice;

    }

    result.append("You owed ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");


    return result.toString();
  }
}
