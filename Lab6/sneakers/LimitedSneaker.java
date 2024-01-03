package sneakers;

public class LimitedSneaker<T> extends Sneaker<T> {
    private T edition;

    public LimitedSneaker(String brand, String name, float price, int quantity, T edition) {
        super(brand, name, price, quantity);
        this.edition = edition;
    }

    @Override
    public void displaySpecialFeatures() {
        System.out.println("Limited Edition: " + edition);
    }
}
