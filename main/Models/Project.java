package main.Models;

public class Project {
    private int projectID;
    protected String title;
    protected String studentID;
    protected String supervisorID;
    protected projectStatus status;

    public int getID(){return this.projectID;}
    public String getTitle(){return this.title;}
    public String getStudentID(){return this.studentID;}
    public String getSupervisorID(){return this.supervisorID;}
    public projectStatus getStatus(){return this.status;}

    public void setStudent(Student s){this.studentID= s.userID;}
    public void setSupervisor(Supervisor f){this.supervisorID= f.userID;}
    public void setTitle(String s){this.title=s;}

}

