package main.Pages;

import java.io.Console;
import java.util.Scanner;

public class StaffMain extends Page{
    Scanner sc = new Scanner(System.in);
    Console console = System.console();
    
    public StaffMain(Page previousPage, int accesslevel){
        super(previousPage, accesslevel);
    }

    @Override
    public Page executable(){
        System.out.println("You reached the StaffMain");
        return new Exit(this.getPreviousPage());
    }

    
    // public void setPasswordStaff(String newPassword){

    //     /* Encrypt new password */
    //     Encryptor encryptor = new Encryptor(newPassword);
    //     String salt = encryptor.generateSalt();
    //     byte[] byteSalt = encryptor.convertSalt(salt);
    //     String encryptedPass = encryptor.generateHashedPassword(byteSalt);

    //     /* Make FileHandler object */
    //     FileHandler filehandler = new FileHandler();

    //     /* Read and write file */
    //     String filepath = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv"; // set filepath
    //     String line[] = filehandler.readFile(filepath, this.userID, 2); // set target line
    //     String newline = String.join(",", line[0], line[1], line[2], encryptedPass, salt); // make new string to replace target string
    //     filehandler.writeToFile(filepath, this.userID, 2, newline); // write new string to target string
        
    
    
}
