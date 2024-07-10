package uz.app;

import uz.app.service.AuthService;

import static uz.app.util.Utils.scanStr;

public class Main {

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("1. Sign Up  2. Sign In  0. Exit");
            String command = scanStr.nextLine();

            switch (command) {
                case "1" -> AuthService.signUp();
                case "2" -> AuthService.signIn();
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