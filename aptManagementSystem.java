import java.util.ArrayList;
import java.util.Scanner;

class aptManagementSystem{
    private ArrayList<Visitor> visitors;
    private ArrayList<apt> apts;

    public aptManagementSystem() {
        visitors = new ArrayList<>();
        apts = new ArrayList<>();
    }

    private class Visitor {
        private String name;
        private int age;
        private String contactNumber;

        public Visitor(String name, int age, String contactNumber) {
            this.name = name;
            this.age = age;
            this.contactNumber = contactNumber;
        }

        // Getter and Setter methods
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }
    }

    private class apt {
        private Visitor visitor;
        private String date;
        private String time;
        private boolean visited;

        public apt(Visitor visitor, String date, String time) {
            this.visitor = visitor;
            this.date = date;
            this.time = time;
            this.visited = false;
        }

        // Getter and Setter methods
        public Visitor getVisitor() {
            return visitor;
        }

        public void setVisitor(Visitor visitor) {
            this.visitor = visitor;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public void display() {
            System.out.println("Visitor Name: " + visitor.getName());
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("Visited: " + (visited ? "Yes" : "No"));
        }
    }

    public void addingTovisitorList(String name, int age, String contactNumber) {
        Visitor visitor = new Visitor(name, age, contactNumber);
        visitors.add(visitor);
    }

    public void visitorsLists() {
        if (visitors.isEmpty()) {
            System.out.println("No visitors in the list.");
        } else {
            System.out.println("Visitor List:");
            for (Visitor visitor : visitors) {
                System.out.println(visitor.getName());
            }
        }
    }

    public void visitorDetailesEdited(String visitorName) {
        for (Visitor visitor : visitors) {
            if (visitor.getName().equalsIgnoreCase(visitorName)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new age: ");
                int newAge = scanner.nextInt();
                scanner.nextLine();  
                System.out.print("Enter new contact number: ");
                String newContactNumber = scanner.nextLine();
                visitor.setName(newName);
                visitor.setAge(newAge);
                visitor.setContactNumber(newContactNumber);
                System.out.println("Visitor display updated successfully.");
                return;
            }
        }
        System.out.println("Visitor not found in the list.");
    }

    public void scheduleVisitor(String date) {
        System.out.println("apt Schedule for " + date + ":");
        boolean hasapts = false;
        for (apt apt : apts) {
            if (apt.getDate().equalsIgnoreCase(date)) {
                apt.display();
                hasapts = true;
            }
        }
        if (!hasapts) {
            System.out.println("No apts scheduled for " + date);
        }
    }

    public void aptToBook(String visitorName, String date, String time) {
        Visitor visitor = null;
        for (Visitor v : visitors) {
            if (v.getName().equalsIgnoreCase(visitorName)) {
                visitor = v;
                break;
            }
        }
        if (visitor == null) {
            System.out.println("Visitor not found in the list. Please add the visitor first.");
            return;
        }

        for (apt apt : apts) {
            if (apt.getDate().equalsIgnoreCase(date) && apt.getTime().equalsIgnoreCase(time)) {
                System.out.println("apt slot already booked. Please choose a different time slot.");
                return;
            }
        }

        apt apt = new apt(visitor, date, time);
        apts.add(apt);
        System.out.println("apt booked successfully.");
    }

    public void editapt(String visitorName, String date, String time) {
        for (apt apt : apts) {
            if (apt.getVisitor().getName().equalsIgnoreCase(visitorName) &&
                    apt.getDate().equalsIgnoreCase(date) && apt.getTime().equalsIgnoreCase(time)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. Update apt after visit");

                System.out.println("2. Cancel apt");

                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  
                switch (choice) {
                    case 1:
                        apt.setVisited(true);
                        System.out.println("apt updated successfully.");
                        break;
                    case 2:
                        apts.remove(apt);
                        System.out.println("apt canceled successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                return;
            }
        }
        System.out.println("apt not found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        aptManagementSystem appoint = new aptManagementSystem();

        while (true) {
            System.out.println("----- apt Management System Menu -----");
            System.out.println("1. View Visitor List");
            System.out.println("2. Add new Visitor");
            System.out.println("3. Edit Visitor display");
            System.out.println("4. View apt Schedule for a Day");
            System.out.println("5. Book an apt");
            System.out.println("6. Edit/Cancel apt");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    appoint.visitorsLists();
                    break;
                case 2:
                    System.out.print("Enter visitor name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter visitor age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();  
                    System.out.print("Enter visitor contact number: ");
                    String contactNumber = scanner.nextLine();
                    appoint.addingTovisitorList(name, age, contactNumber);
                    System.out.println("Visitor added successfully.");
                    break;
                case 3:
                    System.out.print("Enter visitor name: ");
                    String visitorName = scanner.nextLine();
                    appoint.visitorDetailesEdited(visitorName);
                    break;
                case 4:
                    System.out.print("Enter date: ");
                    String date = scanner.nextLine();
                    appoint.scheduleVisitor(date);
                    break;
                case 5:
                    System.out.print("Enter visitor name: ");
                    String bookVisitorName = scanner.nextLine();
                    System.out.print("Enter date: ");
                    String bookDate = scanner.nextLine();
                    System.out.print("Enter time: ");
                    String bookTime = scanner.nextLine();
                    appoint.aptToBook(bookVisitorName, bookDate, bookTime);
                    break;
                case 6:
                    System.out.print("Enter visitor name: ");
                    String editVisitorName = scanner.nextLine();
                    System.out.print("Enter date: ");
                    String editDate = scanner.nextLine();
                    System.out.print("Enter time: ");
                    String editTime = scanner.nextLine();
                    appoint.editapt(editVisitorName, editDate, editTime);
                    break;
                case 7:
                    System.out.println("Exiting apt Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();  
        }
    }
}
