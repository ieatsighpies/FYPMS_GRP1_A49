package main.Utils;

/**
* Provide methods to authenticate different user types
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class Authenticator {

    /**
     * Method to authenticate student
     * 
     * @param username userID of student
     * @param password password of student
     * @return whether the student passed authentication
     */
    public static boolean authenticateStudent(String username, String password){
        
        /* filepath */
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\student_list.csv";
        
        /* Get target line from csv */
        String data[] = FileHandler.readFile(filepath, username, 2);
        if(data == null){
            return false;
        }

        /* Regenerate encrypted password */                
        Encryptor encryptor = new Encryptor(password);
        byte[] byteSalt = encryptor.convertSalt(data[4]);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);

        /* accept if using default password */
        if(password.equals("password") && data[3].equals("password")){
            return true;
        }
        /* else verify against encrypted password */
        else if(data[3].equals(encryptedPass)){
            return true;
        }
        /* no match return false */
        else{
            return false;
        }
  
    }

    /**
     * Method to authenticate staff
     * 
     * @param username userID of staff
     * @param password password of staff
     * @return whether the staff passed authentication
     */
    public static boolean authenticateStaff(String username, String password){
        
        /* filepath */
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
        
        /* Get target line from csv */
        String data[] = FileHandler.readFile(filepath, username, 2);
        if(data == null){
            return false;
        }

        /* Regenerate encrypted password */                
        Encryptor encryptor = new Encryptor(password);
        byte[] byteSalt = encryptor.convertSalt(data[4]);
        String encryptedPass = encryptor.generateHashedPassword(byteSalt);

        /* accept if using default password */
        if(password.equals("password")){
            return true;
        }
        /* else verify against encrypted password */
        else if(data[3].equals(encryptedPass)){
            return true;
        }
        /* no match return false */
        else{
            return false;
        }
    }

}
