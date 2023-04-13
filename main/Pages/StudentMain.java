package main.Pages;

import java.io.Console;
import java.util.Scanner;

import main.Models.Student;
import main.Utils.ConsoleUtils;

/**
 * StudentMain is a subclass of Page that allows student to interact with the FYP system
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class StudentMain extends Page{
    
    Scanner sc = new Scanner(System.in);
    Console console = System.console();

    private Student student;
    
    /**
     * Constructor for this class
     * @param previousPage the previous page
     * @param student the current user
     */
    public StudentMain(Page previousPage, Student student){
        super(previousPage);
        this.student = student;
    }
    /**
     * Main executable for this page
     * @return next page {@link main.Pages.Welcome},{@link main.Pages.StudentRequestTitleChange},{@link main.Pages.StudentRequestHistory},{@link main.Pages.StudentRequestDeregister},{@link main.Pages.StudentMyProject}
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
        System.out.println("║                       -Student Menu-                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║ Welcome, \u001B[33m%-48s\u001B[0m║\n", this.student.getName());
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Reset password                                        ║");
        System.out.println("║[2] View available projects                               ║");
        System.out.println("║[3] View my project                                       ║");
        System.out.println("║[4] Request de-register project                           ║");
        System.out.println("║[5] Request project title change                          ║");
        System.out.println("║[6] View request history                                  ║");
        System.out.println("║[7] Log-out                                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        // get option
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-7]{1}$"))){

            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){

            // SetPassword Page
            case 1:
                return new SetPassword(this, this.student.getUserID(), "1");

            // View available project
            case 2:
                return new StudentProjectView(this, this.student);

            // View my project
            case 3:
                return new StudentMyProject(this, this.student);

            // De-reg project
            case 4:
                return new StudentRequestDeregister(this, this.student);
            
            // Request project title change
            case 5:
                return new StudentRequestTitleChange(this, this.student);

            // View request history
            case 6:
                return new StudentRequestHistory(this, this.student);
            // logout
            case 7:
                System.out.println("Logging out.");
                return this.getPreviousPage().getPreviousPage();
        }

        return this;
    }

}
