package main.Pages;

import java.util.Scanner;

import main.Models.Coordinator;
import main.Utils.ConsoleUtils;

public class CoordAllProject extends Page{
    private Scanner sc = new Scanner(System.in);
    private Coordinator coordinator;

    public CoordAllProject(Page previousPage, Coordinator coordinator) {
        super(previousPage);
        this.coordinator = coordinator;
    }

    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        this.coordinator.printProjects();
        
        // to return
        System.out.print("Enter any input to return: ");
        String hold = sc.nextLine();
        
        return this.getPreviousPage();
    }

}
