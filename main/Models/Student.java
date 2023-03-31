package main.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Student extends User {
    //student HAS-A project
    private Project project;
    //to differentiate from students without a project
    private boolean deregistered;
    private ArrayList<Request> requestList = new ArrayList<Request>();

    public Student(String name, String email){
        super(name, email);
        this.project = null;
        this.deregistered = false;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return email;
    }

    public String getUserID(){
        return userID;
    }

    public boolean getDeregisteredStatus(){
        return deregistered;
    }

    public Project getProject(){
        if(this.project.getStatus()==projectStatus_ENUM.ALLOCATED)
            return this.project;
        else if(this.project==null && this.deregistered==false){
            System.out.println("You have not registered for a project");
            return this.project;
        }
        return null;
    }

    public void initialiseProject(){
        String filepath = System.getProperty("usser.dir") + "\\main\\Data\\project_record.csv";
        String currentLine;
        String data[];
        int col = 6;
        boolean found = false;

        // read file line by line, initialise project if match email and status = ALLOCATED
        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split("\\s*,\\s*");
                if(data[col].equals(this.getEmail()) && data[4].equals(projectStatus_ENUM.ALLOCATED.toString())){
                    this.project = new Project(data[0], data[1], data[2], data[3], projectStatus_ENUM.valueOf(data[4]), data[5], data[6]);
                    found = true;
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

        if(found == false){
            this.project = null;
        }
    }

    public void updateProject(){
        this.initialiseProject();
    }



}