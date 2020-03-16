
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.*;
public class LoyaltyPlanTest {
  @Test
  public void rental_earns_one_point() throws Exception {
    assertThat(new LoyaltyPlan().calculatePoints(new Rental(new Movie("title", Movie.REGULAR), 1)), Matchers.equalTo(1));
  }

  @Test
  public void new_release_movie_for_one_day_earns_one_point() throws Exception {
    assertThat(new LoyaltyPlan().calculatePoints(new Rental(new Movie("title", Movie.REGULAR), 1)), Matchers.equalTo(1));
  }

  @Test
  public void new_release_movie_for_more_than_one_day_earns_two_points() throws Exception {
    assertThat(new LoyaltyPlan().calculatePoints(new Rental(new Movie("title", Movie.REGULAR), 2)), Matchers.equalTo(2));
    assertThat(new LoyaltyPlan().calculatePoints(new Rental(new Movie("title", Movie.REGULAR), 3)), Matchers.equalTo(2))
  }
}
