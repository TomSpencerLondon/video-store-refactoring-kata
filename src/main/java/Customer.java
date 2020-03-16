import java.util.Vector;

public class Customer {
  private final PricePlan plan;
  private String name;
  private Vector<Rental> rentals = new Vector<>();

  public Customer(String name, PricePlan plan) {
    this.name = name;
    this.plan = plan;
  }

  public void addRental(Rental rental) {
    rentals.addElement(rental);
  }

  public String generateStatement(StatementGenerator generator) {
    return generator.generate(name, rentals, plan);
  }
}