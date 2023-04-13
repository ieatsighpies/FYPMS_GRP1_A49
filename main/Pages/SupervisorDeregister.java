package main.Pages;

import java.util.Scanner;

import main.Models.Supervisor;
import main.Utils.ConsoleUtils;
import main.Utils.FileHandler;

/**
 * SupervisorDeregister is a subclass of Page that allows supervisor to deregister project
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class SupervisorDeregister extends Page{
    private Scanner sc = new Scanner(System.in);
    private Supervisor staff;
    /**
     * SupervisorDeregister constructor
     *
     */
    public SupervisorDeregister(Page previousPage, Supervisor staff) {
        super(previousPage);
        this.staff = staff;
    }
    /**
     * Main executable for SupervisorDeregister page
     * @return next page {@link main.Pages.StaffMain)
     */
    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();

        // check if supervisor have created any project
        if(this.staff.getProjects().isEmpty()){
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║       -\u001B[31mAccess Denied: You did not create any projects\u001B[0m-       ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.print("Enter any input to return:");
            String hold = sc.nextLine();
            return this.getPreviousPage();
        }

        // if have project ask for project ID
        this.staff.printProjects();
        String projectID;
        while(true){
            System.out.print("Enter projectID to deregister(empty input to return): ");
            projectID = sc.nextLine().trim();
            if(projectID.isBlank()){
                return this.getPreviousPage();
            }
            if(this.staff.getProjectbyID(projectID) == null){
                System.out.println("Invalid projectID!");
                continue;
            }
            break;
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

        // write to file
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        String[] data = FileHandler.readFile(filepath, projectID, 0);
        String newString = data[0]+","+data[1]+","+data[2]+","+data[3]+","+data[4]+",NaN,Nan";
        FileHandler.writeFile(filepath, projectID, 0, newString);

        ConsoleUtils.clearScreen();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║        -Project Deregistered-        ║");
        System.out.println("╚═══════════════════════════════════════╝");

        System.out.print("Enter any input to return: ");
        String hold = sc.nextLine();
        return this.getPreviousPage();
    }


}
