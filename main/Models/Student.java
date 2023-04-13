package main.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Student extends User implements IinitialiseRequest, IinitialiseProject{
    
    private Project project; //student HAS-A project
    private boolean deregistered; //to differentiate from students without a project
    private ArrayList<Request> requestList = new ArrayList<Request>(); // all request of current student

    public Student(String name, String email, Boolean dereg){
        super(name, email);
        this.project = null;
        this.deregistered = dereg;
        this.type = 1;
        initialiseProject();
        initialiseRequest();
    }

    public boolean getDeregisteredStatus(){
        return deregistered;
    }

    public Project getProject(){
        if(this.project == null){
            return null;
        }
        return this.project;
    }

    public ArrayList<Request> getRequests(){
        return this.requestList;
    }

    public Request getRequestbyID(String requestID) {
        for(Request r : this.requestList){
            if(r.getRequestID().equals(requestID)){
                return r;
            }
        }
        return null;
    }

    public void initialiseProject(){
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        String currentLine;
        String data[];
        int col = 6;
        boolean found = false;

        // read file line by line, initialise project if match email and status = ALLOCATED
        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                if(currentLine.trim().length()>0){
                    data = currentLine.split("\\s*,\\s*");
                    if(data[col].equalsIgnoreCase(this.getEmail()) && data[4].equals(projectStatus_ENUM.ALLOCATED.toString())){
                        this.project = new Project(data[0], data[1], data[2], data[3], projectStatus_ENUM.valueOf(data[4]), data[5], data[6]);
                        found = true;
                        return;
                    }
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

    public void initialiseRequest(){
        String filePath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        String currentLine;
        String data[];

        // read file line by line, initialise request if match email
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                if(currentLine.trim().length()>0){
                    data = currentLine.split("\\s*,\\s*");
                    if(data[1].equalsIgnoreCase(this.getUserID())){
                        if(data[3].equals("1")){
                            Request request = new RegisterProjectReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6]);
                            requestList.add(request);
                        }
                        if(data[3].equals("2")){
                            Request request = new DeregisterProjectReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6]);
                            requestList.add(request);
                            if(data[4].equals(requestStatus_ENUM.APPROVED.toString())){
                                this.deregistered = true;
                            }
                        }
                        if(data[3].equals("3")){
                            Request request = new EditTitleReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6],data[8]);
                            requestList.add(request);
                        }
                    }
                }
                
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void updateRequest(){
        this.requestList = new ArrayList<Request>();
        this.initialiseRequest();
    }

    public void printProject(){
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                            ███╗   ███╗██╗   ██╗         ██████╗ ██████╗  ██████╗      ██╗███████╗ ██████╗████████╗███████╗                                 ║");
        System.out.println("║                            ████╗ ████║╚██╗ ██╔╝         ██╔══██╗██╔══██╗██╔═══██╗     ██║██╔════╝██╔════╝╚══██╔══╝██╔════╝                                 ║");
        System.out.println("║                            ██╔████╔██║ ╚████╔╝          ██████╔╝██████╔╝██║   ██║     ██║█████╗  ██║        ██║   ███████╗                                 ║");
        System.out.println("║                            ██║╚██╔╝██║  ╚██╔╝           ██╔═══╝ ██╔══██╗██║   ██║██   ██║██╔══╝  ██║        ██║   ╚════██║                                 ║");
        System.out.println("║                            ██║ ╚═╝ ██║   ██║            ██║     ██║  ██║╚██████╔╝╚█████╔╝███████╗╚██████╗   ██║   ███████║                                 ║");
        System.out.println("║                            ╚═╝     ╚═╝   ╚═╝            ╚═╝     ╚═╝  ╚═╝ ╚═════╝  ╚════╝ ╚══════╝ ╚═════╝   ╚═╝   ╚══════╝                                 ║");
        System.out.println("╠══════════════════╦════════════════════════════════════════════════════════════════════════════════════╦════════════════╦═════════════════════════╦═════════╣");
        System.out.println("║ID                ║Project Title                                                                       ║Student Name    ║Student Email            ║Status   ║");


        System.out.println("╠══════════════════╬════════════════════════════════════════════════════════════════════════════════════╬════════════════╬═════════════════════════╬═════════╣");
        System.out.printf("║%-18.18s║%-80.80s\t║%-16.16s║%-25.25s║%-9.9s║\n", this.project.getID(), this.project.getTitle(), this.project.getStudentName(), this.project.getStudentEmail(), this.project.getStatus());

        System.out.println("╚══════════════════╩════════════════════════════════════════════════════════════════════════════════════╩════════════════╩═════════════════════════╩═════════╝");
    }



}