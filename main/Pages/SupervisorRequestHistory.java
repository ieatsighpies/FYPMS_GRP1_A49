package main.Pages;

import java.util.Scanner;

import main.Models.Request;
import main.Models.Supervisor;
import main.Utils.ConsoleUtils;

public class SupervisorRequestHistory extends Page{
    private Scanner sc = new Scanner(System.in);
    private Supervisor staff;

    public SupervisorRequestHistory(Page previousPage, Supervisor staff) {
        super(previousPage);
        this.staff = staff;
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
        
        for(Request r : this.staff.getRequests()){
            if(r.getRequestType().equals("4")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request to Transfer Student (Outgoing)", r.getRequestStatus().toString());
            }
            else if(r.getRequestType().equals("3")){
                System.out.println("╠══════════════════╬══════════════════════════════════════════════════════════════════════╬════════════╣");
                System.out.printf("║%-18.18s║%-70.70s║%-12.12s║\n", r.getRequestID(), "Request for Project Title Change (Incoming)", r.getRequestStatus().toString());
            }
        
        }
        System.out.println("╚══════════════════╩══════════════════════════════════════════════════════════════════════╩════════════╝");

        System.out.println("Enter any input to return:");
        String hold = sc.nextLine();

        return this.getPreviousPage();
    }

}
