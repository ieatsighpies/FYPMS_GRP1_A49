package main.Pages;

import java.util.Scanner;

import main.Models.Student;
import main.Utils.ConsoleUtils;
/**
 * StudentMyProject is a subclass of Page that allows student to view their project
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class StudentMyProject extends Page{
    private Scanner sc = new Scanner(System.in);
    private Student student;
    /**
     * StudentMyProject constructor
     *
     */
    public StudentMyProject(Page previousPage, Student student) {
        super(previousPage);
        this.student = student;
    }
    /**
     * Main executable for this page
     * @return next page {@link main.Pages.StudentMain)
     */
    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();

        // check if this student has a project
        boolean check_project = checkProjectExist();
        if(!check_project){
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║          -\u001B[31mAccess Denied: You do not have a project\u001B[0m-          ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.print("Enter any input to return:");
            String hold = sc.nextLine();
            return this.getPreviousPage();
        }

        // if have project then print
        this.student.printProject();

        System.out.print("Enter any input to return: ");
        String hold = sc.nextLine();

        return this.getPreviousPage();
        
    }

    private boolean checkProjectExist(){
        if(this.student.getProject() != null){
            return true;
        }
        return false;
    }

}
