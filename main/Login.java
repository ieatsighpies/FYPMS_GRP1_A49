package main;
import java.io.Console;
import java.util.Scanner;

public class Login {
    Scanner sc;
    Console console;
    String userID;
    String userPass;

    public Login(Scanner sc, Console console){
        this.sc = sc;
        this.console = console;
        attemptLogin();
    }

    public void attemptLogin(){
        System.out.println("Enter UserID: ");
        userID = sc.nextLine();

        userPass = new String(console.readPassword("Enter Password: "));
        authenicate();
    }

    public void authenicate(){
        if(userID.equals("root")){
            if(userPass.equals("root")){
                System.out.println("Login success!");
            }
            else{
                System.out.println("Invalid Password!");
            }
        }
        else{
            System.out.println("Invalid UserID!");
        }
    }

}
