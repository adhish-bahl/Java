package sneakers;

public final class ExclusiveSneaker extends Sneaker {
    private String exclusivityLevel;

    public ExclusiveSneaker(String brand, String name, float price, int quantity, String exclusivityLevel) {
        super(brand, name, price, quantity);
        this.exclusivityLevel = exclusivityLevel;
    }

    @Override
    public void displaySpecialFeatures() {
        System.out.println("Exclusivity Level: " + exclusivityLevel);
    }
}
