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
        System.out.printf("║ %-57s║\n", "Welcome, " + this.coordinator.getUserID());
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Reset password                                        ║");
        System.out.println("║[2] View projects                                         ║");
        System.out.println("║[3] View created projects                                 ║");
        System.out.println("║[4] View student requests                                 ║");
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
                return new SetPassword(this, this.coordinator.getUserID(), "3");

            case 8:
                System.out.println("Logging out.");
                return this.getPreviousPage().getPreviousPage();
        }

        return this;
    }
}
