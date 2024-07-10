package uz.app.service;

import uz.app.db.Database;
import uz.app.entity.User;
import uz.app.enums.Role;

import java.util.Optional;

import static uz.app.util.Utils.scanStr;
import static uz.app.util.Utils.setUser;

public class AuthService {
    static Database database = Database.getInstance();

    AdminService adminService = AdminService.getInstance();
    UserService userService = UserService.getInstance();


    public static void signUp() {
        User user = new User();

        System.out.print("Enter name: ");
        user.setName(scanStr.nextLine());

        System.out.print("Enter surname: ");
        user.setSurname(scanStr.nextLine());

        // username check method:
        System.out.print("Enter username: ");
        user.setUsername(scanStr.nextLine());

        System.out.print("Enter password: ");
        user.setPassword(scanStr.nextLine());

        database.addUser(user);

    }

    // sign in:
    public static void signIn() {
        System.out.print("Enter username: ");
        String username = scanStr.nextLine();

        System.out.print("Enter password: ");
        String password = scanStr.nextLine();

        Optional<User> userOptional = database.getUsers()
                .stream().filter(user -> user.getUsername() != null && user.getPassword() != null && (user.getUsername()
                        .equals(username) && user.getPassword()
                        .equals(password))).findFirst();

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            if (user.getRole() == Role.ADMIN) {
                setUser(user);
                System.out.println("Logged in as admin.");
                AdminService.adminMenu();
            } else {
                setUser(user);
                System.out.println("Logged in as user.");
                UserService.userMenu();
            }

        } else {
            System.out.println("User not found!");
        }
    }
}
