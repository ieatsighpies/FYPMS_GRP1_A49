package main.Pages;

import java.util.Scanner;

import main.Models.Coordinator;
import main.Utils.ConsoleUtils;

public class CoordMyProject extends Page{
    private Scanner sc = new Scanner(System.in);
    private Coordinator coordinator;

    public CoordMyProject(Page previousPage, Coordinator coordinator) {
        super(previousPage);
        this.coordinator = coordinator;
    }

    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        this.coordinator.printProjects(2);
        System.out.print("Enter any input to return: ");
        String hold = sc.nextLine();
        return this.getPreviousPage();
    }

}
