package main.Pages;

import java.util.Scanner;

import main.Utils.ConsoleUtils;
/**
 * Welcome page
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class Welcome extends Page{
    Scanner sc = new Scanner(System.in);
    private String userType;
    /**
     * Constructor for the Welcome class
     *
     */
    public Welcome(Page previousPage){
        super(previousPage);
    }
    /**
     * Main executable for the page
     * @return next page {@link main.Pages.StudentLogin}, {@link main.Pages.StaffLogin}
     */
    @Override
    public Page executable(){
        
        // print menu
        ConsoleUtils.clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║       ███████╗██╗   ██╗██████╗ ███╗   ███╗███████╗       ║");
        System.out.println("║       ██╔════╝╚██╗ ██╔╝██╔══██╗████╗ ████║██╔════╝       ║");
        System.out.println("║       █████╗   ╚████╔╝ ██████╔╝██╔████╔██║███████╗       ║");
        System.out.println("║       ██╔══╝    ╚██╔╝  ██╔═══╝ ██║╚██╔╝██║╚════██║       ║");
        System.out.println("║       ██║        ██║   ██║     ██║ ╚═╝ ██║███████║       ║");
        System.out.println("║       ╚═╝        ╚═╝   ╚═╝     ╚═╝     ╚═╝╚══════╝       ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║                      -User Selection-                    ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║[1] Student                                               ║");
        System.out.println("║[2] Staff                                                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        // get user type
        System.out.print("Enter your user type(empty input to exit): ");
        this.userType = sc.nextLine().trim();
        System.out.println();

        // if input not blank, loop to ask for valid input
        while(!(this.userType.matches("^[1-2]{1}$"))){

            // exit program if empty input
            if(this.userType.isBlank()){
                return new Exit(null);
            }

            System.out.println("Enter a valid user type:");
            this.userType = sc.nextLine().trim();
        }

        return userType.equals("1") 
                    ? new StudentLogin(this, this.userType)
                    : new StaffLogin(this, this.userType);

        
    }

}
