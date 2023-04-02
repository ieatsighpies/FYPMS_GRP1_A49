package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Supervisor;
import main.Utils.ConsoleUtils;

public class StaffMain extends Page{
    Scanner sc = new Scanner(System.in);
    Console console = System.console();

    Supervisor staff;
    
    public StaffMain(Page previousPage, Supervisor staff){
        super(previousPage);
        this.staff = staff;
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
        System.out.println("║                        -Staff Menu-                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║ Welcome, \u001B[33m%-48s\u001B[0m║\n", this.staff.getName());
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Reset password                                        ║");
        System.out.println("║[2] View projects                                         ║");
        System.out.println("║[3] View student requests                                 ║");
        System.out.println("║[4] Create Project                                        ║");
        System.out.println("║[5] Deregister Project                                    ║");
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
            
            // Create Project
            case 4:
                return new CreateProjectPage(this, this.staff);
            // De-reg project
            case 5:
                return new SupervisorDeregister(this, this.staff);
                
            // Edit project title
            case 6:
                return new SupervisorEditTitle(this, this.staff);
            // case 5: 
            //     System.out.println("Enter new project title:");
            //     String newTitle = sc.nextLine();
            //     return new Supervisor.approve(newTitle);

            // Request to transfer student
            case 7:
                return new SupervisorTransferStudent(this, this.staff);

            // View request history
            case 8:
                return new SupervisorRequestHistory(this, this.staff);
            // Logout
            case 9:
                System.out.println("Logging out.");
                return this.getPreviousPage().getPreviousPage();
        }

        return this;
    }
    
    
}
