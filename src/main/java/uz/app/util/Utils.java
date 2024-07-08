package uz.app.util;

import uz.app.entity.User;

import java.util.Scanner;

public class Utils {
    public static Scanner scanNum = new Scanner(System.in);
    public static Scanner scanStr = new Scanner(System.in);

    private static User currentUser;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
