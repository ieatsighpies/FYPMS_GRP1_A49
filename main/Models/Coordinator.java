package main.Models;

import java.util.ArrayList;

import main.Models.Project;

public class Coordinator extends Supervisor{

    public Coordinator(String name, String email, ArrayList<Project> projects){
        super(name, email,projects);
    }
}
