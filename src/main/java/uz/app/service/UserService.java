package uz.app.service;

import uz.app.db.Database;
import uz.app.entity.*;
import uz.app.service.impl.BookingServiceImpl;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static uz.app.util.Utils.*;

public class UserService {
    private static Database database = Database.getInstance();

    private static UserService userService;

    private static BookingService bookingService;

    public UserService(BookingService bookingService) {
        this.bookingService = new BookingService(new BookingServiceImpl());
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(bookingService);
        }
        return userService;
    }


    // user main menus:
    public void userMenu() {

        while (true) {
            System.out.println("1. Booking  2. Profile  0. Exit");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    bookingMenu();
                }
                case "2" -> {
                    profile();
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

    // booking menus:
    private static void bookingMenu() {
        while (true) {
            System.out.println("1. New Booking  2. Active Booking  3. My Booking  0. Back");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    newBooking();
                }
                case "2" -> {
//                    activeBooking();
                }
                case "3" -> {
//                    myBooking();
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

    // user's all bookings:
//    private static void myBooking() {
//        User currentUser = getCurrentUser();
//
//        if (currentUser == null) {
//            System.out.println("User not logged in.");
//            return;
//        }
//
//        List<Booking> bookings = database.getBookings();
//
//        if (bookings.isEmpty()) {
//            System.out.println("No bookings found for user: " + currentUser.getUsername());
//            return;
//        }
//
//        System.out.println("All bookings for user " + currentUser.getUsername() + ":");
//        for (Booking booking : bookings) {
//            if (booking.getUser().equals(currentUser)) {
//                System.out.println("Booking ID: " + booking.getId());
//                System.out.println("Hotel: " + booking.getRoom().getHotel().getName());
//                System.out.println("Room Number: " + booking.getRoom().getRoomNumber());
//                System.out.println("Booking Period: " + booking.getStartDate() + " to " + booking.getEndDate());
//                System.out.println("-----------------------------------------");
//            }
//        }
//
//
//    }

    // user's active bookings:
//    private static void activeBooking() {
//        User currentUser = getCurrentUser();
//
//        if (currentUser == null) {
//            System.out.println("User not found!!!!");
//            return;
//        }
//
//        List<Booking> bookings = database.getBookings();
//
//        if (bookings.isEmpty()) {
//            System.out.println("No bookings found.");
//            return;
//        }
//
//        System.out.println("Your active Bookings:");
//
//        boolean foundActiveBooking = false;
//
//        for (Booking booking : bookings) {
//            if (booking.getUser().equals(currentUser) && booking.isActive()) {
//                System.out.println("Booking ID: " + booking.getId());
//                System.out.println("Hotel: " + booking.getRoom().getHotel().getName());
//                System.out.println("Room Number: " + booking.getRoom().getRoomNumber());
//                System.out.println("Booking Period: " + booking.getStartDate() + " to " + booking.getEndDate());
//                System.out.println("-----------------------------------------");
//                foundActiveBooking = true;
//            }
//        }
//
//        if (!foundActiveBooking) {
//            System.out.println("No active bookings found for user: " + currentUser.getUsername());
//        }
//    }

    // new booking:

    // date format:
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    // new booking:
    private static void newBooking() {
        int count = 1;

        for (Hotel hotel : Database.getHotels()) {
            System.out.println(count++ + ". " + hotel.getName());
        }

        System.out.print("Choice one: ");
        int choice = 1;
        try {
            choice = scanNum.nextInt() - 1; // Foydalanuvchi tomonidan tanlangan hotelni indeksi
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return;
        }

        if (choice < 0 || choice >= Database.getHotels().size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Hotel selectedHotel = Database.getHotels().get(choice);
        System.out.println("«" + selectedHotel.getName() + "» selected ✅");

        Date startDate = null;
        Date endDate = null;

        while (startDate == null) {
            System.out.print("Start date (dd-MM-yyyy): ");
            String startDateStr = scanStr.nextLine();
            startDate = parseDate(startDateStr);
            if (startDate == null) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        while (endDate == null) {
            System.out.print("End date (dd-MM-yyyy): ");
            String endDateStr = scanStr.nextLine();
            endDate = parseDate(endDateStr);
            if (endDate == null) {
                System.out.println("Invalid date format. Please try again.");
            } else if (!endDate.after(startDate)) {
                System.out.println("End date must be after start date. Please try again.");
                endDate = null;
            }
        }

        Integer floor = null;
        Integer roomNumber = null;

        // Kiritilgan floor va room ni so'ramiz
        while (floor == null || roomNumber == null) {
            System.out.print("Enter floor: ");
            try {
                floor = scanNum.nextInt();
                if (floor < 1 || floor > selectedHotel.getRooms().getFloor()) {
                    System.out.println("Invalid floor. This hotel has floors from 1 to " + selectedHotel.getRooms().getFloor() + ". Please try again.");
                    floor = null;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanStr.nextLine(); // Skaner qarobga chiqarish
            }

            if (floor != null) {
                System.out.print("Enter room: ");
                try {
                    roomNumber = scanNum.nextInt();
                    if (roomNumber < 1 || roomNumber > selectedHotel.getRooms().getNumber()) {
                        System.out.println("Invalid room number. This hotel has rooms from 1 to " + selectedHotel.getRooms().getNumber() + " on each floor. Please try again.");
                        roomNumber = null;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanStr.nextLine(); // Skaner qarobga chiqarish
                }
            }
        }

        // Kiritilgan startDate va endDate dan foydalanib, xonani tanlash vaqtini olib boramiz
        boolean roomAvailable = false;
        Room availableRoom = null;
        Date tempEndDate = new Date(endDate.getTime()); // endDate ni saqlab qolinadi

        // Xonani band qilishni urinamiz
        while (!roomAvailable && !startDate.after(tempEndDate)) {
            Room room = selectedHotel.getRooms().get(roomNumber - 1);

            room.setNumber(selectedHotel.getRooms().getNumber());
            room.setFloor(selectedHotel.getRooms().getFloor());

            if (room.getFloor() == floor && room.getNumber() == roomNumber) {
                // Check if this room is available for the selected dates
                boolean isRoomAvailable = bookingService.isRoomAvailable(startDate, tempEndDate, room);
                if (isRoomAvailable) {
                    roomAvailable = true;
                    availableRoom = room;
                    break;
                } else {
                    // If not available, move tempEndDate to the next day
                    tempEndDate.setTime(tempEndDate.getTime() + (1000 * 60 * 60 * 24)); // Bir kunni qo'shib yuborish
                }
            }
        }

        if (roomAvailable) {
            boolean success = bookingService.createBooking(startDate, tempEndDate, availableRoom);
            if (success) {
                System.out.println("Booking created successfully!");
            } else {
                System.out.println("Failed to create booking. The room is not available for the given dates.");
            }
        } else {
            System.out.println("Failed to create booking. The room is not available for the given dates.");
        }
    }


    // date parser:
    private static Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    // user's profile:
    private static void profile() {
        while (true) {
            System.out.println("1. Infos  2. Balance  0. Back");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    infos();
                }
                case "2" -> {
                    balance();
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

    // user balance:
    private static void balance() {
        System.out.println("Your balance: " + getCurrentUser().getBalance() + "$");

        while (true) {
            System.out.println("1. Fill balance  0. Back");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    fillBalance();
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

    // fill balance:
    private static void fillBalance() {
        System.out.print("Enter amount: ");
        Double amount = scanNum.nextDouble();

        getCurrentUser().setBalance(getCurrentUser().getBalance() + amount);

        database.saveUsersToJson();
        System.out.println("Balance filled!");
    }

    // user infos:
    private static void infos() {
        System.out.println("Your infos: ↙️");
        System.out.println("Name: " + getCurrentUser().getName());
        System.out.println("Surname: " + getCurrentUser().getSurname());
        System.out.println("Username: " + getCurrentUser().getUsername());
        System.out.println("Password: " + getCurrentUser().getPassword());

        while (true) {
            System.out.println("1. Update  0. Back");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    updateInfos();
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

    // update information:
    private static void updateInfos() {
        System.out.println("Please, enter new infos!");

        System.out.print("Enter name: ");
        getCurrentUser().setName(scanStr.nextLine());

        System.out.print("Enter surname: ");
        getCurrentUser().setSurname(scanStr.nextLine());

        boolean validUsername = false;

        while (!validUsername) {
            System.out.print("Enter username: ");
            String username = scanStr.nextLine();

            validUsername = true;

            for (User user : Database.userList) {
                if (!getCurrentUser().getUsername().equals(username) && username.equals(user.getUsername())) {
                    System.out.println("Username already exists! Please enter a different username.");
                    validUsername = false;
                    break;
                }
            }

            if (validUsername) {
                getCurrentUser().setUsername(username);
                System.out.print("Enter password: ");
                getCurrentUser().setPassword(scanStr.nextLine());
            }
        }

        database.saveUsersToJson();
        System.out.println("Successfully updated!");
    }
}
