package main.Models;

public class Project {
    private int projectID;
    private String title;
    private String studentID;
    private String supervisorID;
    private boolean status;

    public int getID(){return this.projectID;}
    public String getTitle(){return this.title;}
    public String getStudentID(){return this.studentID;}
    public String getSupervisorID(){return this.supervisorID;}
    public boolean getStatus(){return this.status;}

    public void setStudent(Student s){this.studentID= s.userID;}
    public void setSupervisor(Faculty f){this.supervisorID= f.userID;}
    public void setTitle(String s){this.title=s;}

}

