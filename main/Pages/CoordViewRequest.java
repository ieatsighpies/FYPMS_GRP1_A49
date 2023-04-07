package main.Pages;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Request;
import main.Models.TransferStudentReq;
import main.Utils.ConsoleUtils;
import main.Utils.FileHandler;

import main.Utils.ReqType1;
import main.Utils.ReqType2;
public class CoordViewRequest extends Page{
    private Scanner sc = new Scanner(System.in);
    private Coordinator coordinator;

    public CoordViewRequest(Page previousPage, Coordinator coordinator) {
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

        List<String> PendingID = new ArrayList<>();
        this.coordinator.updateRequest();
        for(Request r : this.coordinator.getRequests()){
            if(r.getRequestType().equals("4") && r.getRequestStatus().toString().equals("PENDING")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request to Transfer Student", r.getRequestStatus().toString());
                PendingID.add(r.getRequestID());
            }
            else if(r.getRequestType().equals("3")&& r.getRequestStatus().toString().equals("PENDING")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project Title Change", r.getRequestStatus().toString());
                PendingID.add(r.getRequestID());
            }
            else if(r.getRequestType().equals("2")&& r.getRequestStatus().toString().equals("PENDING")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project De-registation", r.getRequestStatus().toString());
                PendingID.add(r.getRequestID());
            }
            else if(r.getRequestType().equals("1")&& r.getRequestStatus().toString().equals("PENDING")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project Registration", r.getRequestStatus().toString());
                PendingID.add(r.getRequestID());
            }

        }
        System.out.println("╚══════════════════╩══════════════════════════════════════════════════════════════════════╩════════════╝");
        
        // ask for requestID to view
        String requestID;
        while(true) {
            boolean valid=false;
            System.out.print("Enter requestID to process(leave blank to return): ");
            requestID = sc.nextLine().trim();

            // check if request exist
            for (int i = 0; i < PendingID.size(); i++){
                if (PendingID.get(i).equals(requestID)){valid = true;}
            }

            // return if blank
            if (requestID.isBlank()) {
                return this.getPreviousPage();
            }
            if(valid){break;}
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

        // process request
        String action;
        while(true) {
            System.out.print("Choose \u001B[32mAPPROVE\u001B[0m or \u001B[31mREJECT\u001B[0m for the request(blank for return): ");
            action = sc.nextLine();

            if (action.isBlank()) {
                return this.getPreviousPage();
            }
            else if(action.equals("APPROVE") || action.equals("REJECT")){break;}

        }

        r.processRequest(action);

        System.out.println("Enter any input to return");
        String temp = sc.nextLine();
        return this.getPreviousPage();
    }


}
