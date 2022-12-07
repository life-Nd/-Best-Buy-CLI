import java.util.Comparator;
import java.util.List;

// SortByOrderID class implements the SortingStrategy interface
// and provides the specific implementation for sorting by id
public class SortByOrderID implements SortStrategy {
    @Override
    public List<Computer> sort(List<Computer> orders) {
        // sort the orders by id in ascending computer
        orders.sort(Comparator.comparing((Computer computer) -> computer.getOrderId()).reversed());
        return orders;
    }
}