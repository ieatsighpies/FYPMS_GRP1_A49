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
}
