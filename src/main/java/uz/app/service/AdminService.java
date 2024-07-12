package uz.app.service;

import uz.app.db.Database;
import uz.app.entity.*;
import uz.app.enums.Role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static uz.app.util.Utils.scanNum;
import static uz.app.util.Utils.scanStr;

public class AdminService {

    // database get instance:
    private static Database database = Database.getInstance();

    // for choice:
    static int choice = 0;

    // admin service:
    private static AdminService adminService;

    // admin service:
    public static AdminService getInstance() {
        if (adminService == null) {
            adminService = new AdminService();
        }
        return adminService;
    }

    // admin menu:
    public void adminMenu() {

        while (true) {
            System.out.println("1. Hotel  2. Show Rooms  3. Booking  4. History  0. Exit");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    hotel();
                }
                case "2" -> {
                    showRooms();
                }
                case "3" -> {
                    booking();
                }
                case "4" -> {
                    history();
                }
                case "0" -> {
                    System.out.println("Thank you bye, bye!");
                    return;
                }
                default -> {
                    System.out.println("Command not found, please try again!");
                }
            }
        }
    }

    private static void hotel() {
        while (true) {
            System.out.println("1. Create  2. Show  0. Back");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    addHotel();
                }
                case "2" -> {
                    showHotels();
                }
                case "0" -> {
                    return;
                }
                default -> {
                    System.out.println("Command not found, please try again!");
                }
            }
        }
    }

    private static void showHotels() {
        int count = 1;
        for (Hotel hotel : Database.hotelList) {
            System.out.println(count++ + ". " + hotel.getName());
        }
    }

    // history
    private static void history() {
        int count = 1;
        for (History history : database.getHistories()) {
            System.out.println(count++ + ". " + history);
        }
    }

    // admin booking menu:
    private static void booking() {
        while (true) {
            System.out.print("""
                    1. Create booking for temp User
                    2. Show temp users Bookings
                    3. Update temp user's Booking
                    4. Show all active Bookings
                    5. Show all Bookings
                    0. Back
                    """);
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    createBookingForTempUser();
                }
                case "2" -> {
                    showAllTempBookings();
                }
                case "3" -> {
                    updateTempBooking();
                }

                case "4" -> {
                    showAllActiveBookings();
                }

                case "5" -> {
                    showAllBookings();
                }

                case "0" -> {
                    return;
                }
                default -> {
                    System.out.println("Command not found, please try again!");
                }
            }
        }
    }

    // all active bookings:
    private static void showAllActiveBookings() {
        int count = 1;
        for (Booking booking : Database.getInstance().getBookings()) {
            if (booking.isActive()) {
                System.out.println(count++ + ". " + booking);
            }
        }
    }

    // all bookings:
    private static void showAllBookings() {
        int count = 1;
        for (Booking booking : Database.getInstance().getBookings()) {
            if (!booking.isTemporary()) {
                System.out.println(count++ + ". " + booking);
            }
        }
    }

    // update temp booking:
    private static void updateTempBooking() {
        showAllTempBookings();

        System.out.print("One choice: ");
        try {
            choice = scanNum.nextInt() - 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Booking booking = Database.bookingList.get(choice);
        System.out.println(booking + " selected!");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Please enter updating start date (yyyy-MM-dd): ");
        String startDateInput = scanStr.nextLine();

        System.out.print("Please enter updating end date (yyyy-MM-dd): ");
        String endDateInput = scanStr.nextLine();

        try {
            Date startDate = dateFormat.parse(startDateInput);
            Date endDate = dateFormat.parse(endDateInput);

            booking.setStartDate(startDate);
            booking.setEndDate(endDate);

            System.out.println("Booking updated successfully!");

            System.out.println("Start Date: " + booking.getStartDate());
            System.out.println("End Date: " + booking.getEndDate());

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
        }

    }

    // show all temp bookings:
    private static void showAllTempBookings() {
        int count = 1;
        for (Booking booking : Database.bookingList) {
            if (booking.isTemporary()) {
                System.out.println(count++ + ". " + booking);
            }
        }
    }

    // create temp user and booking:
    private static void createBookingForTempUser() {
        System.out.print("Enter name: ");
        String name = scanStr.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanStr.nextLine();

        Database.addUser(new User(name, surname, Role.GUEST, true));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print("Please enter start date (yyyy-MM-dd): ");
        String startDateInput = scanStr.nextLine();

        System.out.print("Please enter end date (yyyy-MM-dd): ");
        String endDateInput = scanStr.nextLine();

        try {
            Date startDate = dateFormat.parse(startDateInput);
            Date endDate = dateFormat.parse(endDateInput);

            Booking booking = new Booking(startDate, endDate, true);
            Database.addBooking(booking);

            System.out.println("Booking created successfully!");
            System.out.println("Start Date: " + booking.getStartDate());
            System.out.println("End Date: " + booking.getEndDate());

        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
        }
    }

    // show rooms:
    private static void showRooms() {
//        while (true) {
//            System.out.println("1. Available  2. Unavailable  0. Back");
//            String command = scanStr.nextLine();
//
//            switch (command) {
//                case "1" -> {
//                    int count = 1;
//                    for (Booking booking : Database.bookingList) {
//                        if () {
//                            System.out.println(count++ + ". " + booking.getRoom().getRoomNumber());
//                        }
//                    }
//                }
//                case "2" -> {
//                    int count = 1;
//                    for (Booking booking : Database.bookingList) {
//                        if (!booking.getRoom().isAvailable()) {
//                            System.out.println(count++ + ". " + booking.getRoom().getRoomNumber());
//                        }
//                    }
//                }
//                case "0" -> {
//                    return;
//                }
//                default -> {
//                    System.out.println("Command not found, please try again!");
//                }
//            }
//        }
    }

    // add new hotel:
    private static void addHotel() {

        System.out.print("Enter name: ");
        String name = scanStr.nextLine();

        System.out.print("Enter floor: ");
        Integer floorInput = scanNum.nextInt();

        System.out.print("Enter number: ");
        Integer numberInput = scanNum.nextInt();

        Room room = new Room();
        room.setFloor(floorInput);
        room.setNumber(numberInput);

        Database.addHotel(new Hotel(name, room));
        Database.addRoom(new Room(floorInput, numberInput));

    }
}
