package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Student;
import main.Utils.Authenticator;
import main.Utils.FileHandler;

public class StudentLogin extends Page{
    Scanner sc = new Scanner(System.in);
    Console console = System.console();
    private String userType;
    private String userID;
    private Student student;
    private String userPass;
    
    public StudentLogin(Page previousPage, String userType){
        super(previousPage);
        this.userType = userType;
        if (console==null)
        {
            System.out.println(
                    "No console available");
            return;
        }
    }

    @Override
    public Page executable(){
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                      -Student Login-                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║   -Leave any field empty to return to user selection-    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        do{
            // get userID
            System.out.print("Enter UserID:");
            this.userID = sc.nextLine().trim();

            // get userPass
            this.userPass = new String(console.readPassword("Enter Password:"));


            // verify userID and userPass
            String filepath = System.getProperty("user.dir") + "\\main\\Data\\student_list.csv";
            boolean userCheck = FileHandler.findFile(filepath, this.userID, 2);
            boolean passcheck = Authenticator.authenticateStudent(this.userID, this.userPass);

            // print error message and page redirection
            if(!userCheck){
                System.out.println("UserID does not exist.");
            }
            else if(!passcheck){
                System.out.println("Invalid login details.");
            }
            // redirect to student main if verified
            else{
                // initialise student object
                String[] data = FileHandler.readFile(filepath, this.userID, 2);
                this.student = new Student(data[0], data[1],Boolean.valueOf(data[5]));
                return new StudentMain(this, this.student);
            }
            if(!(this.userPass.isBlank() || this.userID.isBlank())){
                System.out.println("Re-attempting login.");
            }

        } while(!(this.userPass.isBlank() || this.userID.isBlank()));
        return this.getPreviousPage();
    }
}
