package uz.app;

import uz.app.service.AuthService;

import java.util.Scanner;

public class Main {

    public static Scanner scanNum = new Scanner(System.in);
    public static Scanner scanStr = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Sign Up  2. Sign In  0. Exit");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> AuthService.signUp();
                // case "2" -> signIn();
                case "0" -> {
                    System.out.println("Bye, bye!");
                    return;
                }
                default -> {
                    System.out.println("Command not found, please try again!");
                }
            }
        }
    }
}