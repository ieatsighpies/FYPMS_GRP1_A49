package main.Utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
* Provide methods to hash password and verify password
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class Encryptor {
    /**
     * user input password
     */
    private String password;

    /**
     * Base constructor for Encryptor class
     * 
     * @param password user input password
     */
    public Encryptor(String password){
        this.password = password;
    }

    /**
     * Method to generate salt for hashing
     * @return salt for hasing as byte array
     */
    public String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String s = Base64.getEncoder().encodeToString(salt);
        return s;
    }

    
    /**
     *  Method for hashing password
     * 
     * @param salt salt for hashing
     * @return hashed password as byte array
     */
    public byte[] hash(byte[] salt)   
    {  
        PBEKeySpec spec = new PBEKeySpec(this.password.toCharArray(), salt, 65536, 256);  
        try   
        {  
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  
            return skf.generateSecret(spec).getEncoded();  
        }   
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)   
        {  
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);  
        }   
        finally   
        {  
            spec.clearPassword();  
        }  
    }  

    /**
     * Method to convert byte array to String
     * 
     * @param salt salt for hashing
     * @return hashed password as String
     */
    public String generateHashedPassword(byte[] salt)   
    {  
        String finalpass = null;  
  
        byte[] securePassword = hash(salt);  
   
        finalpass = Base64.getEncoder().encodeToString(securePassword);  
   
        return finalpass;  
    }

    /**
     * Method to verify user input password with actual password
     * 
     * @param providedPassword password user inputted
     * @param securedPassword actual password as hash
     * @param salt salt for hashing
     * @return True is password matches; False if does not match
     */
    public boolean verifyUserPassword(String providedPassword,  String securedPassword, String salt)  
    {  
        boolean verify = false;  
        byte[] byteSalt = this.convertSalt(salt);
          
        /* Generate New secure password with the same salt */  
        String newSecurePassword = generateHashedPassword(byteSalt);  
          
        /* Check if two passwords are equal */  
        verify = newSecurePassword.equalsIgnoreCase(securedPassword);  
          
        return verify;  
    }  

    /* Convert  */
    /**
     * Convert salt from type String to Byte array
     * 
     * @param salt salt for hashing as String
     * @return salt as Byte array
     */
    public byte[] convertSalt(String salt){
        byte[] byteSalt = Base64.getDecoder().decode(salt);
        return byteSalt;
    }
}
