package main.Pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import main.Models.Project;
import main.Models.Request;
import main.Models.Student;
import main.Models.projectStatus_ENUM;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleUtils;
import main.Utils.FileHandler;


public class StudentProjectView extends Page{
    private Scanner sc = new Scanner(System.in);
    private Student student;

    public StudentProjectView(Page previousPage, Student student){
        super(previousPage);
        this.student = student;
    }

    @Override
    public Page executable(){
        ConsoleUtils.clearScreen();

        //check if student have a project
        if(student.getProject() != null){
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║    -\u001B[31mAccess Denied: You are already allocated a project!\u001B[0m-     ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.print("Enter any input to return:");
            String hold = sc.nextLine();
            return this.getPreviousPage();
        }

        // check if student have deregistered before
        if(student.getDeregisteredStatus() == true){
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║    -\u001B[31mAccess Denied: You have deregistered a project before!\u001B[0m-   ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.print("Enter any input to return:");
            String hold = sc.nextLine();
            return this.getPreviousPage();
        }

        // print avaliable project
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                             ██████╗ ██████╗  ██████╗      ██╗███████╗ ██████╗████████╗███████╗                                             ║");
        System.out.println("║                                             ██╔══██╗██╔══██╗██╔═══██╗     ██║██╔════╝██╔════╝╚══██╔══╝██╔════╝                                             ║");
        System.out.println("║                                             ██████╔╝██████╔╝██║   ██║     ██║█████╗  ██║        ██║   ███████╗                                             ║");
        System.out.println("║                                             ██╔═══╝ ██╔══██╗██║   ██║██   ██║██╔══╝  ██║        ██║   ╚════██║                                             ║");
        System.out.println("║                                             ██║     ██║  ██║╚██████╔╝╚█████╔╝███████╗╚██████╗   ██║   ███████║                                             ║");
        System.out.println("║                                             ╚═╝     ╚═╝  ╚═╝ ╚═════╝  ╚════╝ ╚══════╝ ╚═════╝   ╚═╝   ╚══════╝                                             ║");
        System.out.println("╠══════════════════╦════════════════════════════════════════════════════════════════════════════════════╦════════════════╦═════════════════════════╦═════════╣");
        System.out.println("║ID                ║Project Title                                                                       ║Supervisor Name ║Supervisor Email         ║Status   ║");

        String currentLine;
        String data[];
        String filePath = System.getProperty("user.dir")  + "\\main\\Data\\project_record.csv";
        int col = 4;

        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split("\\s*,\\s*");

                //print if AVAILABLE
                if(data[col].equals(projectStatus_ENUM.AVAILABLE.toString())){
                    System.out.println("╠══════════════════╬════════════════════════════════════════════════════════════════════════════════════╬════════════════╬═════════════════════════╬═════════╣");
                    System.out.printf("║%-18.18s║%-80.80s\t║%-16.16s║%-25.25s║%-9.9s║\n", data[0],data[3],data[1],data[2].toUpperCase(),data[4]);
                }
            }
            System.out.println("╚══════════════════╩════════════════════════════════════════════════════════════════════════════════════╩════════════════╩═════════════════════════╩═════════╝");
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }

        // check if student already requested for any projects
        for(Request r : student.getRequests()){
            if(r.getRequestType().equals("1") && r.getRequestStatus().equals(requestStatus_ENUM.PENDING)){
                System.out.print("Enter any input to return:");
                String hold = sc.nextLine();
                return this.getPreviousPage();
            }
        }

        // print menu
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║          -Project Selection-          ║");
        System.out.println("╚═══════════════════════════════════════╝");

        // select option
        String projectID;
        String projectData[];
        String fp = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        System.out.print("Enter Project ID(empty input to back): ");
        projectID = sc.nextLine().trim();
        System.out.println();
        // CHeck if dereg
        if (!student.getDeregisteredStatus()){
            System.out.println("Already registered! (any input to back): ");
            String hold= sc.nextLine().trim();
            return this.getPreviousPage();
        }

        //check if projectID exist and available
        while(true){

            // back if empty input
            if(projectID.isBlank()){
                return this.getPreviousPage();
            }

            // read in project data
            projectData = FileHandler.readFile(fp, projectID, 0);

            //if does not exist
            if(projectData==null){
                System.out.println("Invalid ProjectID");
            }
            // exist and not avalaible
            else if(projectData!=null && !projectData[4].equals(projectStatus_ENUM.AVAILABLE.toString())){
                System.out.println("Project not available!");
            }
            else{
                break;
            }

            System.out.print("Enter Project ID(empty input to back): ");
            projectID = sc.nextLine().trim();
        }
        return new StudentProjectRegister(this, student, projectData);
    }
}
