package sneakers;

public final class ExclusiveSneaker<T> extends Sneaker<T> {
    private T exclusivityLevel;

    public ExclusiveSneaker(String brand, String name, float price, int quantity, T exclusivityLevel) {
        super(brand, name, price, quantity);
        this.exclusivityLevel = exclusivityLevel;
    }

    @Override
    public void displaySpecialFeatures() {
        System.out.println("Exclusivity Level: " + exclusivityLevel);
    }
}
