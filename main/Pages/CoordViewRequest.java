package main.Pages;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Request;
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
        String id;
        while(true) {
            boolean valid=false;
            System.out.print("Choose a valid requestID to process (leave blank to return): ");
            id = sc.nextLine().toString();
            for (int i = 0; i < PendingID.size(); i++){
                if (PendingID.get(i).equals(id)){valid = true;}
            }

                if (id.isBlank()) {
                    return this.getPreviousPage();
                }
                if(valid){break;}

        }
        //

        ////
        String action;
        while(true) {
            System.out.print("Choose \u001B[32mAPPROVE\u001B[0m or \u001B[31mREJECT\u001B[0m for the request(blank for return): ");
            action = sc.nextLine();

            if (action.isBlank()) {
                return this.getPreviousPage();
            }
            else if(action.equals("APPROVE") || action.equals("REJECT")){break;}

        }
        //get request type
        // String filepath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        // String[] data = FileHandler.readFile(filepath, id, 0);
        // switch(data[3]){
        //     case 1: ReqType1.executeRequest(id,action);

        //     case 2: ReqType2.executeRequest(id,action);


        // }

        System.out.println("Enter any input to return");
        String temp = sc.nextLine();
        return this.getPreviousPage();
    }


}
