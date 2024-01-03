import java.util.Scanner;
import sneakers.*;

public class Lab6_03 {
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

        LimitedSneaker<String> limitedSneaker = new LimitedSneaker<>(limitedBrand, limitedModel, limitedPrice, limitedQuantity, limitedEdition);
        
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
        
        ExclusiveSneaker<String> exclusiveSneaker = new ExclusiveSneaker<>(exclusiveBrand, exclusiveModel, exclusivePrice, exclusiveQuantity, exclusiveExclusivityLevel);

        System.out.println("Limited Sneaker Info:");
        limitedSneaker.displaySneakerInfo();
        limitedSneaker.displaySpecialFeatures();

        System.out.println("\n===============================\n");

        System.out.println("Exclusive Sneaker Info:");
        exclusiveSneaker.displaySneakerInfo();
        exclusiveSneaker.displaySpecialFeatures();
    }
}