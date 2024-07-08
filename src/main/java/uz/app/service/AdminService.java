package uz.app.service;

import uz.app.db.Database;
import uz.app.entity.Hotel;
import uz.app.util.Utils;

import static uz.app.util.Utils.scanStr;

public class AdminService {
    Database database = Database.getInstance();

    private static AdminService adminService;

    public static AdminService getInstance() {
        if (adminService == null) {
            adminService = new AdminService();
        }
        return adminService;
    }

    public static void adminMenu() {

        while (true) {
            System.out.println("1. Add Hotel  2. Show Rooms  3. Booking  4. History  0. Exit");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> {
                    addHotel();
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

    // history
    private static void history() {
    }

    // booking:
    private static void booking() {
    }

    // show rooms:
    private static void showRooms() {

    }

    // add new hotel:
    private static void addHotel() {
        Hotel hotel = new Hotel();

        System.out.print("Enter name: ");
        hotel.setName(Utils.scanStr.nextLine());

        System.out.print("Enter floor: ");
        hotel.setFloor(Utils.scanNum.nextInt());

        System.out.print("Enter rooms: ");
        hotel.setRooms(Utils.scanNum.nextInt());

        Database.addHotel(hotel);
    }
}
