package main.Models;

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
    public void printInfo() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.printf("║ Request ID: \u001B[33m%-45s\u001B[0m║\n", this.getRequestID());
        System.out.printf("║ Requester ID: \u001B[33m%-45s\u001B[0m║\n", this.getRequesterID());
        System.out.printf("║ Requestee ID: \u001B[33m%-45s\u001B[0m║\n", this.getRequestID());
        System.out.println("╠══════════════════════════════════════════════════════════╣"); 
        System.out.printf("║ Current Title: \u001B[33m%-80s\u001B[0m║\n", this.getTitle());
        System.out.printf("║ New Title: \u001B[33m%-45s\u001B[0m║\n", this.getRequestID());
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}