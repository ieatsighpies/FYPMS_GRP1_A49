package main;
import java.io.Console;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Console console = System.console();
        Login login_page = new Login(sc, console);

        while(true){
            boolean status = login_page.attemptLogin();
            if(status){
                String current_user = login_page.getUser();
                break;
            }
        }
        
        

    }


}
