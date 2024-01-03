package sneakers;

public abstract class Sneaker<T> implements SpecialFeatures<T> {
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
