package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Utils.Encryptor;
import main.Utils.FileHandler;

public class StudentMain extends Page{
    
    Scanner sc = new Scanner(System.in);
    Console console = System.console();

    String userID;
    
    public StudentMain(Page previousPage, int accesslevel, String userID){
        super(previousPage, accesslevel);
        this.userID = userID;
    }

    @Override
    public Page executable(){

        System.out.println("You reached the StudentMain");
        System.out.println("Menu:");
        System.out.println("[1] Change Password");
        System.out.println("[2] .......");
        System.out.println("[9] Log-out");

        // get option
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-9]{1}$"))){

            System.out.println("Enter a valid user type:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){
            // this should redirect to a new page, but for now, just testing
            case 1:
                System.out.print("Enter your new password:");
                String newpass = sc.nextLine();
                setPasswordStudent(newpass); 
                break;

            case 9:
                System.out.println("Temp exit, should be log out here");
                return new Exit(this);
        }

        return this;
    }

    // temporarily put this function here
    public void setPasswordStudent(String newPassword){

        /* Encrypt new password */
        Encryptor encryptor = new Encryptor(newPassword);
        String salt = encryptor.generateSalt();
        byte[] byteSalt = encryptor.convertSalt(salt);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);

        /* Read and write file */
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\student_list.csv"; // set filepath
        String line[] = FileHandler.readFile(filepath, this.userID, 2); // set target line
        String newline = String.join(",", line[0], line[1], line[2], encryptedPass, salt); // make new string to replace target string
        FileHandler.writeFile(filepath, this.userID, 2, newline); // write new string to target string
    
    }

}
