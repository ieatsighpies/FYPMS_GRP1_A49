package main.Pages;

import java.util.Collections;
import java.util.Scanner;

import main.Models.Request;
import main.Models.Student;
import main.Utils.ConsoleUtils;
import main.Utils.FileHandler;
import main.Utils.SortBy;

public class StudentRequestHistory extends Page{
    private Scanner sc = new Scanner(System.in);
    private Student student;
    private static int student_req_sortedby = 1;

    public StudentRequestHistory(Page previousPage, Student student) {
        super(previousPage);
        this.student = student;
    }
                                         
    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ██████╗ ███████╗ ██████╗ ██╗   ██╗███████╗███████╗████████╗███████╗                 ║");
        System.out.println("║                  ██╔══██╗██╔════╝██╔═══██╗██║   ██║██╔════╝██╔════╝╚══██╔══╝██╔════╝                 ║");
        System.out.println("║                  ██████╔╝█████╗  ██║   ██║██║   ██║█████╗  ███████╗   ██║   ███████╗                 ║");
        System.out.println("║                  ██╔══██╗██╔══╝  ██║▄▄ ██║██║   ██║██╔══╝  ╚════██║   ██║   ╚════██║                 ║");
        System.out.println("║                  ██║  ██║███████╗╚██████╔╝╚██████╔╝███████╗███████║   ██║   ███████║                 ║");
        System.out.println("║                  ╚═╝  ╚═╝╚══════╝ ╚══▀▀═╝  ╚═════╝ ╚══════╝╚══════╝   ╚═╝   ╚══════╝                 ║");
        System.out.println("╠══════════════════╦══════════════════════════════════════════════════════════════════════╦════════════╣");
        System.out.println("║ID                ║Request Type                                                          ║Status      ║");

        for(Request r : this.student.getRequests()){
            if(r.getRequestType().equals("1")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project Registration ", r.getRequestStatus().toString());
            }
            else if(r.getRequestType().equals("2")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project De-registration ", r.getRequestStatus().toString());
            }
            else if(r.getRequestType().equals("3")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project Title Change ", r.getRequestStatus().toString());
            }
        
        }
        System.out.println("╚══════════════════╩══════════════════════════════════════════════════════════════════════╩════════════╝");

        // print menu
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║               -Options-               ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.printf("║ %-37.37s ║\n", "[1] Sort by ID "+(student_req_sortedby==1?"(current)":""));
        System.out.printf("║ %-37.37s ║\n", "[2] Sort by Type "+(student_req_sortedby==2?"(current)":""));
        System.out.printf("║ %-37.37s ║\n", "[3] Sort by Status "+(student_req_sortedby==3?"(current)":""));
        System.out.println("║ [4] More Information                  ║");
        System.out.println("╚═══════════════════════════════════════╝");

        // get user input
        String option;
        System.out.print("Enter choice (empty to return): ");
            option = sc.nextLine().trim();
        
        while(!(option.matches("^[1-4]{1}$"))){
            
            if(option.isBlank()){               // return if blank
                return this.getPreviousPage();
            }
            System.out.println("Invalid option!");
            System.out.print("Enter choice (empty to return): ");
            option = sc.nextLine().trim();
        }

        int x = Integer.parseInt(option);

        switch(x){

            // sort by id
            case 1:
                Collections.sort(this.student.getRequests(), SortBy.ID);
                student_req_sortedby = 1;
                return this;

            // sort by type
            case 2:
                Collections.sort(this.student.getRequests(), SortBy.Type);
                student_req_sortedby = 2;
                return this;

            // sort by status
            case 3:
                Collections.sort(this.student.getRequests(), SortBy.Status);
                student_req_sortedby = 3;
                return this;

            // get more info
            case 4:
                System.out.println("╔═══════════════════════════════════════╗");
                System.out.println("║         -Request Information-         ║");
                System.out.println("╚═══════════════════════════════════════╝");
                break;
        }

        // ask for requestID to view
        String requestID;
        while(true){
            System.out.print("Enter requestID to view more information(empty to return): ");
            requestID = sc.nextLine().trim();

            // return if blank
            if(requestID.isBlank()){
                return this.getPreviousPage();
            }

            // check if exist and is this student's request
            if(this.student.getRequestbyID(requestID) == null){
                System.out.println("Invalid requestID!");
                continue;
            }

            break;
        }

        // print request info
        Request r = this.student.getRequestbyID(requestID);
        String pfilepath = System.getProperty("user.dir")+"\\main\\Data\\project_record.csv";
        String[] projectData = FileHandler.readFile(pfilepath, r.getProjectID(), 0);
        String info1 = null;
        String info2 = null;
        String info3 = null;
        if(r.getRequestType().equals("1")){
            info1 = projectData[3];
            info2 = projectData[1];
            info3 = projectData[2];
        }
        else if(r.getRequestType().equals("2")){
            info1 = projectData[3];
            info2 = projectData[1];
            info3 = projectData[2];
        }
        else if(r.getRequestType().equals("3")){
            info1 = projectData[3];
        }
        r.printInfo(info1, info2, info3);

        System.out.print("Enter any input to continue: ");
        String hold = sc.nextLine();

        return this;

    }
}
