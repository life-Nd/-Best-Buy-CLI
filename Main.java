import java.io.IOException;

public class Main {
    private static MarketSpace marketSpace = MarketSpace.getInstance();

    public static void main(String[] args) throws IOException {
        marketSpace.loadComponents();
        marketSpace.startCLI();
    }
}
