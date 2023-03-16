package main;
import java.io.Console;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Console console = System.console();
        Login login_page = new Login(sc, console);
        String current_user;

        while(true){
            boolean status = login_page.attemptLogin();
            if(status){
                current_user = login_page.getUser();
                break;
            }
        }
        MainPage main_page = new MainPage(sc, console, current_user);
        String new_pass;
        System.out.println("Enter new pass:");
        new_pass = sc.next();
        if(login_page.getUserType()==1){
            main_page.setPasswordStudent(new_pass);
        }
        else if(login_page.getUserType()==2){
            main_page.setPasswordStaff(new_pass);
        }
        
        
        

    }


}
