package main.Utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class Encryptor {
    private String password;

    public Encryptor(String password){
        this.password = password;
    }

    public String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String s = Base64.getEncoder().encodeToString(salt);
        return s;
    }

    
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

    public String generateHashedPassword(byte[] salt)   
    {  
        String finalpass = null;  
  
        byte[] securePassword = hash(salt);  
   
        finalpass = Base64.getEncoder().encodeToString(securePassword);  
   
        return finalpass;  
    }

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
    public byte[] convertSalt(String salt){
        byte[] byteSalt = Base64.getDecoder().decode(salt);
        return byteSalt;
    }
}
