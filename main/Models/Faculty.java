package main.Models;

import java.util.ArrayList;

public class Faculty extends User {
	private ArrayList<Project> project;
	private boolean isCoordinator;
	private int type;

	public Faculty(String name, String email, boolean isCoordinator, ArrayList<Project> project) {
        super(name, email);
        this.isCoordinator = isCoordinator;
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


