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
    // create a constant reference to the MarketSpace instance
    private static final MarketSpace INSTANCE = new MarketSpace();

    // create a constant reference to the default Computer object
    private static Component defaultComputer = new Component("Default Computer", 700);

    // get the MarketSpace instance
    public static MarketSpace getInstance() {
        return INSTANCE;
    }

    // list of Computer objects added to the cart
    private static List<Computer> cart = new ArrayList<>();

    // map of available products (key: product name, value: product details)
    private Map<String, Component> products;

    // scanner to read user input from the command-line interface (CLI)
    private static Scanner scanner = new Scanner(System.in);

    // private constructor to ensure that only one instance of the MarketSpace class
    // is created
    private MarketSpace() {
        // initialize the products map
        products = new HashMap<>();
    }

    void loadComponents() {
        // Load Components from file
        try {
            // read the products data file and create a BufferedReader object
            BufferedReader reader = new BufferedReader(new FileReader("products.txt"));

            // read each line from the file
            String line;
            while ((line = reader.readLine()) != null) {
                // split the line into parts based on the comma separator
                String[] parts = line.split(",");

                // get the name and price of the product
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());

                // create a Component object with the name and price of the product
                Component component = new Component(name, price);

                // add the Component object to the products map
                products.put(component.getName(), component);
            }

            // close the BufferedReader object
            reader.close();
        } catch (Exception e) {
        }
    }

    // return the list of products
    public Map<String, Component> getProducts() {
        return products;
    }

    // add a new component to the Map<String,Component> products
    public void addComponent(Component Component) {
        products.put(Component.getName(), Component);
    }

    // return a specific product
    public Component getComponent(String name) {
        return products.get(name);
    }

    // start the Command-Line-Interface to get the user to interact with the program
    public void startCLI() {

        while (true) {
            System.out.println("Hi, what would you like to do?");
            System.out.println("1: Buy a computer");
            System.out.println("2: See my shopping cart");
            System.out.println("3: Sort by computer ID (Descending computer)");
            System.out.println("4: Sort by computer price (Descending computer)");
            System.out.println("5: Quit");

            int choice = scanner.nextInt();
            // show a specific function depending on the user input
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
                    System.out.println("❌ Invalid choice, try again.");
                    break;
            }
        }

    }

    private static void buyComputer() {
        // Create a new computer with default computer
        ComputerDecorator computer = new ComputerDecorator(defaultComputer);

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
            try {
                if (choice == market.getProducts().size() + 1) {
                    break;
                }
                Component component = (Component) market.getProducts().values().toArray()[choice - 1];
                computer.addComponent(new Component(component.getName(), component.getPrice()));
            } catch (Exception e) {
                System.out.println("❌ Invalid choice, try again.");

            }

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
                System.out.print(computer.getDescription());
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