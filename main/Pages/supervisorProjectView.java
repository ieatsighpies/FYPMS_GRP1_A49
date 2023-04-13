package main.Pages;

import java.util.ArrayList;
import java.util.Scanner;

import main.Models.Project;
import main.Models.Supervisor;
import main.Utils.ConsoleUtils;
/**
 * supervisorProjectView is a subclass of Page that allows supervisor to view their project
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class supervisorProjectView extends Page{
    Scanner sc = new Scanner(System.in);
    private Supervisor staff;
    /**
     * supervisorProjectView constructor
     *
     */
    public supervisorProjectView(Page previouPage, Supervisor staff){
        super(previouPage);
        this.staff = staff;
    }
    /**
     * Main executable for supervisorProjectView page
     * @return next page {@link main.Pages.StudentMain)
     */
    @Override
    public Page executable(){
        ConsoleUtils.clearScreen();                              
        staff.printProjects();
        
        System.out.print("Enter any input to return to previous page: ");
        String temp = sc.nextLine();
        return this.getPreviousPage();
    }
}
