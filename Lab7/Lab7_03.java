import java.util.Scanner;

class Sneaker {
    private String brand;
    private String model;
    private double price;
    private boolean isLimitedEdition;

    public Sneaker(String brand, String model, double price, boolean isLimitedEdition) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.isLimitedEdition = isLimitedEdition;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public boolean isLimitedEdition() {
        return isLimitedEdition;
    }

    @Override
    public String toString() {
        return brand + " " + model + " - Rs. " + price + (isLimitedEdition ? " (Limited Edition)" : "");
    }
}

public class Lab7_03 {
    private static final int MAX_SNEAKERS = 100;
    private static final Sneaker[] sneakers = new Sneaker[MAX_SNEAKERS];
    private static int numberOfSneakers = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Enter Sneaker Details");
            System.out.println("2. Display All Sneakers");
            System.out.println("3. Display Limited Edition Sneakers");
            System.out.println("4. Sort Sneakers by Price");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    enterSneakerDetails();
                    break;

                case 2:
                    displaySneakers();
                    break;

                case 3:
                    displayLimitedEditionSneakers();
                    break;

                case 4:
                    sortSneakersByPrice();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void enterSneakerDetails() {
        if (numberOfSneakers < MAX_SNEAKERS) {
            System.out.print("\nEnter details for the Sneaker:");
            System.out.print("\nBrand: ");
            String brand = scanner.nextLine();

            System.out.print("Model: ");
            String model = scanner.nextLine();

            System.out.print("Price: Rs. ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Is Limited Edition (true/false): ");
            boolean isLimitedEdition = scanner.nextBoolean();
            scanner.nextLine();

            sneakers[numberOfSneakers++] = new Sneaker(brand, model, price, isLimitedEdition);
            System.out.println("Sneaker details added successfully!\n\n");
        } else {
            System.out.println("Maximum number of sneakers reached. Cannot add more sneakers.");
        }
    }

    private static void displaySneakers() {
        System.out.println("\nAll Sneakers:");
        for (int i = 0; i < numberOfSneakers; i++) {
            System.out.println(sneakers[i]);
        }
    }

    private static void displayLimitedEditionSneakers() {
        System.out.println("\nLimited Edition Sneakers:");
        for (int i = 0; i < numberOfSneakers; i++) {
            if (sneakers[i].isLimitedEdition()) {
                System.out.println(sneakers[i]);
            }
        }
    }

    private static void sortSneakersByPrice() {
        for (int i = 0; i < numberOfSneakers - 1; i++) {
            for (int j = 0; j < numberOfSneakers - i - 1; j++) {
                if (sneakers[j].getPrice() > sneakers[j + 1].getPrice()) {
                    // Swap sneakers[j] and sneakers[j + 1]
                    Sneaker temp = sneakers[j];
                    sneakers[j] = sneakers[j + 1];
                    sneakers[j + 1] = temp;
                }
            }
        }

        System.out.println("\nSneakers Sorted by Price:");
        displaySneakers();
    }
}
