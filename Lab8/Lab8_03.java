import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Sneaker {
    String brand;
    String name;
    int size;
    float price;
    int quantity;

    Sneaker(String name, String brand, int size, float price, int quantity) {
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }
}

class Customer {
    String name;
    int age;
    float totalAmount;

    Customer(String name, int age) {
        this.name = name;
        this.age = age;
        this.totalAmount = 0.0f;
    }
}

public class Lab8_03 {
    private static Sneaker createSneaker(String name, String brand, int size, float price, int quantity) {
        return new Sneaker(name, brand, size, price, quantity);
    }

    private static Customer createCustomer(String name, int age) {
        return new Customer(name, age);
    }

    private static void insertIntoInventory(List<Sneaker> inventory) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Sneaker details:");
        System.out.print("Brand: ");
        String sneakerBrand = scanner.next();
        System.out.print("Name: ");
        String sneakerName = scanner.next();
        System.out.print("Size: ");
        int sneakerSize = scanner.nextInt();
        System.out.print("Price: ");
        float sneakerPrice = scanner.nextFloat();
        System.out.print("Quantity: ");
        int sneakerQuantity = scanner.nextInt();

        System.out.println("\n==============================================================\n\n");

        Sneaker newSneaker = createSneaker(sneakerName, sneakerBrand, sneakerSize, sneakerPrice, sneakerQuantity);
        inventory.add(newSneaker);
    }

    private static void displayInventory(List<Sneaker> inventory) {
        System.out.println("Sneakers in the inventory:");
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Sneaker sneaker : inventory) {
                System.out.printf("%s %s | %d | Rs. %.2f | %d pcs\n", sneaker.brand, sneaker.name, sneaker.size,
                        sneaker.price, sneaker.quantity);
            }
        }
        System.out.println("\n==============================================================\n\n");
    }

    private static void displayCustomers(List<Customer> customers) {
        System.out.println("Customer details:");
        if (customers.isEmpty()) {
            System.out.println("Customer list is empty.");
        } else {
            for (Customer customer : customers) {
                System.out.printf(" %s | %d years old | Rs. %.2f\n", customer.name, customer.age, customer.totalAmount);
            }
        }
        System.out.println("\n==============================================================\n\n");
    }

    private static void deleteSneaker(List<Sneaker> inventory) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sneaker name to delete: ");
        String name = scanner.next();

        boolean removed = inventory.removeIf(sneaker -> sneaker.name.equals(name));

        if (removed) {
            System.out.println("Sneaker " + name + " deleted.");
        } else {
            System.out.println("Sneaker " + name + " not found in the inventory.");
        }
        System.out.println("\n==============================================================\n\n");
    }

    private static void deleteCustomer(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name to delete: ");
        String name = scanner.next();

        boolean removed = customers.removeIf(customer -> customer.name.equals(name));

        if (removed) {
            System.out.println("Customer " + name + " deleted.");
        } else {
            System.out.println("Customer " + name + " not found.");
        }
        System.out.println("\n==============================================================\n\n");
    }

    private static void buySneaker(List<Sneaker> inventory, List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        String customerName, sneakerName;
        int customerAge, quantity;

        System.out.println("Available Sneakers for Purchase:");
        displayInventory(inventory);

        System.out.print("Enter the name of the sneaker you want to buy: ");
        sneakerName = scanner.next();

        Sneaker selectedSneaker = inventory.stream().filter(sneaker -> sneaker.name.equals(sneakerName)).findFirst()
                .orElse(null);

        if (selectedSneaker == null) {
            System.out.println("Sneaker not found in the inventory.");
            System.out.println("\n==============================================================\n\n");
            return;
        }

        System.out.print("Enter the quantity you want to buy: ");
        quantity = scanner.nextInt();

        if (quantity > selectedSneaker.quantity) {
            System.out.println("Insufficient quantity in stock.");
            System.out.println("\n==============================================================\n\n");
            return;
        }

        System.out.println("Enter customer details:");
        System.out.print("Name: ");
        customerName = scanner.next();
        System.out.print("Age: ");
        customerAge = scanner.nextInt();
        System.out.println("\n==============================================================\n\n");

        customers.add(createCustomer(customerName, customerAge));

        float totalAmount = quantity * selectedSneaker.price;

        selectedSneaker.quantity -= quantity;

        for (Customer currentCustomer : customers) {
            if (currentCustomer.name.equals(customerName)) {
                currentCustomer.totalAmount += totalAmount;
                break;
            }
        }

        System.out.println("  Bill for " + customerName + ":\n" +
                "\t\t\tSneakShop\n\t\t\t===========\n\n " +
                selectedSneaker.name + "\t\t " + quantity + "\t\t Rs. " + selectedSneaker.price + "\n\n" +
                "\t\t\t\tTotal Amount: Rs. " + totalAmount + "\n\n\t\t\tThank you");
        System.out.println("\n==============================================================\n\n");
    }

    public static void main(String[] args) {
        List<Sneaker> inventory = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        int choice;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Sneaker to Inventory");
            System.out.println("2. Display Sneaker Inventory");
            System.out.println("3. Buy Sneaker");
            System.out.println("4. Display Customers");
            System.out.println("5. Delete Customer");
            System.out.println("6. Delete Sneaker");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            System.out.println("\n==============================================================\n\n\n");

            switch (choice) {
                case 1:
                    insertIntoInventory(inventory);
                    break;

                case 2:
                    displayInventory(inventory);
                    break;

                case 3:
                    buySneaker(inventory, customers);
                    break;

                case 4:
                    displayCustomers(customers);
                    break;

                case 5:
                    deleteCustomer(customers);
                    break;

                case 6:
                    deleteSneaker(inventory);
                    break;

                case 7:
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 7);
    }
}
