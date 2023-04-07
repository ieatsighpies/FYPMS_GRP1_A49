package main.Models;

public class TransferStudentReq extends Request{

    private String newSupervisorID;

    public TransferStudentReq(String requestID, String requesterID, String requesteeID, String requestType,
            requestStatus_ENUM status, String projectID, String comment, String newSupervisorID) {
        super(requestID, requesterID, requesteeID, requestType, status, projectID, comment);
        this.newSupervisorID = newSupervisorID;
    }

    public String getNewSupervisorID() {
        return newSupervisorID;
    }

    public void setNewSupervisorID(String newSupervisorID) {
        this.newSupervisorID = newSupervisorID;
    }

    @Override
    public void printInfo(String projectTitle, String currentSup, String replacementSup) {
        System.out.println("╔══════════════════════════════════════╦═════════════════════╗");
        System.out.printf("║ Request ID: \u001B[33m%-25s\u001B[0m║Status: \u001B[33m%-13s\u001B[0m║ \n", this.getRequestID(), this.getRequestStatus().toString());
        System.out.println("╠══════════════════════════════════════╩═════════════════════╣");
        System.out.printf("║ Request from: \u001B[33m%-43s\u001B[0m║\n", this.getRequesterID());
        System.out.printf("║ Request to: \u001B[33m%-45s\u001B[0m║\n", "FYP Coordinator");
        System.out.println("╠══════════════════════════════════════╩═════════════════════╣");
        System.out.printf("║ Current SupervisorID: \u001B[33m%-43s\u001B[0m║\n", currentSup);
        System.out.printf("║ Replacement SupervisorID: \u001B[33m%-45s\u001B[0m║\n", replacementSup);
        System.out.println("╠════════════════════════════════════════════════════════════╩═══════════════════════════════════╗"); 
        System.out.printf("║ Project Title: \u001B[33m%-80s\u001B[0m║\n", projectTitle);
        System.out.printf("║ Project ID: \u001B[33m%-84s\u001B[0m║\n", this.getProjectID());
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    @Override
    public void processRequest(String decision) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'proccessRequest'");
    }
}
