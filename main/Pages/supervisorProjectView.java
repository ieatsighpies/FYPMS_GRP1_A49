package main.Pages;

import java.util.ArrayList;
import java.util.Scanner;

import main.Models.Project;
import main.Models.Supervisor;
import main.Utils.ConsoleUtils;

public class supervisorProjectView extends Page{
    Scanner sc = new Scanner(System.in);
    private Supervisor staff;

    public supervisorProjectView(Page previouPage, Supervisor staff){
        super(previouPage);
        this.staff = staff;
    }

    @Override
    public Page executable(){
        ConsoleUtils.clearScreen();                              
        staff.printProjects();
        
        System.out.print("Enter any input to return to previous page: ");
        String temp = sc.nextLine();
        return this.getPreviousPage();
    }
}
