package main.Pages;

import java.util.Collections;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Utils.ConsoleUtils;
import main.Utils.ProjSortBy;

/**
* Page for Coordinator to view all projects
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class CoordAllProject extends Page{
    /**
     * scanner for user input
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * current user object
     */
    private Coordinator coordinator;
    /**
     * 1: sorted by projectID
     * 2: sorted by student name
     * 3: sort by project status
     */
    private static int coord_proj_sortedby = 1;

    /**
     * Base constructor for this page
     * 
     * @param previousPage the previous page that redirected to this page
     * @param coordinator the current user 
     */
    public CoordAllProject(Page previousPage, Coordinator coordinator) {
        super(previousPage);
        this.coordinator = coordinator;
    }

    /**
    * Main executable for this page
    * 
    * Please see the {@link main.Pages} class for abstract method
    * @return next page 
    * @see CoordinatorMain
    */
    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        this.coordinator.printProjects();

        // print menu
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║               -Options-               ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.printf("║ %-37.37s ║\n", "[1] Sort by ID "+(coord_proj_sortedby==1?"(current)":""));
        System.out.printf("║ %-37.37s ║\n", "[2] Sort by Student Name "+(coord_proj_sortedby==2?"(current)":""));
        System.out.printf("║ %-37.37s ║\n", "[3] Sort by Status "+(coord_proj_sortedby==3?"(current)":""));
        System.out.println("╚═══════════════════════════════════════╝");

        // get user input
        String option;
        System.out.print("Enter choice (empty to return): ");
            option = sc.nextLine().trim();

        while(!(option.matches("^[1-3]{1}$"))){

            if(option.isBlank()){               // return if blank
                return this.getPreviousPage();
            }
            System.out.println("Invalid option!");
            System.out.print("Enter choice (empty to return): ");
            option = sc.nextLine().trim();
        }

        int x = Integer.parseInt(option);

        switch(x){

            // sort by id
            case 1:
                Collections.sort(this.coordinator.getProjects(), ProjSortBy.ID);
                coord_proj_sortedby = 1;
                return this;

            // sort by type
            case 2:
                Collections.sort(this.coordinator.getProjects(), ProjSortBy.StudentName);
                coord_proj_sortedby = 2;
                return this;

            // sort by status
            case 3:
                Collections.sort(this.coordinator.getProjects(), ProjSortBy.Status);
                coord_proj_sortedby = 3;
                return this;

        }

        return this.getPreviousPage();
    }

}
