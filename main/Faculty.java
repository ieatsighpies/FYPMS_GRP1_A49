public class Faculty extends User {
	private project[] project;
	private isCoordinator boolean;
	private name String;
	private email String;
	private userID String;
	private pswd String;
	private type int;
	
	public Faculty(String name, String email, String userID, Project project) {
        this.name = name;
        this.email = email;
        this.userID = userID;
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserID() {
        return userID;
    }

    public Project getProject(int projectID) {
        return project[projectID];
    }
}

}
