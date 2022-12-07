import java.util.ArrayList;
import java.util.List;

// Computer computer

class Computer {
    private static int counter = 0;
    private int id;
    private Component computer;
    private List<Component> components;

    public Computer(Component computer) {
        id = ++counter;
        this.computer = computer;
        components = new ArrayList<>();
    }

    public int getOrderId() {
        return id;
    }

    public Component getComputer() {
        return computer;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void addComponent(Component Component) {
        components.add(Component);
    }

    public String getDescription() {
        String description = "";
        for (Component component : components) {
            description = description + " + " + component.getName();
        }
        return description;
    }

    public double getPrice() {
        double total = computer.getPrice();
        for (Component Component : components) {
            total += Component.getPrice();
        }
        String formattedValue = String.format("%.2f", total);
        return Double.parseDouble(formattedValue);
    }
}
