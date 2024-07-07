package uz.app.service;

import uz.app.db.Database;
import uz.app.entity.User;

import static uz.app.Main.scanStr;

public class AuthService {
    static Database database = Database.getInstance();

    // AdminService adminService = AdminService.getInstance();
    // UserService userService = UserService.getInstance();


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

    /*public void signIn() {
        System.out.print("Enter username: ");
        String username = scanStr.nextLine();

        System.out.print("Enter password: ");
        String password = scanStr.nextLine();

        for (User user : database.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                Context.setUser(user);
                if (user.getRole().equals("admin")) {

                    adminService.service();
                } else {
                    userService.service();
                }
                return;
            }
        }
        System.out.println("user not found!");
    }*/

    public void checkUsername(String username){

    }
}
