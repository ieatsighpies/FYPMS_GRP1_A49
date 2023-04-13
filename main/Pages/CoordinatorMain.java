package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Request;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleColors;
import main.Utils.ConsoleUtils;

/**
* Page for Coordinator's main menu
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class CoordinatorMain extends Page{
    /**
     * scanner for user input
     */
    Scanner sc = new Scanner(System.in);
    /**
     * console for user input
     */
    Console console = System.console();

    /**
     * current user object
     */
    Coordinator coordinator;
    
    /**
     * Base constructor for this page
     * @param previousPage the previous page
     * @param staff current user
     */
    public CoordinatorMain(Page previousPage, Coordinator staff){
        super(previousPage);
        this.coordinator = staff;
    }

    /**
    * Main executable for this page
    * 
    * Please see the {@link main.Pages.Page} class for abstract method
    * @return next page {@link main.Pages.SetPassword}, {@link main.Pages.CoordAllProject}, {@link main.Pages.CoordMyProject}, {@link main.Pages.CoordViewRequest}, {@link main.Pages.CreateProjectPage}, {@link main.Pages.CoordEditTitle}, {@link main.Pages.CoordTransferStudent}, {@link main.Pages.StudentRequestHistory}, {@link main.Pages.Welcome}, {@link main.Pages.CoordinatorMain}
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
        System.out.println("║                     -Coordinator Menu-                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║ Welcome, \u001B[33m%-48s\u001B[0m║\n", this.coordinator.getName());
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Reset password                                        ║");
        System.out.println("║[2] View projects                                         ║");
        System.out.println("║[3] View my projects                                      ║");
        System.out.println("║[4] View requests                                         ║ " + (this.havePending()?ConsoleColors.BLUE_BOLD_BRIGHT+"NEW!"+ConsoleColors.RESET:""));
        System.out.println("║[5] Create Project                                        ║");
        System.out.println("║[6] Edit project title                                    ║");
        System.out.println("║[7] Request to transfer student                           ║");
        System.out.println("║[8] View request history                                  ║");
        System.out.println("║[9] Log-out                                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        // get option
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-9]{1}$"))){

            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){

            // SetPassword Page
            case 1:
                return new SetPassword(this, this.coordinator.getUserID(), "3");
            
            // view all projects
            case 2:
                return new CoordAllProject(this, this.coordinator);

            // view my created projects
            case 3:
                return new CoordMyProject(this, this.coordinator);

            // view student requests
            case 4:
                return new CoordViewRequest(this, this.coordinator);

            // create project
            case 5:
                return new CreateProjectPage(this, coordinator);

            // edit project title
            case 6:
                return new CoordEditTitle(this, coordinator);

            // request to transfer student
            case 7:
                return new CoordTransferStudent(this, coordinator);

            // view request history
            case 8:
                return new CoordRequestHistory(this, coordinator);
            // log out
            case 9:
                System.out.println("Logging out.");
                return this.getPreviousPage().getPreviousPage();
        }

        return this;
    }

    /**
     * Method to check if coordinator have pending requests
     * @return True if have pending; else returns false
     */
    private boolean havePending(){
        for(Request r : this.coordinator.getRequests()){
            if(r.getRequestStatus().equals(requestStatus_ENUM.PENDING)){
                return true;
            }
        }
        return false;
    }
}
