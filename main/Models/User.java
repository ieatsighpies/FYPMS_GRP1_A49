package main.Models;

enum type {STUDENT, FACULTY, COORDINATOR}

public class User {
    String name;
    String email;
    String userID;
    type type;

    public User(String name, String email){
        this.name = name;
        this.email = email;
        this.userID = email.split("@")[0];
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getUserID(){
        return this.userID;
    }
}
