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
}
