package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Request;
import main.Models.Supervisor;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleColors;
import main.Utils.ConsoleUtils;

/**
* Page for Supervisor's main menu
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class StaffMain extends Page{

    /**
     * scanner for user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * console for user input
     */
    Console console = System.console();

    /**
     * current supervisor object
     */
    Supervisor staff;
    
    /**
     * Base constructor for this page
     * 
     * @param previousPage the previous page
     * @param staff the current user
     */
    public StaffMain(Page previousPage, Supervisor staff){
        super(previousPage);
        this.staff = staff;
    }

    /**
    * Main executable for this page
    * 
    * Please see the {@link main.Pages.Page} class for abstract method
    * @return next page {@link main.Pages.SetPassword}, {@link main.Pages.supervisorProjectView}, {@link main.Pages.supervisorViewRequest}, {@link main.Pages.CreateProjectPage}, {@link main.Pages.SupervisorEditTitle}, {@link main.Pages.SupervisorTransferStudent}, {@link main.Pages.SupervisorRequestHistory}, {@link main.Pages.StaffMain}
    */
    @Override
    public Page executable(){
        ConsoleUtils.clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║       ███████╗██╗   ██╗██████╗ ███╗   ███╗███████╗       ║");
        System.out.println("║       ██╔════╝╚██╗ ██╔╝██╔══██╗████╗ ████║██╔════╝       ║");
        System.out.println("║       █████╗   ╚████╔╝ ██████╔╝██╔████╔██║███████╗       ║");
        System.out.println("║       ██╔══╝    ╚██╔╝  ██╔═══╝ ██║╚██╔╝██║╚════██║       ║");
        System.out.println("║       ██║        ██║   ██║     ██║ ╚═╝ ██║███████║       ║");
        System.out.println("║       ╚═╝        ╚═╝   ╚═╝     ╚═╝     ╚═╝╚══════╝       ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║                        -Staff Menu-                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║ Welcome, \u001B[33m%-48s\u001B[0m║\n", this.staff.getName());
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Reset password                                        ║");
        System.out.println("║[2] View projects                                         ║");
        System.out.println("║[3] View student requests                                 ║ " + (this.havePending()?ConsoleColors.BLUE_BOLD_BRIGHT+"NEW!"+ConsoleColors.RESET:""));
        System.out.println("║[4] Create Project                                        ║");
        System.out.println("║[5] Edit project title                                    ║");
        System.out.println("║[6] Request to transfer student                           ║");
        System.out.println("║[7] View request history                                  ║");
        System.out.println("║[8] Log-out                                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        // get option
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-8]{1}$"))){

            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){

            // SetPassword Page
            case 1:
                return new SetPassword(this, this.staff.getUserID(), "2");
            
            // View Projects
            case 2:
                return new supervisorProjectView(this, this.staff);

            //view student request
            case 3:
                return new supervisorViewRequest(this, this.staff);

            // Create Project
            case 4:
                return new CreateProjectPage(this, this.staff);

            // Edit project title
            case 5:
                return new SupervisorEditTitle(this, this.staff);

            // Request to transfer student
            case 6:
                return new SupervisorTransferStudent(this, this.staff);

            // View request history
            case 7:
                return new SupervisorRequestHistory(this, this.staff);
            // Logout
            case 8:
                System.out.println("Logging out.");
                return this.getPreviousPage().getPreviousPage();
        }

        return this;
    }

    /**
     * Method to check if current supervisor have pending request from student
     * @return True if have, else returns False
     */
    private boolean havePending(){
        for(Request r : this.staff.getRequests()){
            if(r.getRequestStatus().equals(requestStatus_ENUM.PENDING) && r.getRequestType().equals("3")){
                return true;
            }
        }
        return false;
    }
    
    
}
