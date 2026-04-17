import java.util.*;

class Room {
    int id; String type; double price; boolean isAvail = true;

    public Room(int id, String type, double price) { this.id = id; this.type = type; this.price = price; }

    @Override
    public String toString() { 
        return "Room #" + id + " [" + type + "] - ₹" + price + " | " + (isAvail ? "Available" : "Occupied"); 
    }
}

class Customer {
    String id, name, phone; int roomId;

    public Customer(String id, String name, String phone, int roomId) { this.id = id; this.name = name; this.phone = phone; this.roomId = roomId; }

    @Override
    public String toString() { return "ID: " + id + " | Name: " + name + " | Room: " + roomId; }
}

class HotelSystem {
    List<Room> rooms = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();

    public void addRoom(int id, String type, double price) { 
        rooms.add(new Room(id, type, price));
         System.out.println("Room added!"); 
        }

    public void displayAvailable() {
        System.out.println("\n--- Available Rooms ---");
        rooms.stream().filter(r -> r.isAvail).forEach(System.out::println);
    }

    public void sortRooms() { 
        rooms.sort(Comparator.comparingDouble(r -> r.price)); 
        System.out.println("Sorted by price.");
     }

    public void bookRoom(String id, String name, String phone, int roomId) {
        Room r = find(roomId);
        if (r == null || !r.isAvail) { System.out.println(" Unavailable or non-existent."); return; }
        r.isAvail = false;
        customers.add(new Customer(id, name, phone, roomId));
        System.out.println("Booked!");
    }

    public void checkout(int roomId) {
        Room r = find(roomId);
        if (r == null || r.isAvail) {
             System.out.println("❌ Not found or vacant."); 
             return;
        }
        r.isAvail = true;
        customers.removeIf(c -> c.roomId == roomId);
        System.out.println("Checked out!");
    } 

    public void displayCustomers() { System.out.println("\n--- Customers ---"); customers.forEach(System.out::println); }

    private Room find(int id) { return rooms.stream().filter(r -> r.id == id).findFirst().orElse(null); }
}

public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); HotelSystem hotel = new HotelSystem();
        hotel.addRoom(101, "Single", 1500); hotel.addRoom(102, "Deluxe", 3500);

        while (true) {
            System.out.print("\n1.Add 2.Display 3.Sort 4.Book 5.Checkout 6.List 7.Exit\nChoice: ");
            try {
                switch (sc.nextInt()) {
                    case 1 -> { System.out.print("No, Type, Price: "); hotel.addRoom(sc.nextInt(), sc.next(), sc.nextDouble()); }
                    case 2 -> hotel.displayAvailable();
                    case 3 -> { hotel.sortRooms(); hotel.displayAvailable(); }
                    case 4 -> { System.out.print("ID, Name, Phone, Room: "); hotel.bookRoom(sc.next(), sc.next(), sc.next(), sc.nextInt()); }
                    case 5 -> { System.out.print("Room to Checkout: "); hotel.checkout(sc.nextInt()); }
                    case 6 -> hotel.displayCustomers();
                    case 7 -> { System.out.println(" Goodbye!"); System.exit(0); }
                    default -> System.out.println(" Try again.");
                }
            } catch (Exception e) { System.out.println("Error."); sc.nextLine(); }
        }
    }
}
