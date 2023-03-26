package main.Models;

import java.util.ArrayList;

import main.Models.Project;

public class Coordinator extends Supervisor{

    public Coordinator(String name, String email){
        super(name, email);
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
