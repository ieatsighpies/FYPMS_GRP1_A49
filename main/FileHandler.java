package main;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class FileHandler {
    public FileHandler(){
    }

    /* read file and return target line */
    public String[] readFile(String filePath, String check, int col){
        String currentLine;
        String data[] = {};

        /* read file line by line, break when value found against checker */
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split("\\s*,\\s*");
                if(data[col].equalsIgnoreCase(check)){
                    return data;
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    /* write given line to target line. 
     * target line is found by matching target string with check
     * col is the column index that u want to check against
     * newString is the new string you want to replace target string with
     */
    public void writeToFile(String filepath, String check, int col, String newString){
        String currentLine;
        String data[];

        /* write to specific line in file base on check */
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            StringBuffer inputBuffer = new StringBuffer();

            while((currentLine = br.readLine()) != null){
                data = currentLine.split(",");
                if(data[col].equalsIgnoreCase(check)){
                    currentLine = newString;
                }
                inputBuffer.append(currentLine);
                inputBuffer.append("\n");
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
