package main;
import java.io.Console;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Login {
    Scanner sc;
    Console console;
    String userID;
    String userPass;
    int userType;

    public Login(Scanner sc, Console console){
        this.sc = sc;
        this.console = console;
    }

    public String getUser(){
        return this.userID;
    }

    public boolean attemptLogin(){
        /* get userType */
        try{
            System.out.println("Welcome to FYP Interface");
            System.out.println("Choose user type:");
            System.out.println("1) Student");
            System.out.println("2) Staff");
            this.userType = sc.nextInt();
            if(!(this.userType==1 || this.userType==2)){
                System.out.println("Invalid option!");
                return false;
            }
        } catch(Exception e){
            System.out.println("Invalid input type! Please enter an integer.");
            sc.next();
            return false;
        }

        /* get userID and userPass */
        System.out.println("Enter UserID: ");
        this.userID = sc.next().toLowerCase();
        this.userPass = new String(console.readPassword("Enter Password: "));

        if(userType == 1){
            /* authenticate student */
            if(authenicateStudent(this.userID, this.userPass)){
                System.out.println("Login Success!");
                return true;
            }
            else{
                System.out.println("Invalid Login details!");
                return false;
            }
        }
        else if(userType == 2){
            /* authenticate staff */
            if(authenicateStaff(this.userID, this.userPass)){
                System.out.println("Login Success!");
                return true;
            }
            else{
                System.out.println("Invalid Login details! Make sure to choose the correct user type.");
                return false;
            }
        }
        return false;
    }

    public boolean authenicateStudent(String username, String password){
        String currentLine;
        String data[];

        /* read file to match with user input */
        try{
            FileReader fr = new FileReader(System.getProperty("user.dir") + "\\main\\" + "student_list.csv");
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split(",");
                if(data[1].split("@")[0].toLowerCase().equals(username) && data[2].equals(password)){
                    return true;
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }

        return false;
    }

    public boolean authenicateStaff(String username, String password){
        String currentLine;
        String data[];

        /* read file to match with user input */
        try{
            FileReader fr = new FileReader(System.getProperty("user.dir") + "\\main\\" + "faculty_list.csv");
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split(",");
                if(data[1].split("@")[0].toLowerCase().equals(username) && data[2].equals(password)){
                    return true;
                }
            }

        } catch(Exception e){
            System.out.println(e);
        }

        return false;
    }


}
