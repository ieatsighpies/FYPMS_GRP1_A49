package main.Models;

import main.Models.requestStatus_ENUM;

public abstract class Request {
    private String requestID;
    private User requester;
    private String projectID;
    private int requestType;
    private requestStatus_ENUM requestStatus;
    private String comments;

    public Request(User u, String projectID){
        this.requestStatus = requestStatus_ENUM.PENDING;
        this.requester = u;
        this.projectID = projectID;
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
    public User getRequester() {
        return requester;
    }
    public void setRequester(User requester) {
        this.requester = requester;
    }
    public String getProjectID() {
        return projectID;
    }
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
    public int getRequestType() {
        return requestType;
    }
    public void setRequestType(int requestType) {
        this.requestType = requestType;
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


}
