package main.Models;

public class Student extends User implements Request {
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
        if(s.assignStatus || s.deregistered== false){
            s.project = project;
            project.setStudent(s);
        }
        else
            System.out.println("Student has already registered for a project.");
    }
    @Override
    public void request(String newProjectTitle){
        if((this.project.getTitle()).compareTo(newProjectTitle)==0) return;
        else{
            
        }
    }
    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}