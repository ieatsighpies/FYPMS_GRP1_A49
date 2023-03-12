package main;
import java.io.Console;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Console console = System.console();
        Login test = new Login(sc, console);

        User temp1 = new User("lol", "asdads@gmail.com", "password", 1);
        System.out.println(temp1.getUserID());

    }
}
