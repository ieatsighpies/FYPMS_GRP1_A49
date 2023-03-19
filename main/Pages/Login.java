package main.Pages;

import main.Utils.Authenticator;
import main.Utils.ConsoleUtils;
import main.Utils.FileHandler;

import java.util.Scanner;
import java.io.Console;


public class Login extends Page{
    Scanner sc = new Scanner(System.in);
    Console console = System.console();
    
    String userType;
    String userID;
    String userPass;

    public Login(Page previousPage, int accesslevel, String userType){
        super(previousPage, accesslevel);
        this.userType = userType;
    }

    @Override
    public Page executable(){
        ConsoleUtils.clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.printf("║%s║\n", userType.equals("1")
                                            ? "                     -Student Login-                      "
                                            : "                      -Staff Login-                       ");
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
            String filepath = userType.equals("1") ? System.getProperty("user.dir") + "\\main\\Data\\student_list.csv" : System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
            boolean userCheck = FileHandler.findFile(filepath, this.userID, 2);
            boolean passcheck = userType.equals("1") ? Authenticator.authenticateStudent(this.userID, this.userPass) : Authenticator.authenticateStaff(this.userID, this.userPass);
            if(!userCheck){
                System.out.println("UserID does not exist.");
            }
            else if(!passcheck){
                System.out.println("Invalid login details.");
            }
            // redirect to respective page if verified
            else{
                return userType.equals("1") 
                    ? new StudentMain(this, 1, this.userID)
                    : new StaffMain(this, 2, this.userID);
            }
            if(!(this.userPass.isBlank() || this.userID.isBlank())){
                System.out.println("Re-attempting login.");
            }

        } while(!(this.userPass.isBlank() || this.userID.isBlank()));
        return this.getPreviousPage();
    }
}
