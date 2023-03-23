package main.Models;

public class Request {
    protected boolean requestStatus;

    //student requesting to register for proj
    public Request(Student s, int projectID){
        requestStatus = false;
    }
    //student requesting to change proj title
    public Request(Student s, String newProjectTitle){
        requestStatus = false;
    }
    //student requesting to de-register proj
    public Request(Student s, Project s.project){
        requestStatus = false;
    }
    //transferring student to replacement supervisor
    public Request(int projectID, int supervisorID){
        requestStatus = false;
    }
}
