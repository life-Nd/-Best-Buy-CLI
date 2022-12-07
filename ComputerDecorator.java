import java.util.ArrayList;
import java.util.List;

public class ComputerDecorator implements Computer {
    private static int counter = 0;
    private int id;
    private Component computer;
    private List<Component> components;

    public ComputerDecorator(Component computer) {
        id = ++counter;
        this.computer = computer;
        components = new ArrayList<>();
    }

    @Override
    public int getOrderId() {
        return id;
    }

    @Override
    public Component getComputer() {
        return computer;
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public String getDescription() {
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
