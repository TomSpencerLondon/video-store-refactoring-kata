import java.util.function.Function;

public class PricePlan implements Function<Rental, PricedRental> {
  private final Tariff tariff = new Tariff();
  private final LoyaltyPlan loyaltyPlan = new LoyaltyPlan();

  @Override
  public PricedRental apply(Rental r) {
    return new PricedRental(
            tariff.calculatePrice(r),
            loyaltyPlan.calculatePoints(r),
            r.getMovie().getTitle()
    );
  }
}
