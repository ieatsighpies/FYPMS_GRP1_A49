package main.Models;
/**
* The user base class. Different user types will inherit this class
*
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/

public class User {
    /**
     * Name of user
     */
    protected String name;
    /**
     * Email of user
     */
    protected String email;
    /**
     * ID of user
     */
    protected String userID;
    /**
     * type of user
     */
    protected int type;


    /**
     * Constructor of User
     * @param name user's name
     * @param email user's email
     */
    public User(String name, String email){
        this.name = name;
        this.email = email;
        this.userID = email.split("@")[0];
    }
    /**
     * Gets user's name
     * @return user's name
     */
    public String getName(){
        return this.name;
    }
    /**
     * Gets user's email
     * @return user's email
     */
    public String getEmail(){
        return this.email;
    }
    /**
     * Gets user's ID
     * @return user's ID
     */
    public String getUserID(){
        return this.userID;
    }
    /**
     * Gets type of user
     * @return type of user
     */
    public int getType() {
        return this.type;
    }
}
