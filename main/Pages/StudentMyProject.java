package main.Pages;

import java.util.Scanner;

import main.Models.Student;
import main.Utils.ConsoleUtils;

public class StudentMyProject extends Page{
    private Scanner sc = new Scanner(System.in);
    private Student student;

    public StudentMyProject(Page previousPage, Student student) {
        super(previousPage);
        this.student = student;
    }

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
