import java.util.ArrayList;
import java.util.List;

/**
 * In this class, we have implemented the `Computer` interface and provided the
 * concrete implementation for the methods defined in the interface. The
 * `ComputerDecorator` class is a decorator class that allows us to add
 * additional components to a `Computer` object and calculate its total price.
 */
public class ComputerDecorator implements Computer {
    // counter to generate unique IDs for the Computer objects
    private static int counter = 0;
    // the ID of the Computer object
    private int id;
    // the Computer object
    private Component computer;
    // the list of additional components added to the Computer object
    private List<Component> components;

    public ComputerDecorator(Component defaultComputer) {
        // increment the counter and assign the ID to the Computer object
        id = ++counter;
        // set the default Computer object
        this.computer = defaultComputer;
        // initialize the list of additional components
        components = new ArrayList<>();
    }

    @Override
    public int getOrderId() {
        // return the ID of the Computer object
        return id;
    }

    @Override
    public Component getComputer() {
        // return the base Computer object
        return computer;
    }

    @Override
    public List<Component> getComponents() {
        // return the list of additional components added to the Computer object
        return components;
    }

    @Override
    public void addComponent(Component component) {
        // add an additional component to the Computer object
        components.add(component);
    }

    @Override
    public String getDescription() {
        // get the description of the additional components added to the Computer object
        String description = "";
        for (Component component : components) {
            description = description + " + " + component.getName();
        }
        return description;
    }

    @Override
    public double getPrice() {
        double total = computer.getPrice();
        for (Component component : components) {
            total += component.getPrice();
        }
        String formattedValue = String.format("%.2f", total);
        return Double.parseDouble(formattedValue);
    }
}
