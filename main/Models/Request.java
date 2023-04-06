package main.Models;

public abstract class Request {
    private String requestID;
    private String requesterID;
    private String requesteeID;
    private String projectID;
    private String requestType;
    private requestStatus_ENUM requestStatus;
    private String comments;

    public Request(String requestID, String requesterID, String requesteeID,
                    String requestType, requestStatus_ENUM status, String projectID, String comment){
        this.requestID = requestID;
        this.requesterID = requesterID;
        this.requesteeID = requesteeID;
        this.requestType = requestType;
        this.requestStatus = status;
        this.projectID = projectID;
        this.comments = comment;
    }
    public requestStatus_ENUM getRequestStatus() {
        return requestStatus;
    }
    public String getRequestID() {
        return requestID;
    }
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
    public String getRequesterID() {
        return requesterID;
    }
    public void setRequesterID(String requesterID) {
        this.requesterID = requesterID;
    }
    public String getProjectID() {
        return projectID;
    }
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType2) {
        this.requestType = requestType2;
    }
    public void setRequestStatus(requestStatus_ENUM requestStatus) {
        this.requestStatus = requestStatus;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getRequesteeID() {
        return requesteeID;
    }
    public void setRequesteeID(String requesteeID) {
        this.requesteeID = requesteeID;
    }
    public abstract void printInfo();


}
