package main;
import java.io.Console;
import java.util.Scanner;

import javax.annotation.processing.Filer;

import java.io.BufferedReader;
import java.io.FileReader;

public class Login {
    Scanner sc;
    Console console;
    String userID;
    String userPass;

    public Login(Scanner sc, Console console){
        this.sc = sc;
        this.console = console;
    }

    public boolean attemptLogin(){
        System.out.println("Enter UserID: ");
        this.userID = sc.nextLine();
        this.userPass = new String(console.readPassword("Enter Password: "));

        if(authenicate(this.userID, this.userPass, System.getProperty("user.dir") + "\\main\\" + "student_list.csv")){
            System.out.println("Login Success!");
            return true;
        }
        else{
            System.out.println("Invalid Login details!");
            return false;
        }
    }

    public boolean authenicate(String username, String password, String filepath){
        String currentLine;
        String data[];

        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split(",");
                if(data[1].split("@")[0].equals(username) && data[2].equals(password)){
                    return true;
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }

        return false;
    }

    public void printMenu(){
        System.out.println("Welcome to FYP Interface");
        System.out.println("Choose user type:");
        System.out.println("1) Student");
        System.out.println("2) Staff");
    }

}
