package main.Models;

public class Project {
    private int projectID;
    protected String supervisorName;
    protected String supervisorEmail;
    protected String supervisorID;
    protected String title;
    protected projectStatus status;
    protected String studentName;
    protected String studentEmail;
    protected String studentID;
    
    

    public int getID(){return this.projectID;}
    public String getTitle(){return this.title;}
    public String getStudentID(){return this.studentID;}
    public String getSupervisorID(){return this.supervisorID;}
    public projectStatus getStatus(){return this.status;}

    public void setStudent(Student s){this.studentID= s.userID;}
    public void setSupervisor(Supervisor f){this.supervisorID= f.userID;}
    //only supervisor can setTitle
    //public void setTitle(String s){this.title=s;}

}

