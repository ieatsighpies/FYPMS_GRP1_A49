package main.Models;

public class User {
    protected String name;
    protected String email;
    protected String userID;
    protected int type;

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
