import java.util.List;

// Computer computer
public interface Computer {
    int getOrderId();

    String getDescription();

    double getPrice();

    Component getComputer();

    List<Component> getComponents();

    void addComponent(Component component);
}
