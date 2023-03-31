package main.Models;

public class Coordinator extends Supervisor{

    public Coordinator(String name, String email){
        super(name, email);
        initialiseProject();
    }
    public String getName() {
        return super.name;
    }

    public String getEmail() {
        return super.email;
    }

    public String getUserID() {
        return super.userID;
    }
}
