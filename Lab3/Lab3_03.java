import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Limited Edition Sneaker Details:");
        System.out.print("Brand: ");
        String limitedBrand = scanner.nextLine();
        System.out.print("Model: ");
        String limitedModel = scanner.nextLine();
        System.out.print("Price: Rs. ");
        float limitedPrice = scanner.nextFloat();
        System.out.print("Quantity: ");
        int limitedQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Edition: ");
        String limitedEdition = scanner.nextLine();

        LimitedSneaker limitedSneaker = new LimitedSneaker(limitedBrand, limitedModel, limitedPrice, limitedQuantity, limitedEdition);

        System.out.println("\nEnter Exclusive Sneaker Details:");
        System.out.print("Brand: ");
        String exclusiveBrand = scanner.nextLine();
        System.out.print("Model: ");
        String exclusiveModel = scanner.nextLine();
        System.out.print("Price: Rs. ");
        float exclusivePrice = scanner.nextFloat();
        System.out.print("Quantity: ");
        int exclusiveQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Exclusivity Level: ");
        String exclusiveExclusivityLevel = scanner.nextLine();
        
        System.out.println("\n===============================\n\n\n");

        ExclusiveSneaker exclusiveSneaker = new ExclusiveSneaker( exclusiveBrand, exclusiveModel, exclusivePrice, exclusiveQuantity, exclusiveExclusivityLevel );

        System.out.println("Limited Sneaker Info:");
        limitedSneaker.displaySneakerInfo();
        limitedSneaker.displaySpecialFeatures();

        System.out.println("\n===============================\n");

        System.out.println("Exclusive Sneaker Info:");
        exclusiveSneaker.displaySneakerInfo();
        exclusiveSneaker.displaySpecialFeatures();
    }
}