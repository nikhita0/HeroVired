import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;


class MenuItem {
    private int id;
    private String name;
    private double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and setters for MenuItem attributes
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Order {
    private int orderId;
    private List<MenuItem> items;
    private Date orderDate;
    private double totalAmount;
    private boolean isCancelled;

    public Order(int orderId, List<MenuItem> items) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = new Date();
        this.totalAmount = calculateTotalAmount();
        this.isCancelled = false;
    }

    public void cancelOrder() {
        this.isCancelled = true;
    }

    private double calculateTotalAmount() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    // Getters for Order attributes
    public int getOrderId() {
        return orderId;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
     public boolean isCancelled() {
        return isCancelled;
    }
}

class CollectionReport {
    private Date date;
    private double totalCollection;

    public CollectionReport(Date date, double totalCollection) {
        this.date = date;
        this.totalCollection = totalCollection;
    }

    // Getters for CollectionReport attributes
     public Date getDate() {
        return date;
    }

    public double getTotalCollection() {
        return totalCollection;
    }
}

class FileManager {
    private static final String ORDERS_FILE = "orders.csv";
    private static final String COLLECTION_REPORT_FILE = "collection_report.csv";

    public static List<MenuItem> loadMenu() {
        // Implement code to load menu items from MENU_FILE
        return new ArrayList<>();
    }

    public static List<Order> loadOrders() {
        // Implement code to load orders from ORDERS_FILE
        return new ArrayList<>();
    }

    public static List<CollectionReport> loadCollectionReport() {
        // Implement code to load collection reports from COLLECTION_REPORT_FILE
        return new ArrayList<>();
    }

    public static void saveOrders(List<Order> orders) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDERS_FILE))) {
            for (Order order : orders) {
                writer.println(order.getOrderId() + "," + order.getOrderDate() + "," + order.getTotalAmount() + "," + order.isCancelled());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCollectionReport(CollectionReport report) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COLLECTION_REPORT_FILE, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(report.getDate());
            writer.println(formattedDate + "," + report.getTotalCollection());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

public class RestaurantApp {
    private static List<MenuItem> menu;
    private static List<Order> orders;
    private static List<CollectionReport> collectionReports;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu = FileManager.loadMenu();
        orders = FileManager.loadOrders();
        collectionReports = FileManager.loadCollectionReport();
       
        
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    placeOrder(scanner);
                    break;
                case 2:
                    cancelOrder(scanner);
                    break;
                case 3:
                    generateCollectionReport();
                    break;
                case 4:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Quick-Bites Restaurant Management");
        System.out.println("1. Place Order");
        System.out.println("2. Cancel Order");
        System.out.println("3. Generate Collection Report");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

   private static void placeOrder(Scanner scanner) {
    System.out.println("Menu Items:");
    for (MenuItem item : menu) {
        System.out.println(item.getId() + ". " + item.getName() + " - $" + item.getPrice());
    }
    
    System.out.print("Enter the IDs of menu items (comma-separated): ");
    String input = scanner.next();
    String[] itemIds = input.split(",");

    List<MenuItem> orderedItems = new ArrayList<>();
    double totalAmount = 0.0;

    for (String itemId : itemIds) {
        int id = Integer.parseInt(itemId);
        MenuItem menuItem = menu.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        if (menuItem != null) {
            orderedItems.add(menuItem);
            totalAmount += menuItem.getPrice();
        }
    }

    Order order = new Order(orders.size() + 1, orderedItems);
    orders.add(order);
    FileManager.saveOrders(orders);

    System.out.println("Order placed successfully!");
}

private static void cancelOrder(Scanner scanner) {
    System.out.print("Enter the order ID to cancel: ");
    int orderId = scanner.nextInt();

    Order orderToCancel = orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);

    if (orderToCancel != null && !orderToCancel.isCancelled()) {
        orderToCancel.cancelOrder();
        FileManager.saveOrders(orders);
        System.out.println("Order cancelled successfully.");
    } else {
        System.out.println("Order not found or already cancelled.");
    }
}


   private static void generateCollectionReport() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    System.out.print("Enter the date (yyyy-MM-dd) for the collection report: ");
    String dateString = scanner.next();

    try {
        Date selectedDate = sdf.parse(dateString);
        double totalCollection = orders.stream()
            .filter(order -> !order.isCancelled() && sdf.format(order.getOrderDate()).equals(dateString))
            .mapToDouble(Order::getTotalAmount)
            .sum();

        CollectionReport report = new CollectionReport(selectedDate, totalCollection);
        collectionReports.add(report);
        FileManager.saveCollectionReport(report);

        System.out.println("Collection report for " + dateString + ": $" + totalCollection);
    } catch (ParseException e) {
        System.out.println("Invalid date format. Use yyyy-MM-dd.");
    }
}

}
