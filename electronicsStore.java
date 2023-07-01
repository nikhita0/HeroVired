import java.util.ArrayList;
import java.util.Scanner;

class electronicsStore {
    private String name;
    private String specs;
    private double price;
    private int quant;

    public electronicsStore(String name, String specs, double price, int quant) {
        this.name = name;
        this.specs = specs;
        this.price = price;
        this.quant = quant;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getspecs() {
        return specs;
    }

    public void setspecs(String specs) {
        this.specs = specs;
    }

    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public int getquant() {
        return quant;
    }

    public void setquant(int quant) {
        this.quant = quant;
    }

    public void addquant(int quant) {
        this.quant += quant;
    }

    public void deletequant(int quant) {
        if (this.quant >= quant) {
            this.quant -= quant;
        } else {
            System.out.println("Insufficient quant available.");
        }
    }

    public void displayStoreDetails() {
        System.out.println("electronicsStore Name: " + name);
        System.out.println("specs: " + specs);
        System.out.println("price: $" + price);
        System.out.println("quant available: " + quant);
    }
}

public class storeManagement{
    private ArrayList<electronicsStore> electronicsStores;

    public storeManagement() {
        electronicsStores = new ArrayList<>();
    }

    public void addToStore(electronicsStore electronicsStore) {
        electronicsStores.add(electronicsStore);
    }

    public void viewStoreList() {
        if (electronicsStores.isEmpty()) {
            System.out.println("No electronicsStores in the store.");
        } else {
            System.out.println("electronicsStore List:");
            for (electronicsStore electronicsStore : electronicsStores) {
                System.out.println(electronicsStore.getName());
            }
        }
    }

    public void viewStoreCount(String electronicsStoreName) {
        for (electronicsStore electronicsStore : electronicsStores) {
            if (electronicsStore.getName().equalsIgnoreCase(electronicsStoreName)) {
                System.out.println("quant of " + electronicsStoreName + ": " + electronicsStore.getquant());
                return;
            }
        }
        System.out.println("electronicsStore not found in the store.");
    }

    public void viewStoreDetails(String electronicsStoreName) {
        for (electronicsStore electronicsStore : electronicsStores) {
            if (electronicsStore.getName().equalsIgnoreCase(electronicsStoreName)) {
                electronicsStore.displayStoreDetails();
                return;
            }
        }
        System.out.println("electronicsStore not found in the store.");
    }

    public void editStore(String electronicsStoreName) {
        for (electronicsStore electronicsStore : electronicsStores) {
            if (electronicsStore.getName().equalsIgnoreCase(electronicsStoreName)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new specs: ");
                String newspecs = scanner.nextLine();
                System.out.print("Enter new price: $");
                double newprice = scanner.nextDouble();
                electronicsStore.setName(newName);
                electronicsStore.setspecs(newspecs);
                electronicsStore.setprice(newprice);
                System.out.println("electronicsStore details updated successfully.");
                return;
            }
        }
        System.out.println("electronicsStore not found in the store.");
    }

    public void update(String electronicsStoreName, int quant) {
        for (electronicsStore electronicsStore : electronicsStores) {
            if (electronicsStore.getName().equalsIgnoreCase(electronicsStoreName)) {
                if (quant >= 0) {
                    electronicsStore.addquant(quant);
                    System.out.println(quant + " units of " + electronicsStoreName + " added to store.");
                } else {
                    electronicsStore.deletequant(-quant);
                    System.out.println(-quant + " units of " + electronicsStoreName + " deleted from store.");
                }
                return;
            }
        }
        System.out.println("electronicsStore not found in the store.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        storeManagement store = new storeManagement();

        while (true) {
            System.out.println("----- store Management System Menu -----");
            System.out.println("1. View electronicsStore List");
            System.out.println("2. View electronicsStore Count");
            System.out.println("3. View electronicsStore Details");
            System.out.println("4. Edit electronicsStore");
            System.out.println("5. Update store");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    store.viewStoreList();
                    break;
                case 2:
                    System.out.print("Enter electronicsStore name: ");
                    String electronicsCountName = scanner.nextLine();
                    store.viewStoreCount(electronicsCountName);
                    break;
                case 3:
                    System.out.print("Enter electronicsStore name: ");
                    String electronicsStoreDetailsName = scanner.nextLine();
                    store.viewStoreDetails(electronicsStoreDetailsName);
                    break;
                case 4:
                    System.out.print("Enter electronicsStore name: ");
                    String storeEditedName = scanner.nextLine();
                    store.editStore(storeEditedName);
                    break;
                case 5:
                    System.out.print("Enter electronicsStore name: ");
                    String electronicsUpdatedName = scanner.nextLine();
                    System.out.print("Enter quant (-ve for deletion): ");
                    int quant = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    store.update(electronicsUpdatedName, quant);
                    break;
                case 6:
                    System.out.println("Exiting store Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();  
            
        }
    }
}

