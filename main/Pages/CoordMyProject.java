package main.Pages;

import java.util.Scanner;

import main.Models.Coordinator;
import main.Utils.ConsoleUtils;

/**
* Page for Coordinator to view own project
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class CoordMyProject extends Page{
    /**
     * scanner for user input
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * current coordinator object
     */
    private Coordinator coordinator;

    /**
     * Base constructor for this page
     * @param previousPage the previous page
     * @param coordinator the current user
     */
    public CoordMyProject(Page previousPage, Coordinator coordinator) {
        super(previousPage);
        this.coordinator = coordinator;
    }

    /**
    * Main executable for this page
    * 
    * Please see the {@link main.Pages.Page} class for abstract method
    * @return another page {@link main.Pages.CoordinatorMain}
    */
    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        this.coordinator.printProjects(2);
        System.out.print("Enter any input to return: ");
        String hold = sc.nextLine();
        return this.getPreviousPage();
    }

}
