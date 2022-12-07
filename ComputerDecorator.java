// Abstract decorator class for computer
class ComputerDecorator extends Component {
    protected Component computer;
    public ComputerDecorator(String name, double price) {
        super(name, price);
        this.computer = new Component(name, price);
    }

    @Override
    public String getName() {
        return computer.getName();
    }

    @Override
    public double getPrice() {
        return computer.getPrice();
    }
}