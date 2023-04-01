package main.Pages;

import java.util.Scanner;

import main.Models.Coordinator;
import main.Models.Request;
import main.Utils.ConsoleUtils;

public class CoordRequestHistory extends Page{
    private Scanner sc = new Scanner(System.in);
    private Coordinator coordinator;

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
            if(r.getRequestType().equals("4")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request to Transfer Student", r.getRequestStatus().toString());
            }
            else if(r.getRequestType().equals("3")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project Title Change", r.getRequestStatus().toString());
            }
            else if(r.getRequestType().equals("2")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project De-registation", r.getRequestStatus().toString());
            }
            else if(r.getRequestType().equals("1")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project Registration", r.getRequestStatus().toString());
            }
        
        }
        System.out.println("╚══════════════════╩══════════════════════════════════════════════════════════════════════╩════════════╝");

        System.out.println("Enter any input to return:");
        String hold = sc.nextLine();

        return this.getPreviousPage();
    }
    
    
}
