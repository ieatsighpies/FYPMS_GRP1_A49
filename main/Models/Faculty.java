package main.Models;

import java.util.ArrayList;

public class Supervisor extends User {
	private ArrayList<Project> project;

	public Supervisor(String name, String email, ArrayList<Project> project) {
        super(name, email);
        this.project = project;
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


