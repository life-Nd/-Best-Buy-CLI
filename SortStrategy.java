import java.util.List;

// SortingStrategy interface defines the method for sorting a List of orders
public interface SortStrategy {
    List<Computer> sort(List<Computer> orders);
}
