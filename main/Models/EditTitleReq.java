package main.Models;

import main.Utils.FileHandler;

public class EditTitleReq extends Request{

    private String newTitle;

    public EditTitleReq(String requestID, String requesterID, String requesteeID,
                            String requestType, requestStatus_ENUM status, String projectID, String comment, String newTitle){
        super(requestID, requesterID, requesteeID, requestType, status, projectID, comment);
        this.newTitle = newTitle;
    }

    public String getTitle() {
        return newTitle;
    }

    public void setTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    @Override
    public void printInfo(String currentTitle) {
        System.out.println("╔══════════════════════════════════════╦═════════════════════╗");
        System.out.printf("║ Request ID: \u001B[33m%-25s\u001B[0m║Status: \u001B[33m%-13s\u001B[0m║ \n", this.getRequestID(), this.getRequestStatus().toString());
        System.out.println("╠══════════════════════════════════════╩═════════════════════╣");
        System.out.printf("║ Requester from: \u001B[33m%-43s\u001B[0m║\n", this.getRequesterID());
        System.out.printf("║ Requestee to: \u001B[33m%-45s\u001B[0m║\n", this.getRequestID());
        System.out.println("╠════════════════════════════════════════════════════════════╩═══════════════════════════════════╗"); 
        System.out.printf("║ Current Title: \u001B[33m%-80s\u001B[0m║\n", currentTitle);
        System.out.printf("║ New Title: \u001B[33m%-84s\u001B[0m║\n", this.getTitle());
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    @Override
    public void proccessRequest(String decision) {
        // changing request records
        String newStatus = decision.equals("APPROVE")
                            ? requestStatus_ENUM.APPROVED.toString()
                            : requestStatus_ENUM.REJECTED.toString();
        String rfilepath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        String[] data = FileHandler.readFile(rfilepath, this.getRequestID(), 0);
        String newString = data[0]+","+data[1]+","+data[2]+","+data[3]+","+newStatus+","+data[5]+","+data[6]+","+data[7]+","+data[8];
        FileHandler.writeFile(rfilepath, this.getRequestID(), 0, newString);

        // changing project records
        String pfilepath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        String[] data1 = FileHandler.readFile(pfilepath, this.getProjectID(), 0);
        String newString1 = data1[0]+","+data1[1]+","+data1[2]+","+this.newTitle+","+data1[4]+","+data1[5]+","+data1[6];
        FileHandler.writeFile(pfilepath, this.getProjectID(), 0, newString1);

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║          -Request processed-          ║");
        System.out.println("╚═══════════════════════════════════════╝");

    }

    
}