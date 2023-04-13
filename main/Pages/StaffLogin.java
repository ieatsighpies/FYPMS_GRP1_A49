package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Supervisor;
import main.Utils.Authenticator;
import main.Utils.FileHandler;

/**
* Page for Supervisor to login
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class StaffLogin extends Page{

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
     * current supervisor object
     */
    private Supervisor staff;

    /**
     * current coordinator object
     */
    private Coordinator coordinator;

    /**
     * current user's password'
     */
    private String userPass;

    /**
     * Base constructor for this page
     * @param previousoPage the previous page
     * @param userType the current user's user type
     */
    public StaffLogin(Page previousoPage, String userType){
        super(previousoPage);
        this.userType = userType;
    }

    /**
    * Main executable for this page
    * 
    * Please see the {@link main.Pages.Page} class for abstract method
    * @return next page {@link main.Pages.Welcome}, {@link main.Pages.StaffMain}, {@link main.Pages.CoordinatorMain}
    */
    @Override
    public Page executable(){
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                       -Staff Login-                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║   -Leave any field empty to return to user selection-    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        do{
            // get userID
            System.out.print("Enter UserID:");
            this.userID = sc.nextLine().trim();
            System.out.println();

            // get userPass
            this.userPass = new String(console.readPassword("Enter Password:"));

            // verify userID and userPass
            String filepath_faculty = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
            String filepath_coordinator = System.getProperty("user.dir") + "\\main\\Data\\coordinator_list.csv";
            boolean userCheck = FileHandler.findFile(filepath_faculty, this.userID, 2);
            boolean passcheck = Authenticator.authenticateStaff(this.userID, this.userPass);
           
            //check if is coordinator
            boolean isCoordinator = FileHandler.findFile(filepath_coordinator, this.userID, 2);
            if(isCoordinator){
                this.userType = "3";
            }
            
            // print error message and page redirection
            if(!userCheck){
                System.out.println("UserID does not exist.");
            }
            else if(!passcheck){
                System.out.println("Invalid login details.");
            }
            // redirect to staff main if verified
            else{
                // initialise faculty object
                String[] data = FileHandler.readFile(filepath_faculty, this.userID, 2);
                if(this.userType.equals("2")){
                    this.staff = new Supervisor(data[0], data[1]);
                }
                else{
                    this.coordinator = new Coordinator(data[0], data[1]);
                }
                return this.userType.equals("2") 
                            ? new StaffMain(this, this.staff)
                            : new CoordinatorMain(this, this.coordinator);
            }
            if(!(this.userPass.isBlank() || this.userID.isBlank())){
                System.out.println("Re-attempting login.");
            }

        } while(!(this.userPass.isBlank() || this.userID.isBlank()));
        return this.getPreviousPage();
    }
    
}
