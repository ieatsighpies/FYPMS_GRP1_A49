package main.Pages;

import java.util.Scanner;

import main.Models.Request;
import main.Models.Supervisor;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleUtils;

public class supervisorViewRequest extends Page{
    private Supervisor staff;
    private Scanner sc = new Scanner(System.in);

    public supervisorViewRequest(Page previousPage, Supervisor staff) {
        super(previousPage);
        this.staff = staff;
    }

    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();

        // print requests
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ██████╗ ███████╗ ██████╗ ██╗   ██╗███████╗███████╗████████╗███████╗                 ║");
        System.out.println("║                  ██╔══██╗██╔════╝██╔═══██╗██║   ██║██╔════╝██╔════╝╚══██╔══╝██╔════╝                 ║");
        System.out.println("║                  ██████╔╝█████╗  ██║   ██║██║   ██║█████╗  ███████╗   ██║   ███████╗                 ║");
        System.out.println("║                  ██╔══██╗██╔══╝  ██║▄▄ ██║██║   ██║██╔══╝  ╚════██║   ██║   ╚════██║                 ║");
        System.out.println("║                  ██║  ██║███████╗╚██████╔╝╚██████╔╝███████╗███████║   ██║   ███████║                 ║");
        System.out.println("║                  ╚═╝  ╚═╝╚══════╝ ╚══▀▀═╝  ╚═════╝ ╚══════╝╚══════╝   ╚═╝   ╚══════╝                 ║");
        System.out.println("╠══════════════════╦══════════════════════════════════════════════════════════════════════╦════════════╣");
        System.out.println("║ID                ║Request Type                                                          ║Status      ║");

        for(Request r : this.staff.getRequests()){
            if(r.getRequestType().equals("3") && r.getRequestStatus().equals(requestStatus_ENUM.PENDING)){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project Title Change", r.getRequestStatus().toString());
            }
        }
        System.out.println("╚══════════════════╩══════════════════════════════════════════════════════════════════════╩════════════╝");
        
        // ask for request id to view
        String requestID;
        Request r;
        while(true){
            System.out.print("Enter requestID to view(empty to return): ");
            requestID = sc.nextLine().trim();

            // return if blank
            if(requestID.isBlank()){
                return this.getPreviousPage();
            }

            // check if request exist
            r = staff.getRequestbyID(requestID);
            if(r == null){
                System.out.println("Invalid requestID");
                continue;
            }

            // check if is type 3 request
            if(!r.getRequestType().equals("3")){
                System.out.println("Cannot access this request");
                continue;
            }
            
            // check if is PENDING
            if(!r.getRequestStatus().equals(requestStatus_ENUM.PENDING)){
                System.out.println("Cannot access this request");
                continue;
            }
            break;
        }

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.printf("║ Request ID: \u001B[33m%-45s\u001B[0m║\n", r.getRequestID());
        System.out.printf("║ Requester ID: \u001B[33m%-45s\u001B[0m║\n", r.getRequesterID());
        System.out.printf("║ Requestee ID: \u001B[33m%-45s\u001B[0m║\n", r.getRequestID());
        System.out.println("╠══════════════════════════════════════════════════════════╣"); 
        System.out.printf("║ Current Title: \u001B[33m%-80s\u001B[0m║\n", staff.getProjectbyID(r.getProjectID()).getTitle());
        System.out.printf("║ New Title: \u001B[33m%-45s\u001B[0m║\n", r.getRequestID());
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        String hold = sc.nextLine();
        return this.getPreviousPage();
    }
    

}
