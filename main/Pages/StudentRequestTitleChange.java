package main.Pages;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import main.Models.Student;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleUtils;
import main.Utils.UIDGenerator;

public class StudentRequestTitleChange extends Page{
    private Scanner sc = new Scanner(System.in);
    private Student student;

    public StudentRequestTitleChange(Page previousPage, Student student) {
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

        // if have project get new title
        String newTitle;
        this.student.printProject();

        while(true){
            System.out.print("Enter new project title(empty input to return): ");
            newTitle = sc.nextLine().trim();
            if(newTitle.isBlank()){
                return this.getPreviousPage();
            }
            if(newTitle.contains(",")){
                System.out.println("Title cannot contain commas (,)");
            }
            else if(newTitle.length() > 80){
                System.out.println("Title length cannot exceed 80");
            }
            else{
                break;
            }
        }
        
        // ask for confirmation
        String confirmation;
        while(true){
            System.out.print("Type \"\u001B[31mCONFIRM\u001B[0m\" to confirm(Empty to back): ");
            confirmation = sc.nextLine();
            if(confirmation.isBlank()){
                return this.getPreviousPage();
            }
            if(confirmation.equals("CONFIRM")){
                break;
            }
            else{
                System.out.println("Invalid input!");
            }
        }

        // generate request
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        Long UID = UIDGenerator.generateLongId();
        try(FileWriter fw = new FileWriter(filepath, true)){
            PrintWriter writer = new PrintWriter(fw);
            writer.println(UID+","+student.getUserID()+","+this.student.getProject().getSupervisorID()+",3,"+requestStatus_ENUM.PENDING.toString()+","+this.student.getProject().getID()+",NaN,NaN,"+newTitle);

        }catch(Exception e){
            System.out.println(e);
        }

        // reload request
        student.updateRequest();

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║             -Request Sent-            ║");
        System.out.println("╚═══════════════════════════════════════╝");

        System.out.println("Enter any input to return to Main Menu:");
        String temp = sc.nextLine();
        
        return this.getPreviousPage();

    }

    private boolean checkProjectExist(){
        if(this.student.getProject() != null){
            return true;
        }
        return false;
    }

}
