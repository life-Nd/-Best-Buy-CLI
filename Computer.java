import java.util.List;

/***
 * The Computer interface defines a set of methods that any class that
 * implements the interface must provide a concrete implementation for. The
 * interface provides a common set of methods that can be used to interact with
 * Computer objects, regardless of their specific implementation.
 */

public interface Computer {
    // get the unique ID of the Computer object
    int getOrderId();

    // get the description of the Computer object (base + additional components)
    String getDescription();

    // get the total price of the Computer object (base + additional components)
    double getPrice();

    // get the base Computer object
    Component getComputer();

    // get the additional components added to the Computer object
    List<Component> getComponents();

    // add an additional component to the Computer object
    void addComponent(Component component);
}