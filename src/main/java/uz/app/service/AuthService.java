package uz.app.service;

import uz.app.db.Database;
import uz.app.entity.User;
import uz.app.enums.Role;

import static uz.app.util.Utils.*;

public class AuthService {
    static Database database = Database.getInstance();

    static AdminService adminService = AdminService.getInstance();
    static UserService userService = UserService.getInstance();

    public static void signUp() {
        User user = new User();

        System.out.print("Enter name: ");
        user.setName(scanStr.nextLine());

        System.out.print("Enter surname: ");
        user.setSurname(scanStr.nextLine());

        System.out.print("Enter username: ");
        String username = scanStr.nextLine();

        while (isUsernameTaken(username)) {
            System.out.print("This us rname is already taken!\nPlease enter a different username: ");
            username = scanStr.nextLine();
        }
        user.setUsername(username);

        System.out.print("Enter password: ");
        user.setPassword(scanStr.nextLine());

        database.addUser(user);
    }

    // check username:
    private static boolean isUsernameTaken(String username) {
        for (User user : database.getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // sign in:
    public static void signIn() {
        System.out.print("Enter username: ");
        String username = scanStr.nextLine();

        System.out.print("Enter password: ");
        String password = scanStr.nextLine();

        for (User user : database.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                setUser(user);

                getCurrentUser().setRole(Role.USER);

                if (user.getRole().equals(Role.USER) && user.getUsername().equals("admin")) {
                    adminService.adminMenu();
                } else {
                    userService.userMenu();
                }
                return;
            }
        }
        System.out.println("User not found!");
    }
}
