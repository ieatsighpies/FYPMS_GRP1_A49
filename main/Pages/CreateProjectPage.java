package main.Pages;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import main.Models.Project;
import main.Models.Supervisor;
import main.Models.projectStatus_ENUM;
import main.Utils.ConsoleUtils;
import main.Utils.UIDGenerator;

/**
* Page for Supervisor to create projects
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class CreateProjectPage extends Page{

    /**
     * scanner for user input
     */
    Scanner sc = new Scanner(System.in);

    /**
     * the current supervisor
     */
    Supervisor staff;
    /**
     * project to be created
     */
    Project project;

    /**
     * Base constructor for this page
     * 
     * @param previousPage the previous page
     * @param staff the current user
     */
    public CreateProjectPage(Page previousPage, Supervisor staff) {
        super(previousPage);
        this.staff = staff;
    }

    /**
    * Main executable for this page
    * 
    * Please see the {@link main.Pages.Page} class for abstract method
    * @return next page {@link main.Pages.StaffMain}, {@link main.Pages.CoordinatorMain}
    */
    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        // print menu
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                      -Project Creation-                  ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║      -Leave any field empty to return to Main Menu-      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        // get project title
        String title;
        System.out.print("Enter project title: ");
        title = sc.nextLine();
        while(true){
            if(title.isBlank()){
                return this.getPreviousPage();
            }
            if(title.contains(",")){
                System.out.println("Title cannot contain commas (,)");
                System.out.print("Enter project title: ");
                title = sc.nextLine();
            }
            else{
                break;
            }
        }

        // Get confirmation
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

        // write to file
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        Long UID = UIDGenerator.generateLongId();
        try (FileWriter fw = new FileWriter(filepath, true)) {
            PrintWriter writer = new PrintWriter(fw);
            writer.println(UID + "," + staff.getName() + "," + staff.getEmail() + "," + title + "," + projectStatus_ENUM.AVAILABLE.toString() + ",NaN,NaN");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // print added project
        ConsoleUtils.clearScreen();
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                            -Project Added-                                            ║");
        System.out.println("╠══════════════════╦════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ID                ║Project Title                                                                       ║");
        System.out.println("╠══════════════════╬════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║%-18.18s║%-80.80s\t║\n", UID.toString(), title);
        System.out.println("╚══════════════════╩════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.print("Enter any input to return to Main Menu:");
        String temp = sc.nextLine();
        staff.updateProject();
        return this.getPreviousPage();
    }

}
