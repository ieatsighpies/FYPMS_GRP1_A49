package main.Pages;

import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Supervisor;
import main.Utils.ConsoleUtils;
import main.Utils.FileHandler;

public class SupervisorEditTitle extends Page{
    private Scanner sc = new Scanner(System.in);
    private Supervisor staff;

    public SupervisorEditTitle(Page previousPage, Supervisor staff) {
        super(previousPage);
        this.staff = staff;
    }

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
            System.out.print("Enter projectID to change title(empty input to return): ");
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

        // ask for new title
        String newTitle;

        while(true){
            System.out.print("Enter new project title(empty input to return): ");
            newTitle = sc.nextLine().trim();
            if(newTitle.isBlank()){
                return this.getPreviousPage();
            }
            if(newTitle.contains(",")){
                System.out.println("Title cannot contain commas (,)");
                continue;
            }
            if(newTitle.length() > 80){
                System.out.println("Title length cannot exceed 80");
                continue;
            }
            break;
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

        // write to file
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        String[] data = FileHandler.readFile(filepath, projectID, 0);
        String newString = data[0]+","+data[1]+","+data[2]+","+newTitle+","+data[4]+","+data[5]+","+data[6];
        FileHandler.writeFile(filepath, projectID, 0, newString);

        ConsoleUtils.clearScreen();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║        -Project Title Changed-        ║");
        System.out.println("╚═══════════════════════════════════════╝");

        this.staff.updateProject();
        System.out.print("Enter any input to return: ");
        String hold = sc.nextLine();
        return this.getPreviousPage();
    }


}
