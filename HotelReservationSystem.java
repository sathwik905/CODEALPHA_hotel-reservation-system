import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true; // Room is initially available
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void bookRoom() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Room " + roomNumber + " booked successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is not available.");
        }
    }
}

class Hotel {
    private List<Room> rooms;
    public Hotel() {
        this.rooms = new ArrayList<>();
        initializeRooms();
    }
    private void initializeRooms() {
        rooms.add(new Room(101, "Single", 100.0));
        rooms.add(new Room(102, "Double", 150.0));
        rooms.add(new Room(103, "Suite", 250.0));
    }
    public List<Room> searchAvailableRooms(String type) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getType().equalsIgnoreCase(type)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public void bookRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.bookRoom();
                return;
            }
        }
        System.out.println("Room " + roomNumber + " not found or already booked.");
    }
}
class Reservation {
    private int reservationId;
    private String guestName;
    private Room bookedRoom;
    private boolean isPaid;
    public Reservation(int reservationId, String guestName, Room bookedRoom) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.bookedRoom = bookedRoom;
        this.isPaid = false; 
    }
    public void markAsPaid() {
        isPaid = true;
        System.out.println("Reservation " + reservationId + " has been paid.");
    }
    public void displayDetails() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Number: " + bookedRoom.getRoomNumber());
        System.out.println("Room Type: " + bookedRoom.getType());
        System.out.println("Total Price: $" + bookedRoom.getPrice());
        System.out.println("Payment Status: " + (isPaid ? "Paid" : "Pending"));
    }
}
public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Pay for Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    searchAvailableRooms(hotel, scanner);
                    break;
                case 2:
                    bookRoom(hotel, scanner);
                    break;
                case 3:
                    payForReservation(scanner);
                    break;
                case 4:
                    viewBookingDetails(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void searchAvailableRooms(Hotel hotel, Scanner scanner) {
        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = scanner.nextLine();

        List<Room> availableRooms = hotel.searchAvailableRooms(roomType);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms of type " + roomType + " found.");
        } else {
            System.out.println("Available " + roomType + " Rooms:");
            for (Room room : availableRooms) {
                System.out.println("- Room " + room.getRoomNumber() + ", $" + room.getPrice());
            }
        }
    }

    private static void bookRoom(Hotel hotel, Scanner scanner) {
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        hotel.bookRoom(roomNumber);
    }

    private static void payForReservation(Scanner scanner) {
        System.out.println("Payment feature is under development.");
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.println("View booking details feature is under development.");
    }
}
