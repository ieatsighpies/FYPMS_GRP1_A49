package main.Models;

import java.util.ArrayList;

public class Coordinator extends Faculty{

    private ArrayList<Project> project;

	public Coordinator(String name, String email) {
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

    public Project getProject(int projectID) {
        return this.project.get(projectID);
    }
}
