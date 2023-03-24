package main.Models;

import java.util.ArrayList;

public class Supervisor extends User {
	private ArrayList<Project> projects = new ArrayList<Project>();
    protected int countSupervising=0;

	public Supervisor(String name, String email, ArrayList<Project> projects) {
        super(name, email);
        this.projects = projects;
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
        return this.projects.get(projectID-1);
    }

}


