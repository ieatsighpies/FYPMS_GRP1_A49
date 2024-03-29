package main.Utils;

/**
* Provide method to for console related methods
* The class does not have any public constructors and should be used as a static utility class.
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class ConsoleUtils {
    /**
     *  Clears the console
     */
    public static void clearScreen(){  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 
}
