package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Utils.ConsoleUtils;

public class CoordinatorMain extends Page{
    Scanner sc = new Scanner(System.in);
    Console console = System.console();

    Coordinator coordinator;
    
    public CoordinatorMain(Page previousPage, Coordinator staff){
        super(previousPage);
        this.coordinator = staff;
    }

    @Override
    public Page executable(){
        ConsoleUtils.clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║ ███╗   ██╗████████╗██╗   ██╗███████╗██╗   ██╗██████╗ ██╗ ║");
        System.out.println("║ ████╗  ██║╚══██╔══╝██║   ██║██╔════╝╚██╗ ██╔╝██╔══██╗██║ ║");
        System.out.println("║ ██╔██╗ ██║   ██║   ██║   ██║█████╗   ╚████╔╝ ██████╔╝██║ ║");
        System.out.println("║ ██║╚██╗██║   ██║   ██║   ██║██╔══╝    ╚██╔╝  ██╔═══╝ ██║ ║");
        System.out.println("║ ██║ ╚████║   ██║   ╚██████╔╝██║        ██║   ██║     ██║ ║");
        System.out.println("║ ╚═╝  ╚═══╝   ╚═╝    ╚═════╝ ╚═╝        ╚═╝   ╚═╝     ╚═╝ ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║                     -Coordinator Menu-                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║ Welcome, \u001B[33m%-48s\u001B[0m║\n", this.coordinator.getName());
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Reset password                                        ║");
        System.out.println("║[2] View projects                                         ║");
        System.out.println("║[3] View my projects                                      ║");
        System.out.println("║[4] View student requests                                 ║");
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

            // create project
            case 5:
                return new CreateProjectPage(this, coordinator);

            // edit project title
            case 6:
                return new CoordEditTitle(this, coordinator);

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
}
