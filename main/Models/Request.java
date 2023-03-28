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

    //student requesting to register for proj --type1
    // public Request(Student s, int projectID){
    //     requestStatus = requestStatus_ENUM.PENDING;
    //     if(s.getProject()==null){

    //     }
    //     if(s.getDeregisteredStatus()==true)

    // }
    // //student requesting to change proj title --type2
    // public Request(Student s, String newProjectTitle){
    //     requestStatus = requestStatus_ENUM.PENDING;
    // }
    // //student requesting to de-register proj --type3
    // public Request(Student s, Project project){
    //     requestStatus = requestStatus_ENUM.PENDING;
    // }
    // //transferring student to replacement supervisor --type4
    // public Request(int projectID, int supervisorID){
    //     requestStatus = requestStatus_ENUM.PENDING;
    // }
    
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
    public String getRequesteeID() {
        return requesteeID;
    }
    public void setRequesteeID(String requesteeID) {
        this.requesteeID = requesteeID;
    }
    public int getRequestType() {
        return requestType;
    }
    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
    public boolean isRequestStatus() {
        return requestStatus;
    }
    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
}
