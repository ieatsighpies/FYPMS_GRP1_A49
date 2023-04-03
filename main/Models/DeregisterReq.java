package main.Models;


public class DeregisterReq extends Request {
    public DeregisterReq(String requestID, String requesterID, String requesteeID, String requestType,
                         requestStatus_ENUM status, String projectID, String comment) {
        super(requestID, requesterID, requesteeID, requestType, status, projectID, comment);
    }
    
    @Override
    public String getComments() {
        return super.getComments();
    }

    @Override
    public String getProjectID() {
        return super.getProjectID();
    }

    @Override
    public String getRequestID() {
        return super.getRequestID();
    }

    @Override
    public requestStatus_ENUM getRequestStatus() {
        return super.getRequestStatus();
    }

    @Override
    public String getRequestType() {
        return super.getRequestType();
    }

    @Override
    public String getRequesteeID() {
        return super.getRequesteeID();
    }

    @Override
    public String getRequesterID() {
        return super.getRequesterID();
    }

    @Override
    public void setComments(String comments) {
        super.setComments(comments);
    }

    @Override
    public void setProjectID(String projectID) {
        super.setProjectID(projectID);
    }

    @Override
    public void setRequestID(String requestID) {
        super.setRequestID(requestID);
    }

    @Override
    public void setRequestStatus(requestStatus_ENUM requestStatus) {
        super.setRequestStatus(requestStatus);
    }

    @Override
    public void setRequestType(String requestType2) {
        super.setRequestType(requestType2);
    }

    @Override
    public void setRequesteeID(String requesteeID) {
        super.setRequesteeID(requesteeID);
    }

    @Override
    public void setRequesterID(String requesterID) {
        super.setRequesterID(requesterID);
    }
}
