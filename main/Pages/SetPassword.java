package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Utils.Authenticator;
import main.Utils.ConsoleUtils;
import main.Utils.Encryptor;
import main.Utils.FileHandler;

public class SetPassword extends Page{
    Console console = System.console();
    Scanner sc = new Scanner(System.in);

    private String userType;
    private String userID;
    
    public SetPassword(Page previousPage, String userID, String userType){
        super(previousPage);
        this.userType = userType;
        this.userID = userID;
    }

    @Override
    public Page executable(){
        ConsoleUtils.clearScreen();
        String filepath = userType.equals("1")
                            ? System.getProperty("user.dir") + "\\main\\Data\\student_list.csv"
                            : System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";

        String current_pass;
        boolean check = false;
        int counter = 3;

        // verify current password
        while(true){
            current_pass = new String(console.readPassword("Enter your current password(empty to return to previous menu):"));
            counter--;

            // if empty input return to previous page
            if(current_pass.isBlank()){
                return this.getPreviousPage();
            }

            // check if current password matches
            check = userType.equals("1")
                        ? Authenticator.authenticateStudent(this.userID, current_pass)
                        : Authenticator.authenticateStaff(this.userID, current_pass);
            if(check){
                break;
            }
            else{
                System.out.printf("Invalid password. %d tries remaining.\n", counter);
            }

            // check remaining tries, return to previous page if tries exceeded
            if(counter <= 0){
                return this.getPreviousPage();
            }
        }

        String new_pass1, new_pass2;
        check = false;

        do{
            System.out.println("Enter your new password(leave any fields empty to return)");
            new_pass1 = new String(console.readPassword("New password:"));
            new_pass2 = new String(console.readPassword("Re-enter new password:"));

            // check if both password match
            check = new_pass1.equals(new_pass2);
            if(!new_pass1.isBlank() && !new_pass2.isBlank()){
                if(!check){
                    System.out.println("Passwords does not match. Try again.");
                }
                // set passsword
                else{
                    setPassword(new_pass1, filepath);
                    return this.getPreviousPage();
                }
            }
            
        } while(!(new_pass1.isBlank() || new_pass2.isBlank()));
        return this.getPreviousPage();


    }

    public void setPassword(String newPassword, String filepath){

        /* Encrypt new password */
        Encryptor encryptor = new Encryptor(newPassword);
        String salt = encryptor.generateSalt();
        byte[] byteSalt = encryptor.convertSalt(salt);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);

        /* Read and write file */
        String line[] = FileHandler.readFile(filepath, this.userID, 2); // set target line
        String newline = String.join(",", line[0], line[1], line[2], encryptedPass, salt); // make new string to replace target string
        FileHandler.writeFile(filepath, this.userID, 2, newline); // write new string to target string
    
    }
}
