package main.Pages;

import java.util.Collections;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Request;
import main.Utils.ConsoleColors;
import main.Utils.ConsoleUtils;
import main.Utils.ReqSortBy;
import main.Models.TransferStudentReq;
import main.Models.requestStatus_ENUM;

/**
* Page for Coordinator to all request's history 
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class CoordRequestHistory extends Page{
    private Scanner sc = new Scanner(System.in);
    private Coordinator coordinator;
    private static int coord_req_sortedby = 1;

    public CoordRequestHistory(Page previousPage, Coordinator coordinator) {
        super(previousPage);
        this.coordinator = coordinator;
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

        for(Request r : this.coordinator.getRequests()){
            if(r.getRequestType().equals("4") ){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║ %-10s\n", r.getRequestID(), "Request to Transfer Student", r.getRequestStatus().toString(), r.getRequestStatus().equals(requestStatus_ENUM.PENDING)?ConsoleColors.BLUE_BOLD_BRIGHT+"NEW!"+ConsoleColors.RESET:"");
            }
            else if(r.getRequestType().equals("3")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║ %-10s\n", r.getRequestID(), "Request for Project Title Change", r.getRequestStatus().toString(), r.getRequestStatus().equals(requestStatus_ENUM.PENDING)?ConsoleColors.BLUE_BOLD_BRIGHT+"NEW!"+ConsoleColors.RESET:"");
            }
            else if(r.getRequestType().equals("2")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║ %-10s\n", r.getRequestID(), "Request for Project De-registation", r.getRequestStatus().toString(), r.getRequestStatus().equals(requestStatus_ENUM.PENDING)?ConsoleColors.BLUE_BOLD_BRIGHT+"NEW!"+ConsoleColors.RESET:"");
            }
            else if(r.getRequestType().equals("1")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║ %-10s\n", r.getRequestID(), "Request for Project Registration", r.getRequestStatus().toString(), r.getRequestStatus().equals(requestStatus_ENUM.PENDING)?ConsoleColors.BLUE_BOLD_BRIGHT+"NEW!"+ConsoleColors.RESET:"");
            }
        
        }
        System.out.println("╚══════════════════╩══════════════════════════════════════════════════════════════════════╩════════════╝");

        // print menu
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║               -Options-               ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.printf("║ %-37.37s ║\n", "[1] Sort by ID "+(coord_req_sortedby==1?"(current)":""));
        System.out.printf("║ %-37.37s ║\n", "[2] Sort by Type "+(coord_req_sortedby==2?"(current)":""));
        System.out.printf("║ %-37.37s ║\n", "[3] Sort by Status "+(coord_req_sortedby==3?"(current)":""));
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
                Collections.sort(this.coordinator.getRequests(), ReqSortBy.ID);
                coord_req_sortedby = 1;
                return this;

            // sort by type
            case 2:
                Collections.sort(this.coordinator.getRequests(), ReqSortBy.Type);
                coord_req_sortedby = 2;
                return this;

            // sort by status
            case 3:
                Collections.sort(this.coordinator.getRequests(), ReqSortBy.Status);
                coord_req_sortedby = 3;
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

            // check if exist 
            if(this.coordinator.getRequestbyID(requestID) == null){
                System.out.println("Invalid requestID!");
                continue;
            }

            break;
        }

        // print request info
        Request r = coordinator.getRequestbyID(requestID);
        String info1 = null;
        String info2 = null;
        String info3 = null;
        if(r.getRequestType().equals("1")){
            info1 = coordinator.getProjectbyID(r.getProjectID()).getTitle();
            info2 = coordinator.getProjectbyID(r.getProjectID()).getSupervisorID();
            info3 = coordinator.getProjectbyID(r.getProjectID()).getSupervisorEmail();
        }
        else if(r.getRequestType().equals("2")){
            info1 = coordinator.getProjectbyID(r.getProjectID()).getTitle();
            info2 = coordinator.getProjectbyID(r.getProjectID()).getSupervisorID();
            info3 = coordinator.getProjectbyID(r.getProjectID()).getSupervisorEmail();
        }
        else if(r.getRequestType().equals("3")){
            info1 = coordinator.getProjectbyID(r.getProjectID()).getTitle();
        }
        else{
            info1 = coordinator.getProjectbyID(r.getProjectID()).getTitle();
            info2 = coordinator.getProjectbyID(r.getProjectID()).getSupervisorID();
            info3 = ((TransferStudentReq) r).getNewSupervisorID();
        }
        r.printInfo(info1, info2, info3);

        System.out.println("Enter any input to return:");
        String hold = sc.nextLine();

        return this;
    }
    
    
}
