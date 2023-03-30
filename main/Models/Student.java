package main.Models;

public class Student extends User {
    //student HAS-A project
    protected Project project;
    //to differentiate from students without a project
    protected boolean deregistered;

    public Student(String name, String email){
        super(name, email);
        this.project = null;
        this.deregistered = false;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return email;
    }
    public String getUserID(){
        return userID;
    }
    public boolean getDeregisteredStatus(){
        return deregistered;
    }
    public Project getProject(){
        if(this.project.getStatus()==projectStatus_ENUM.ALLOCATED)
            return this.project;
        else if(this.project==null && this.deregistered==false){
            System.out.println("You have not registered for a project");
            return this.project;
        }
        if(this.deregistered==true){
            System.out.println("You are not allowed to make selection again as you deregistered your FYP");
            return this.project;
        }
        return null;
    }

}