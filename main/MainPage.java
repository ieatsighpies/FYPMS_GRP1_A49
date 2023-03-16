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
        String currentLine;
        String data[];

        /* Encrypt new password */
        Encryptor encryptor = new Encryptor(newPassword);
        String salt = encryptor.generateSalt();
        byte[] byteSalt = encryptor.convertSalt(salt);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);


        /* Read and write file */
        try{
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\main\\student_list.csv"));
            StringBuffer inputBuffer = new StringBuffer();

            while((currentLine = br.readLine()) != null){
                data = currentLine.split(",");
                if(data[1].split("@")[0].equalsIgnoreCase(this.userID)){
                    currentLine = data[0] + "," + data[1] + "," + encryptedPass + "," + salt;
                }
                inputBuffer.append(currentLine);
                inputBuffer.append("\n");
            }
            br.close();

            FileOutputStream fileout = new FileOutputStream(System.getProperty("user.dir") + "\\main\\student_list.csv");
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();

        } catch(Exception e){
            System.out.println(e);
        }

    
    }

    public void setPasswordStaff(String newPassword){
        String currentLine;
        String data[];

        /* Encrypt new password */
        Encryptor encryptor = new Encryptor(newPassword);
        String salt = encryptor.generateSalt();
        byte[] byteSalt = encryptor.convertSalt(salt);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);


        /* Read and write file */
        try{
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv"));
            StringBuffer inputBuffer = new StringBuffer();

            while((currentLine = br.readLine()) != null){
                data = currentLine.split(",");
                if(data[1].split("@")[0].equalsIgnoreCase(this.userID)){
                    currentLine = data[0] + "," + data[1] + "," + encryptedPass + "," + salt;
                }
                inputBuffer.append(currentLine);
                inputBuffer.append("\n");
            }
            br.close();

            FileOutputStream fileout = new FileOutputStream(System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv");
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();

        } catch(Exception e){
            System.out.println(e);
        }

    
    }
}
