package main;
import java.io.Console;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class Login extends Page{
    String userPass;
    int userType;

    public Login(Scanner sc, Console console){
        super(sc, console);
    }

    public String getUser(){
        return super.getUser();
    }

    public int getUserType(){
        return this.userType;
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
        this.userID = sc.next();
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
        
        /* Create FileHandler object */
        FileHandler filehandler = new FileHandler();
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\student_list.csv";
        
        /* Get target line from csv */
        String data[] = filehandler.readFile(filepath, username, 2);
        if(data == null){
            return false;
        }

        /* Regenerate encrypted password */                
        Encryptor encryptor = new Encryptor(password);
        byte[] byteSalt = encryptor.convertSalt(data[4]);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);

        /* accept if using default password */
        if(password.equals("password")){
            return true;
        }
        /* else verify against encrypted password */
        else if(data[3].equals(encryptedPass)){
            return true;
        }
        /* no match return false */
        else{
            return false;
        }
  
    }

    public boolean authenicateStaff(String username, String password){
        
        /* Create FileHandler object */
        FileHandler filehandler = new FileHandler();
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
        
        /* Get target line from csv */
        String data[] = filehandler.readFile(filepath, username, 2);
        if(data == null){
            return false;
        }

        /* Regenerate encrypted password */                
        Encryptor encryptor = new Encryptor(password);
        byte[] byteSalt = encryptor.convertSalt(data[4]);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);

        /* accept if using default password */
        if(password.equals("password")){
            return true;
        }
        /* else verify against encrypted password */
        else if(data[3].equals(encryptedPass)){
            return true;
        }
        /* no match return false */
        else{
            return false;
        }
    }

}
