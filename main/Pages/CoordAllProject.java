package main.Pages;

import java.util.Collections;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Utils.ConsoleUtils;
import main.Utils.ProjSortBy;

public class CoordAllProject extends Page{
    private Scanner sc = new Scanner(System.in);
    private Coordinator coordinator;
    private static int coord_proj_sortedby = 1;

    public CoordAllProject(Page previousPage, Coordinator coordinator) {
        super(previousPage);
        this.coordinator = coordinator;
    }

    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        this.coordinator.printProjects();

        // print menu
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
