package main.Models;

import main.Models.requestStatus_ENUM;

public class Request {
    protected String requestID;
    protected String requesterID;
    protected String requesteeID;
    protected int requestType;
    protected requestStatus_ENUM requestStatus;
    protected String comments;


    //student requesting to register for proj --type1
    public Request(Student s, int projectID){
        requestStatus = requestStatus_ENUM.PENDING;
        if(s.getProject()==null){

        }
        if(s.getDeregisteredStatus()==true)
            
    }
    //student requesting to change proj title --type2
    public Request(Student s, String newProjectTitle){
        requestStatus = requestStatus_ENUM.PENDING;
    }
    //student requesting to de-register proj --type3
    public Request(Student s, Project project){
        requestStatus = requestStatus_ENUM.PENDING;
    }
    //transferring student to replacement supervisor --type4
    public Request(int projectID, int supervisorID){
        requestStatus = requestStatus_ENUM.PENDING;
    }
}
