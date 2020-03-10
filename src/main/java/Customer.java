import java.util.Vector;

public class Customer {
  private String name;
  private Vector<Rental> rentals = new Vector<>();



  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.addElement(rental);
  }

  public String generateStatement(StatementGenerator generator) {
    return generator.generate(name, rentals, new PricePlan());
  }


}