package main.Models;

public class Request {
    protected String requestID;
    protected String requesterID;
    protected String requesteeID;
    protected int requestType;
    protected boolean requestStatus;
    protected String comments;
    

    //student requesting to register for proj --type1
    public Request(Student s, int projectID){
        requestStatus = false;
    }
    //student requesting to change proj title --type2
    public Request(Student s, String newProjectTitle){
        requestStatus = false;
    }
    //student requesting to de-register proj --type3
    public Request(Student s, Project project){
        requestStatus = false;
    }
    //transferring student to replacement supervisor --type4
    public Request(int projectID, int supervisorID){
        requestStatus = false;
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
