package main.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Coordinator extends Supervisor{

    public Coordinator(String name, String email){
        super(name, email);
        this.initialiseProject();
        this.initialiseRequest();
    }

    @Override
    public Project getProjectbyID(String projectID) {
        return super.getProjectbyID(projectID);
    }

    @Override
    public ArrayList<Project> getProjects() {
        return super.getProjects();
    }

    @Override
    public ArrayList<Request> getRequests() {
        return super.getRequests();
    }

    @Override
    public void initialiseProject() {
        String filePath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        String currentLine;
        String data[];
        int col = 2;

        /* read file line by line, initialise all project */
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split("\\s*,\\s*");
                if(!data[0].equals("ProjectID")){
                    Project project = new Project(data[0], data[1], data[2], data[3], projectStatus_ENUM.valueOf(data[4]), data[5], data[6]);
                    this.getProjects().add(project);
                    if(data[col].equalsIgnoreCase(this.getEmail())&& data[4].equals(projectStatus_ENUM.ALLOCATED.toString())){
                        this.countSupervising++;
                    }
                }
                
            }
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void initialiseRequest() {
        String filePath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        String currentLine;
        String data[];

        // read file line by line, initialise request if match email
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split("\\s*,\\s*");

                if(data[3].equals("1")){
                    Request request = new RegisterProjectReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6]);
                    this.getRequests().add(request);
                }
                // if(data[3].equals("2")){
                //     Request request = new DeregisterReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6]);
                //     this.getRequests().add(request);
                // }                
                if(data[3].equals("3")){
                    Request request = new EditTitleReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6],data[8]);
                    this.getRequests().add(request);
                }
                if(data[3].equals("4")){
                    Request request = new TransferStudentReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6],data[7]);
                    this.getRequests().add(request);
                }
                
            }
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void printProjects() {
        super.printProjects();
    }

    public void printProjects(int type){
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                             ██████╗ ██████╗  ██████╗      ██╗███████╗ ██████╗████████╗███████╗                                             ║");
        System.out.println("║                                             ██╔══██╗██╔══██╗██╔═══██╗     ██║██╔════╝██╔════╝╚══██╔══╝██╔════╝                                             ║");
        System.out.println("║                                             ██████╔╝██████╔╝██║   ██║     ██║█████╗  ██║        ██║   ███████╗                                             ║");
        System.out.println("║                                             ██╔═══╝ ██╔══██╗██║   ██║██   ██║██╔══╝  ██║        ██║   ╚════██║                                             ║");
        System.out.println("║                                             ██║     ██║  ██║╚██████╔╝╚█████╔╝███████╗╚██████╗   ██║   ███████║                                             ║");
        System.out.println("║                                             ╚═╝     ╚═╝  ╚═╝ ╚═════╝  ╚════╝ ╚══════╝ ╚═════╝   ╚═╝   ╚══════╝                                             ║");
        System.out.println("╠══════════════════╦════════════════════════════════════════════════════════════════════════════════════╦════════════════╦═════════════════════════╦═════════╣");
        System.out.println("║ID                ║Project Title                                                                       ║Student Name    ║Student Email            ║Status   ║");

        if(type == 1){
            for(Project p : this.getProjects()){
                System.out.println("╠══════════════════╬════════════════════════════════════════════════════════════════════════════════════╬════════════════╬═════════════════════════╬═════════╣");
                System.out.printf("║%-18.18s║%-80.80s\t║%-16.16s║%-25.25s║%-9.9s║\n", p.getID(), p.getTitle(), p.getStudentName(), p.getStudentEmail(), p.getStatus());
            }
        }
        else if(type == 2){
            for(Project p : this.getProjects()){
                if(p.getSupervisorID().equalsIgnoreCase(this.getUserID())){
                    System.out.println("╠══════════════════╬════════════════════════════════════════════════════════════════════════════════════╬════════════════╬═════════════════════════╬═════════╣");
                    System.out.printf("║%-18.18s║%-80.80s\t║%-16.16s║%-25.25s║%-9.9s║\n", p.getID(), p.getTitle(), p.getStudentName(), p.getStudentEmail(), p.getStatus());
                }
                
            }
        }
        
        System.out.println("╚══════════════════╩════════════════════════════════════════════════════════════════════════════════════╩════════════════╩═════════════════════════╩═════════╝");
    }

    @Override
    public void updateProject() {
        super.updateProject();
    }

    @Override
    public void updateRequest() {
        super.updateRequest();
    }
    
    public String getName() {
        return super.name;
    }

    public String getEmail() {
        return super.email;
    }

    public String getUserID() {
        return super.userID;
    }
}
