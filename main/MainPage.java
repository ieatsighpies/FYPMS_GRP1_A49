package main;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Scanner;

public class MainPage extends Page{
    
    public MainPage(Scanner sc, Console console, String current_user){
        super(sc, console);
        this.userID = current_user;
    }

    public String getUser(){
        return super.getUser();
    }

    public void setPasswordStudent(String newPassword){

        /* Encrypt new password */
        Encryptor encryptor = new Encryptor(newPassword);
        String salt = encryptor.generateSalt();
        byte[] byteSalt = encryptor.convertSalt(salt);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);

        /* Make FileHandler object */
        FileHandler filehandler = new FileHandler();

        /* Read and write file */
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\student_list.csv"; // set filepath
        String line[] = filehandler.readFile(filepath, this.userID, 2); // set target line
        String newline = String.join(",", line[0], line[1], line[2], encryptedPass, salt); // make new string to replace target string
        filehandler.writeToFile(filepath, this.userID, 2, newline); // write new string to target string
    
    }

    public void setPasswordStaff(String newPassword){

        /* Encrypt new password */
        Encryptor encryptor = new Encryptor(newPassword);
        String salt = encryptor.generateSalt();
        byte[] byteSalt = encryptor.convertSalt(salt);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);

        /* Make FileHandler object */
        FileHandler filehandler = new FileHandler();

        /* Read and write file */
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv"; // set filepath
        String line[] = filehandler.readFile(filepath, this.userID, 2); // set target line
        String newline = String.join(",", line[0], line[1], line[2], encryptedPass, salt); // make new string to replace target string
        filehandler.writeToFile(filepath, this.userID, 2, newline); // write new string to target string
        
    
    }
}
