package main;

public class User {
    String name;
    String email;
    String userID;
    String password;
    int type;

    public User(String name, String email, String password, int type){
        this.name = name;
        this.email = email;
        String[] userid = email.split("@");
        this.userID = userid[0];
        this.password = password;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUserID(){
        return this.userID;
    }
}
