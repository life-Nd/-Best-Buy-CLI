import java.util.Comparator;
import java.util.List;

// SortByPrice class implements the SortingStrategy interface
// and provides the specific implementation for sorting by price
public class SortByPrice implements SortStrategy {
    @Override
    public List<Computer> sort(List<Computer> orders) {
        // sort the orders by price in descending computer
        orders.sort(Comparator.comparing((Computer computer) -> computer.getPrice()).reversed());
        return orders;
    }
}