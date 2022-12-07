import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Singleton market space
class MarketSpace {
    private static final MarketSpace INSTANCE = new MarketSpace();
    private static Component defaultComputer = new Component("Default Computer", 700);

    public static MarketSpace getInstance() {
        return INSTANCE;
    }

    private static List<Computer> cart = new ArrayList<>();
    private Map<String, Component> products;
    private static Scanner scanner = new Scanner(System.in);

    private MarketSpace() {
        products = new HashMap<>();
    }

    void loadComponents() {
        // Load Components from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                Component component = new Component(name, price);
                // addComponent(new Component(name, price));

                products.put(component.getName(), component);
            }
            reader.close();
        } catch (Exception e) {
        }

    }

    public Map<String, Component> getProducts() {
        return products;
    }

    public void addComponent(Component Component) {
        products.put(Component.getName(), Component);
    }

    public Component getComponent(String name) {
        return products.get(name);
    }

    public void startCLI() {

        while (true) {
            System.out.println("Hi, what would you like to do?");
            System.out.println("1: Buy a computer");
            System.out.println("2: See my shopping cart");
            System.out.println("3: Sort by computer ID (Descending computer)");
            System.out.println("4: Sort by computer price (Descending computer)");
            System.out.println("5: Quit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    buyComputer();
                    break;
                case 2:
                    seeShoppingCart();
                    break;
                case 3:
                    sort(new SortByOrderID());
                    break;
                case 4:
                    sort(new SortByPrice());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        }

    }

    private static void buyComputer() {
        // Create a new computer with default computer
        Computer computer = new Computer(defaultComputer);

        // Add Components to the computer
        while (true) {

            System.out.print("\nCurrent Build: " + computer.getComputer().getName());
            System.out.print(computer.getDescription());
            System.out.println(" and total price is $"
                    + computer.getPrice());
            System.out.println("What component would you like to add?");
            MarketSpace market = MarketSpace.getInstance();
            for (Component component : market.getProducts().values()) {
                int index = Arrays.asList(market.getProducts().values().toArray()).indexOf(component) + 1;
                System.out.println(index +
                        ": " + component.getName()
                        + " $" + component.getPrice());
            }
            System.out.println((market.getProducts().size() + 1) + ": Done");
            int choice = scanner.nextInt();
            if (choice == market.getProducts().size() + 1) {
                break;
            }
            Component component = (Component) market.getProducts().values().toArray()[choice - 1];
            Component newComponent = new ComputerDecorator(component.getName(), component.getPrice());
            computer.addComponent(newComponent);
        }

        // Add the computer to the list of cart
        cart.add(computer);
    }

    private static void seeShoppingCart() {
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty, you need to buy a computer first.");
        } else {
            System.out.print("Your shopping cart: \n[");
            for (Computer computer : cart) {
                System.out.print("OrderID@" + computer.getOrderId() + " : " + computer.getComputer().getName() + " ");
                System.out.println(computer.getDescription());
                System.out.println("and total price is " + computer.getPrice() + " $");
            }
            System.out.println("]");
        }
    }

    private static void sort(SortStrategy strategy) {
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty, you need to buy a computer first.");
        } else {
            cart = strategy.sort(cart);
            seeShoppingCart();
        }
    }

}