package uz.app.service;

import uz.app.db.Database;
import uz.app.entity.Hotel;
import uz.app.entity.User;

import static uz.app.util.Utils.*;

public class UserService {
    private static Database database = Database.getInstance();

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
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
                    activeBooking();
                }
                case "3" -> {
                    myBooking();
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
    private static void myBooking() {
    }

    // user's active bookings:
    private static void activeBooking() {

    }

    // new booking:
    private static void newBooking() {
        int count = 1;

        for (Hotel hotel : Database.getInstance().getHotels()) {
            System.out.println(count++ + ". " + hotel.getName());
        }

        System.out.print("Choice one: ");
        int choice = 0;

        try {
            choice = scanNum.nextInt() - 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("«" + Database.hotelList.get(choice).getName() + "» selected ✅");

        System.out.println("Enter the start date: ");

        System.out.println("Enter the end date: ");

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
