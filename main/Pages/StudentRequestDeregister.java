package main.Pages;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import main.Models.Request;
import main.Models.Student;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleUtils;
import main.Utils.UIDGenerator;


public class StudentRequestDeregister extends Page{
    private Scanner sc = new Scanner(System.in);
    private Student student;

    public StudentRequestDeregister(Page previousPage, Student student){
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

        // check if already have a pending deregister project request
        for(Request r : this.student.getRequests()){
            if(r.getRequestType().equals("2") && r.getRequestStatus().equals(requestStatus_ENUM.PENDING)){
                System.out.println("╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║           -\u001B[31mAccess Denied: Your request in PENDING\u001B[0m-           ║");
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
                System.out.print("Enter any input to return:");
                String hold = sc.nextLine();
                return this.getPreviousPage();
            }
        }

        // if have project ask if want to de-reg
        this.student.printProject();
        String newAnswer;
        while(true){
            System.out.print("Do you want to deregister this project(Y/N)? ");
            newAnswer = sc.nextLine().trim();
            if(newAnswer.equalsIgnoreCase("N")) {
                return this.getPreviousPage();
            }
            else if (newAnswer.equalsIgnoreCase("Y")){
                break;
            }
            else{System.out.println("Invalid input!");}
        }


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
            writer.println(UID+","+student.getUserID()+","+this.student.getProject().getSupervisorID()+",2,"+requestStatus_ENUM.PENDING.toString()+","+this.student.getProject().getID()+",NaN,NaN,NaN");

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


