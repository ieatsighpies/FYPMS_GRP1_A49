package main.Pages;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import main.Models.Project;
import main.Models.Supervisor;
import main.Models.projectStatus;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleUtils;
import main.Utils.FileHandler;
import main.Utils.UIDGenerator;

public class TransferStudent extends Page{
    Scanner sc = new Scanner(System.in);
    Supervisor staff;

    public TransferStudent(Page previousPage, Supervisor staff){
        super(previousPage);
        this.staff = staff;
    }

    @Override
    public Page executable(){

        // print menu
        this.staff.printProjects();

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║           -Transfer Student-          ║");
        System.out.println("╚═══════════════════════════════════════╝");

        // get project ID
        String projectID;
        while(true){
            System.out.print("Enter projectID(empty to return): ");
            projectID = sc.nextLine();

            // reutrn if blank
            if(projectID.isBlank()){
                return this.getPreviousPage();
            }

            // check if project exist
            Project p = staff.getProjectbyID(projectID);
            if(p == null){
                System.out.println("Invalid projectID or project not created by you!");
                continue;
            }

            // check if duplicate request
            String filepath_record = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
            String dupcheck[] = FileHandler.readFile(filepath_record, projectID, 5);
            if(dupcheck != null && dupcheck[3].equals("4") && dupcheck[4].equals(requestStatus_ENUM.PENDING.toString())){
                System.out.println("Duplicate request!");
                continue;
            }

            // check if allocated
            if(p.getStatus() == projectStatus.ALLOCATED){
                break;
            }
            System.out.println("Selected project is not ALLOCATED");
            
        }

        // get supervisorID
        String replacementID;
        while(true){
            System.out.print("Enter replacement supervisorID(empty to return): ");
            replacementID = sc.nextLine();

            //return if blank
            if(replacementID.isBlank()){
                return this.getPreviousPage();
            }

            // check if ID exist
            String filepath_faculty = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
            boolean userCheck = FileHandler.findFile(filepath_faculty, replacementID, 2);
            if(!userCheck){
                System.out.println("Invalid UserID!");
                continue;
            }
            
            // check if replacement supervisor is already supervisor 2 project
            // String data[] = FileHandler.readFile(filepath_faculty, replacementID, 2);
            // Supervisor replacement = new Supervisor(data[0], data[1]);
            // if(replacement.getSupCount() >= 2){
            //     System.out.println("This supervisor is already supervising 2 projects");
            // }

            break;
        }

        // add comments
        String comment;
        while(true){
            System.out.print("Add a comment(optional): ");
            comment = sc.nextLine();

            if(comment.contains(",")){
                System.out.println("Comment cannot contain commas (,)");
                continue;
            }

            break;
        }

        // generate request
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        Long UID = UIDGenerator.generateLongId();
        try (FileWriter fw = new FileWriter(filepath, true)) {
            PrintWriter writer = new PrintWriter(fw);
            writer.println(UID + "," + staff.getUserID() + "," + replacementID + ",4," + requestStatus_ENUM.PENDING.toString() + "," +projectID+ ","+ comment);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConsoleUtils.clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                      -Request Sent-                      ║");
        System.out.println("╠══════════════════╦════════════════════════════╦══════════╣");
        System.out.println("║RequestID         ║Replacement Supervisor ID   ║ Status   ║");
        System.out.println("╠══════════════════╬════════════════════════════╬══════════╣");
        System.out.printf("║%-18.18s║%-20.20s\t║%-10.10s║\n", UID.toString(), replacementID, "PENDING");
        System.out.println("╚══════════════════╩════════════════════════════╩══════════╝");
        System.out.println();
        System.out.print("Enter any input to return to Main Menu:");
        String hold = sc.nextLine();
            
        return this.getPreviousPage();
    }
}
