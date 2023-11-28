package sneakers;

public class LimitedSneaker extends Sneaker {
    private String edition;

    public LimitedSneaker(String brand, String name, float price, int quantity, String edition) {
        super(brand, name, price, quantity);
        this.edition = edition;
    }

    @Override
    public void displaySpecialFeatures() {
        System.out.println("Limited Edition: " + edition);
    }
}