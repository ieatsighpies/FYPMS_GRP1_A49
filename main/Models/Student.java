package main.Models;

public class Student extends User{
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
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getUserID(){
        return userID;
    }
    public boolean getAssignStatus(){
        return assignStatus;
    }
    public boolean getDeregisteredStatus(){
        return deregistered;
    }
    public Project getProject(){
        return project;
    }

    public void setProject(Student s, Project project){
        if(s.assignStatus || s.deregistered== true){
            s.project = project;
            project.setStudent(s);
        }
        else
            System.out.println("Student has already registered for a project.");
    }
}