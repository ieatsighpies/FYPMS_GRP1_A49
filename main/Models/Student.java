package main.Models;

public class    Student extends User{
    //student HAS-A project
    private Project project;
    private boolean assignStatus;
    //to differentiate from students without a project
    private boolean deregistered;

    public Student(String name, String email){
        super(name, email);
        this.project = null;
        this.assignStatus = false;
        this.deregistered = false;
    }
    public String getName(){
        return Student.name;
    }
    public String getEmail(){
        return Student.email;
    }
    public String getUserID(){
        return Student.userID;
    }
    public boolean getAssignStatus(){
        return Student.assignStatus;
    }
    public boolean getDeregisteredStatus(){
        return Student.deregistered;
    }
    public Project getProject(){
        return Student.project;
    }

    public void setProject(Student s, Project project){
        if(s.getAssignStatus){
            Student.project = project.projectID;
            project.studentID = s.getUserID;
        }
        else
            System.out.println("Student has already registered for a project.");
    }
}