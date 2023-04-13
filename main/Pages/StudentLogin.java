package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Student;
import main.Utils.Authenticator;
import main.Utils.FileHandler;

/**
* Page for student's login 
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class StudentLogin extends Page{

    /**
     * scanner for user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * console for user input
     */
    Console console = System.console();

    /**
     * current user type
     */
    private String userType;

    /**
     * current user ID
     */
    private String userID;

    /**
     * current student object
     */
    private Student student;

    /**
     * current user's password
     */
    private String userPass;
    
    /**
     * Base constructor for this page
     * @param previousPage the previous page
     * @param userType the curretn user type
     */
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

    /**
    * Main executable for this page
    * 
    * Please see the {@link main.Pages.Page} class for abstract method
    * @return next page {@link main.Pages.StudentMain}, {@link main.Pages.Welcome}
    */
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
