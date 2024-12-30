import java.util.ArrayList;
import java.util.Scanner;

// Class to represent an inventory item
class Item {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Item(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    // Display the item details
    public void displayItem() {
        System.out.printf("ID: %d | Name: %s | Quantity: %d | Price: %.2f%n", id, name, quantity, price);
    }
}


public class InventoryManagementSystem {
    private static ArrayList<Item> inventory = new ArrayList<>();
    private static int currentId = 1; // Auto-incrementing ID

    // Method to add an item
    private static void addItem(Scanner scanner) {
        System.out.print("Enter Item Name: ");
        String name = scanner.next();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        Item newItem = new Item(currentId++, name, quantity, price);
        inventory.add(newItem);
        System.out.println("Item added successfully!");
    }

    // Method to view all items
    private static void viewItems() {
        if (inventory.isEmpty()) {
            System.out.println("No items in the inventory.");
        } else {
            System.out.println("Current Inventory:");
            for (Item item : inventory) {
                item.displayItem();
            }
        }
    }

    // Method to update an item
    private static void updateItem(Scanner scanner) {
        System.out.print("Enter Item ID to update: ");
        int id = scanner.nextInt();
        Item itemToUpdate = null;

        for (Item item : inventory) {
            if (item.getId() == id) {
                itemToUpdate = item;
                break;
            }
        }

        if (itemToUpdate == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Enter new details (leave blank to keep unchanged):");
        System.out.print("Enter new Name: ");
        String name = scanner.next();
        if (!name.isEmpty()) {
            itemToUpdate.setName(name);
        }

        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();
        itemToUpdate.setQuantity(quantity);

        System.out.print("Enter new Price: ");
        double price = scanner.nextDouble();
        itemToUpdate.setPrice(price);

        System.out.println("Item updated successfully!");
    }

    // Method to delete an item
    private static void deleteItem(Scanner scanner) {
        System.out.print("Enter Item ID to delete: ");
        int id = scanner.nextInt();

        boolean removed = inventory.removeIf(item -> item.getId() == id);

        if (removed) {
            System.out.println("Item deleted successfully!");
        } else {
            System.out.println("Item not found.");
        }
    }

    // Main menu to display options
    private static void displayMenu() {
        System.out.println("\nInventory Management System");
        System.out.println("1. Add Item");
        System.out.println("2. View Items");
        System.out.println("3. Update Item");
        System.out.println("4. Delete Item");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    viewItems();
                    break;
                case 3:
                    updateItem(scanner);
                    break;
                case 4:
                    deleteItem(scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
