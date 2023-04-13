package main.Pages;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Project;
import main.Models.projectStatus_ENUM;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleUtils;
import main.Utils.FileHandler;
import main.Utils.UIDGenerator;

/**
* Page for Coordinator to request to transfer student
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class CoordTransferStudent extends Page{
    /**
     * current coordinator object
     */
    private Coordinator coordinator;
    /**
     * scanner for user input
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * Base constructor for this page
     * @param previousPage the previous page
     * @param coordinator the curretn user
     */
    public CoordTransferStudent(Page previousPage, Coordinator coordinator) {
        super(previousPage);
        this.coordinator = coordinator;
    }

    /**
    * Main executable for this page
    * 
    * Please see the {@link main.Pages.Page} class for abstract method
    * @return next page {@link main.Pages.CoordinatorMain}
    */
    @Override
    public Page executable() {
        
        // print menu 
        this.coordinator.printProjects(2);
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║           -Transfer Student-          ║");
        System.out.println("╚═══════════════════════════════════════╝");

        // get project ID
        String projectID;
        while(true){
            System.out.print("Enter projectID(empty to return): ");
            projectID = sc.nextLine();

            // return if blank
            if(projectID.isBlank()){
                return this.getPreviousPage();
            }

            // check if project exist
            Project p = coordinator.getProjectbyID(projectID);
            if(p == null){
                System.out.println("Invalid projectID!");
                continue;
            }
            else if(!p.getSupervisorID().equalsIgnoreCase(this.coordinator.getUserID())){
                System.out.println("Not your project!");
                continue;
            }

            // check if allocated
            if(p.getStatus() == projectStatus_ENUM.ALLOCATED){
                break;
            }
            System.out.println("Selected project is not ALLOCATED");
        }

        // get new supervisorID
        String replacementID;
        while(true){
            System.out.print("Enter replacement supervisorID(empty to return): ");
            replacementID = sc.nextLine().trim();

            // return if blank
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
        try(FileWriter fw = new FileWriter(filepath, true)){
            PrintWriter writer = new PrintWriter(fw);
            writer.println(UID+","+coordinator.getUserID()+",NaN,4,"+requestStatus_ENUM.PENDING.toString()+","+projectID+","+comment+","+replacementID+",NaN");
        } catch(IOException e){
            e.printStackTrace();
        }

        // reload request
        coordinator.updateRequest();

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
