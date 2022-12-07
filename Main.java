
// Student Name: Ralph NDUWIMANA
// Student ID: 261066154

import java.io.IOException;

/**
 * In this class, we first create a static reference to the MarketSpace instance
 * using the getInstance() method. This ensures that we always have a reference
 * to the same MarketSpace object throughout the application.
 * 
 * In the main() method, we first call the loadComponents() method on the
 * MarketSpace instance to load the components from the data file. This is done
 * to initialize the MarketSpace with the available components that can be used
 * to create Computer objects.
 * 
 * Next, we call the startCLI() method on the MarketSpace instance to start the
 * command-line interface (CLI) of the application. The CLI allows users to interact with the
 * MarketSpace and its Computer objects using text-based commands, which makes
 * it easy and efficient to use.
 * 

 */
public class Main {
    // create a static reference to the MarketSpace instance
    private static MarketSpace marketSpace = MarketSpace.getInstance();

    public static void main(String[] args) throws IOException {
        // load the components from the data file
        marketSpace.loadComponents();

        // start the command-line interface (CLI)
        marketSpace.startCLI();
    }
}