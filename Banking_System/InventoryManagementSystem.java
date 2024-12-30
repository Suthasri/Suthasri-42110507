import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Item {
    String name;
    int quantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Quantity: " + quantity;
    }
}

public class InventoryManagementSystem {

    // Main method
    public static void main(String[] args) {
        ArrayList<Item> inventory = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu
            displayMenu();

            // Get user choice
            int choice = getValidIntegerInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    addItem(inventory, scanner);
                    break;
                case 2:
                    updateItem(inventory, scanner);
                    break;
                case 3:
                    viewInventory(inventory);
                    break;
                case 4:
                    deleteItem(inventory, scanner);
                    break;
                case 5:
                    System.out.println("Exiting Inventory Management System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to display the menu
    private static void displayMenu() {
        System.out.println("\nInventory Management System");
        System.out.println("1. Add Item");
        System.out.println("2. Update Item");
        System.out.println("3. View Inventory");
        System.out.println("4. Delete Item");
        System.out.println("5. Exit");
    }

    // Method to add an item
    private static void addItem(ArrayList<Item> inventory, Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Item name cannot be empty.");
            return;
        }

        int quantity = getValidIntegerInput(scanner, "Enter item quantity: ");
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        inventory.add(new Item(name, quantity));
        System.out.println("Item added successfully.");
    }

    // Method to update an item's quantity
    private static void updateItem(ArrayList<Item> inventory, Scanner scanner) {
        System.out.print("Enter item name to update: ");
        String name = scanner.nextLine().trim();

        boolean found = false;
        for (Item item : inventory) {
            if (item.name.equalsIgnoreCase(name)) {
                int newQuantity = getValidIntegerInput(scanner, "Enter new quantity: ");
                if (newQuantity < 0) {
                    System.out.println("Quantity cannot be negative.");
                    return;
                }

                item.quantity = newQuantity;
                System.out.println("Item updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // Method to view all items in the inventory
    private static void viewInventory(ArrayList<Item> inventory) {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Current Inventory:");
            for (Item item : inventory) {
                System.out.println(item);
            }
        }
    }

    // Method to delete an item
    private static void deleteItem(ArrayList<Item> inventory, Scanner scanner) {
        System.out.print("Enter item name to delete: ");
        String name = scanner.nextLine().trim();

        Iterator<Item> iterator = inventory.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.name.equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Item deleted successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // Utility method to get a valid integer input
    private static int getValidIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
