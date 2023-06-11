import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class library{
    private static int booksMaxSize = 5; 
    private static int numAttribute = 5; 
    private static int indexBookId = 0;  
    private static int statusIndex = 3; 
    private static int idexIssue = 4; 

    private static int numDays = 7;
    private static double lateCharge = 10.0; 
    public static String[][] catalog = new String[booksMaxSize][numAttribute]; 
    private static int bookCount = 5; 

    public static void main(String[] args) {
        addBook();
        display();
    }

     static void display() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Welcome to the Unique Library");
            System.out.println("--------------------------------------------------");
            System.out.println("1. View the complete list of Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(System.console().readLine());
            System.out.println();

            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    issue();
                    break;
                case 3:
                    bookReturn();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the Application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    
    public static void displayBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the catalog.");
        } else {
            System.out.println("Book ID\tTitle\tAuthor\tAvailability\tIssue Date");
            for (int i = 0; i < bookCount; i++) {
                for (int j = 0; j < numAttribute; j++) {
                    System.out.print(catalog[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }
    public static void addBook(){
            catalog[0][0] = "123";
            catalog[0][1] = "Computer Science1";
            catalog[0][2] = "XXXX";
            catalog[0][3] = "Available";
            catalog[0][4] = "";

            catalog[1][0] = "124";
            catalog[1][1] = "Computer Science2";
            catalog[1][2] = "XXXX";
            catalog[1][3] = "Not-Available";
            catalog[1][4] = "09-02-2023";

            catalog[2][0] = "125";
            catalog[2][1] = "Computer Science3";
            catalog[2][2] = "XXXX";
            catalog[2][3] = "Available";
            catalog[2][4] = "";

            catalog[3][0] = "126";
            catalog[3][1] = "Computer Science4";
            catalog[3][2] = "XXXX";
            catalog[3][3] = "Not-Available";
            catalog[3][4] = "09-04-2023";

            catalog[4][0] = "133";
            catalog[4][1] = "Computer Science5";
            catalog[4][2] = "XXXX";
            catalog[4][3] = "Available";
            catalog[4][4] = "";
}
    private static void issue() {
        if (bookCount == 0) {
            System.out.println("No books in the catalog.");
        } else {
            System.out.print("Enter the Book ID to issue: ");
            String bookID = System.console().readLine();
            boolean bookFound = false;

            for (int i = 0; i < bookCount; i++) {
                if (catalog[i][indexBookId].equals(bookID)) {
                    if (catalog[i][statusIndex].equals("available")) {
                        System.out.print("Enter the Student ID: ");
                        String studentID = System.console().readLine();
                        catalog[i][statusIndex] = studentID;
                        catalog[i][idexIssue] = LocalDate.now().toString();
                        System.out.println("Book issued successfully.");
                    } else {
                        System.out.println("Book is already issued to another student.");
                    }
                    bookFound = true;
                    break;
                }
            }

            if (!bookFound) {
                System.out.println("Book not found in the catalog.");
            }
        }
    }

    private static void bookReturn() {
        if (bookCount == 0) {
            System.out.println("No books in the catalog.");
        } else {
            System.out.print("Enter the Book ID to return: ");
            String bookID = System.console().readLine();
            boolean bookFound = false;

            for (int i = 0; i < bookCount; i++) {
                if (catalog[i][indexBookId].equals(bookID)) {
                    if (!catalog[i][statusIndex].equals("available")) {
                        LocalDate issueDate = LocalDate.parse(catalog[i][idexIssue]);
                        LocalDate currentDate = LocalDate.now();
                        long daysDiff = ChronoUnit.DAYS.between(issueDate, currentDate);

                        if (daysDiff <= numDays) {
                            catalog[i][statusIndex] = "available";
                            catalog[i][idexIssue] = "Null";
                            System.out.println("Book returned successfully.");
                        } else {
                            double lateCharges = (daysDiff - numDays) * lateCharge;
                            System.out.println("Book is returned late. Late charges: Rs. " + lateCharges);
                            catalog[i][statusIndex] = "available";
                            catalog[i][idexIssue] = "Null";
                        }
                    } else {
                        System.out.println("Book is not currently issued to any student.");
                    }
                    bookFound = true;
                    break;
                }
            }

            if (!bookFound) {
                System.out.println("Book not found in the catalog.");
            }
        }
    }
}
