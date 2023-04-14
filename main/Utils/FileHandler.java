package main.Utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
* Provide methods to read and write file
* The class does not have any public constructors and should be used as a static utility class.
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class FileHandler {

    /* read file and return target line */
    /**
     * Method to read file
     * 
     * @param filePath file to read
     * @param check input to match against
     * @param col column to check for "check" 
     * @return Returns the line as String[] seperated by comma if match found, else return null
     */
    public static String[] readFile(String filePath, String check, int col){
        String currentLine;
        String data[] = {};

        /* read file line by line, break when value found against checker */
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                if(currentLine.trim().length() > 0){
                    data = currentLine.split("\\s*,\\s*");
                    if(data[col].equalsIgnoreCase(check)){
                        return data;
                    }
                }
                
            }
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    /* find target string in file by specified column, return boolean */
    /**
     * Method to find if target exist in file
     * 
     * @param filePath file to check
     * @param check target to match for
     * @param col column in csv to find target
     * @return True if found, else return False
     */
    public static boolean findFile(String filePath, String check, int col){
        String currentLine;
        String data[] = {};

        /* read file line by line, break when value found against checker */
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                if(currentLine.trim().length()>0){
                    data = currentLine.split("\\s*,\\s*");
                    if(data[col].equalsIgnoreCase(check)){
                        return true;
                    }
                }
                
            }
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    /* write given line to target line. 
     * target line is found by matching target string with check
     * col is the column index that u want to check against
     * newString is the new string you want to replace target string with
     */
    /**
     * Method to write input string to target line
     * 
     * @param filepath file to write to
     * @param check target to match against
     * @param col column in csv to find target
     * @param newString new string to be written over target line
     */
    public static void writeFile(String filepath, String check, int col, String newString){
        String currentLine;
        String data[];

        /* write to specific line in file base on check */
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            StringBuffer inputBuffer = new StringBuffer();

            while((currentLine = br.readLine()) != null){
                if(currentLine.trim().length()>0){
                    data = currentLine.split(",");
                    if(data[col].equalsIgnoreCase(check)){
                        currentLine = newString;
                    }
                    inputBuffer.append(currentLine);
                    inputBuffer.append("\n");
                }
                
            }
            br.close();

            FileOutputStream fileout = new FileOutputStream(filepath);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
