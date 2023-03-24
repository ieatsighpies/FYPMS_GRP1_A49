package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Supervisor;
import main.Utils.Authenticator;
import main.Utils.FileHandler;

public class StaffLogin extends Page{
    Scanner sc = new Scanner(System.in);
    Console console = System.console();
    
    private String userType;
    private String userID;
    private Supervisor staff;
    private Coordinator coordinator;
    private String userPass;

    public StaffLogin(Page previousoPage, String userType){
        super(previousoPage);
        this.userType = userType;
    }

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
                this.staff = this.userType.equals("2")
                                    ? new Supervisor(data[0], data[1])
                                    : new Coordinator(data[0], data[1]);
                return this.userType.equals("2") 
                            ? new StaffMain(this, this.staff)
                            : new CoordinatorMain(this, (Coordinator) this.staff);
            }
            if(!(this.userPass.isBlank() || this.userID.isBlank())){
                System.out.println("Re-attempting login.");
            }

        } while(!(this.userPass.isBlank() || this.userID.isBlank()));
        return this.getPreviousPage();
    }
    
}
