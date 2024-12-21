package laba;
import laba.bo.CoworkingSpace;
import laba.bo.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {

    private static final List<CoworkingSpace> SPACES = new ArrayList<>();
    private static final List<Reservation> RESERVATIONS = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void initializeCoworkingSpaces() {
        SPACES.add(new CoworkingSpace(1, "Open Space", 20.0, true));
        SPACES.add(new CoworkingSpace(2, "Private Room", 50.0, true));
        SPACES.add(new CoworkingSpace(3, "Meeting Room", 30.0, true));
    }

    public static void showMainMenu() {
        System.out.println("Welcome to the 'Coworking Space Reservation System'");
        while (true) {
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            int choice = SCANNER.nextInt();
            SCANNER.nextLine();
            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu() {
        System.out.println("Admin Menu");
        while (true) {
            System.out.println("1. Add a new coworking space");
            System.out.println("2. Remove a coworking space");
            System.out.println("3. View all reservations");
            System.out.println("4. Exit to main menu");
            System.out.print("Choose an option: ");
            int choice = SCANNER.nextInt();
            SCANNER.nextLine();
            switch (choice) {
                case 1:
                    addCoworkingSpace();
                    break;
                case 2:
                    removeCoworkingSpace();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void customerMenu() {
        System.out.println("Customer Menu");
        while (true) {
            System.out.println("1. Browse available spaces");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Cancel a reservation");
            System.out.println("5. Exit to main menu");
            System.out.print("Choose an option: ");
            int choice = SCANNER.nextInt();
            SCANNER.nextLine();
            switch (choice) {
                case 1:
                    viewCoworkingSpaces();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewMyReservations();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewCoworkingSpaces() {
        System.out.println("Available Coworking Spaces:");
        for (CoworkingSpace space : SPACES) {
            System.out.println(space);
        }
    }

    private static void addCoworkingSpace() {
        System.out.print("Enter new space type (e.g., Open Space, Private Room): ");
        String type = SCANNER.nextLine();
        System.out.print("Enter price per hour: ");
        double price = SCANNER.nextDouble();
        SCANNER.nextLine();
        System.out.print("Is it available (true/false): ");
        boolean isAvailable = SCANNER.nextBoolean();
        SCANNER.nextLine();
        CoworkingSpace newSpace = new CoworkingSpace(SPACES.size() + 1, type, price, isAvailable);
        SPACES.add(newSpace);
        System.out.println("Coworking space added successfully.");
    }

    private static void removeCoworkingSpace() {
        System.out.print("Enter the ID of the space to remove: ");
        int id = SCANNER.nextInt();
        SCANNER.nextLine();
        CoworkingSpace spaceToRemove = null;
        for (CoworkingSpace space : SPACES) {
            if (space.getId() == id) {
                spaceToRemove = space;
                break;
            }
        }
        if (spaceToRemove != null) {
            SPACES.remove(spaceToRemove);
            System.out.println("Coworking space removed successfully.");
        } else {
            System.out.println("Space with the given ID not found.");
        }
    }

    private static void makeReservation() {
        viewCoworkingSpaces();
        System.out.print("Enter the ID of the space to reserve: ");
        int spaceId = SCANNER.nextInt();
        SCANNER.nextLine();
        CoworkingSpace space = null;
        for (CoworkingSpace s : SPACES) {
            if (s.getId() == spaceId && s.isAvailable()) {
                space = s;
                break;
            }
        }
        if (space != null) {
            System.out.print("Enter your name: ");
            String customerName = SCANNER.nextLine();
            System.out.print("Enter the date (YYYY-MM-DD): ");
            String date = SCANNER.nextLine();
            System.out.print("Enter the start time (HH:mm): ");
            String startTime = SCANNER.nextLine();
            System.out.print("Enter the end time (HH:mm): ");
            String endTime = SCANNER.nextLine();
            Reservation reservation = new Reservation(space, customerName, date, startTime, endTime);
            RESERVATIONS.add(reservation);
            space.setAvailable(false);
            System.out.println("Reservation successful.");
        } else {
            System.out.println("Space not available or invalid ID.");
        }
    }

    private static void viewReservations() {
        System.out.println("All Reservations:");
        for (Reservation reservation : RESERVATIONS) {
            System.out.println(reservation);
        }
    }

    private static void viewMyReservations() {
        System.out.println("Your Reservations:");
        for (Reservation reservation : RESERVATIONS) {
            System.out.println(reservation);
        }
    }

    private static void cancelReservation() {
        System.out.print("Enter the reservation ID to cancel: ");
        int reservationId = SCANNER.nextInt();
        SCANNER.nextLine();
        Reservation reservationToCancel = null;
        for (Reservation reservation : RESERVATIONS) {
            if (reservation.getReservationId() == reservationId) {
                reservationToCancel = reservation;
                break;
            }
        }
        if (reservationToCancel != null) {
            RESERVATIONS.remove(reservationToCancel);
            reservationToCancel.getSpace().setAvailable(true);
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Reservation with the given ID not found.");
        }
    }
}