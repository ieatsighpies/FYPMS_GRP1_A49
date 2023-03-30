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
            
            // Create Project
            case 4:
                return new CreateProjectPage(this, this.staff);

            // Request to transfer student
            case 6:
                return new SupervisorTransferStudent(this, this.staff);
            // Logout
            case 8:
                System.out.println("Logging out.");
                return this.getPreviousPage().getPreviousPage();
        }

        return this;
    }
    
    
}
