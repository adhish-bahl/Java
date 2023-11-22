abstract class Sneaker {
    private String brand;
    private String name;
    private float price;
    private int quantity;

    Sneaker(String brand, String name, float price, int quantity) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract void displaySpecialFeatures();

    public void displaySneakerInfo() {
        System.out.println("\nBrand: " + brand);
        System.out.println("Model: " + name);
        System.out.println("Price: Rs. " + price);
        System.out.println("Quantity: " + quantity);
    }
}

class LimitedSneaker extends Sneaker {
    private String edition;

    LimitedSneaker(String brand, String name, float price, int quantity, String edition) {
        super(brand, name, price, quantity);
        this.edition = edition;
    }

    @Override
    public void displaySpecialFeatures() {
        System.out.println("Limited Edition: " + edition);
    }
}

final class ExclusiveSneaker extends Sneaker {
    private String exclusivityLevel;

    ExclusiveSneaker(String brand, String name, float price, int quantity, String exclusivityLevel) {
        super(brand, name, price, quantity);
        this.exclusivityLevel = exclusivityLevel;
    }

    @Override
    public void displaySpecialFeatures() {
        System.out.println("Exclusivity Level: " + exclusivityLevel);
    }
}

public class Lab3_03 {
    public static void main(String[] args) {
        LimitedSneaker limitedSneaker = new LimitedSneaker("Nike", "Air Max 1", 20000.00f, 10, "Gold Edition");
        ExclusiveSneaker exclusiveSneaker = new ExclusiveSneaker("Adidas", "Ultra Boost", 32000.00f, 5, "VIP Access");

        System.out.println("Limited Sneaker Info:");
        limitedSneaker.displaySneakerInfo();
        limitedSneaker.displaySpecialFeatures();

        System.out.println("\n===============================\n");

        System.out.println("Exclusive Sneaker Info:");
        exclusiveSneaker.displaySneakerInfo();
        exclusiveSneaker.displaySpecialFeatures();
    }
}
