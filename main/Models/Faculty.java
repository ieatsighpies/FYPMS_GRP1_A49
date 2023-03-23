package main.Models;

import java.util.ArrayList;

public class Faculty extends User {
	private ArrayList<Project> project;

	public Faculty(String name, String email, ArrayList<Project> project) {
        super(name, email);
        this.project = project;
    }
    public Faculty(String name, String email){
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


