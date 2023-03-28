package main.Models;

import main.Models.requestStatus_ENUM;

public abstract class Request {
    protected String requestID;
    protected Object requester;
    protected String projectID;
    protected int requestType;
    protected requestStatus_ENUM requestStatus;
    protected String comments;

    public Request(Object o, String projectID){
        this.requestStatus = requestStatus_ENUM.PENDING;
        this.requester = o;
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
    public Object getRequester() {
        return requester;
    }
    public void setRequester(Object requester) {
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
