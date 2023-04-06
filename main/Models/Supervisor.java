package main.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Supervisor extends User {
	private ArrayList<Project> projects = new ArrayList<Project>();
    private ArrayList<Request> requestList = new ArrayList<Request>();

    protected int countSupervising=0;

	public Supervisor(String name, String email) {
        super(name, email);
        this.type = 2;
        initialiseProject();
        initialiseRequest();
    }

    public int getSupCount(){
        return this.countSupervising;
    }

    public Project getProjectbyID(String projectID) {
        for(Project p : this.projects){
            if(p.getID().equals(projectID)){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Project> getProjects(){
        return this.projects;
    }

    public void initialiseProject(){
        String filePath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        String currentLine;
        String data[];
        int col = 2;

        /* read file line by line, initialise project if match email */
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split("\\s*,\\s*");
                if(data[col].equalsIgnoreCase(this.getEmail())){
                    Project project = new Project(data[0], data[1], data[2], data[3], projectStatus_ENUM.valueOf(data[4]), data[5], data[6]);
                    projects.add(project);
                    if(data[4].equals(projectStatus_ENUM.ALLOCATED.toString())){
                        this.countSupervising++;
                    }
                }
            }
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void updateProject(){
        this.projects = new ArrayList<Project>();
        this.countSupervising = 0;
        this.initialiseProject();
    }

    public void initialiseRequest(){
        String filePath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        String currentLine;
        String data[];
        int col = 2;

        // read file line by line, initialise request if match email
        try{
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null){
                data = currentLine.split("\\s*,\\s*");
                if(data[1].equalsIgnoreCase(this.getUserID()) || data[2].equalsIgnoreCase(this.getUserID())){
                    if(data[3].equals("3")){
                        Request request = new EditTitleReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6],data[8]);
                        requestList.add(request);
                    }
                    if(data[3].equals("4")){
                        Request request = new TransferStudentReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6],data[7]);
                        requestList.add(request);
                    }
                }
            }
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void updateRequest(){
        this.requestList = new ArrayList<Request>();
        this.initialiseRequest();
    }

    public ArrayList<Request> getRequests(){
        return this.requestList;
    }

    public void printProjects(){
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                             ██████╗ ██████╗  ██████╗      ██╗███████╗ ██████╗████████╗███████╗                                             ║");
        System.out.println("║                                             ██╔══██╗██╔══██╗██╔═══██╗     ██║██╔════╝██╔════╝╚══██╔══╝██╔════╝                                             ║");
        System.out.println("║                                             ██████╔╝██████╔╝██║   ██║     ██║█████╗  ██║        ██║   ███████╗                                             ║");
        System.out.println("║                                             ██╔═══╝ ██╔══██╗██║   ██║██   ██║██╔══╝  ██║        ██║   ╚════██║                                             ║");
        System.out.println("║                                             ██║     ██║  ██║╚██████╔╝╚█████╔╝███████╗╚██████╗   ██║   ███████║                                             ║");
        System.out.println("║                                             ╚═╝     ╚═╝  ╚═╝ ╚═════╝  ╚════╝ ╚══════╝ ╚═════╝   ╚═╝   ╚══════╝                                             ║");
        System.out.println("╠══════════════════╦════════════════════════════════════════════════════════════════════════════════════╦════════════════╦═════════════════════════╦═════════╣");
        System.out.println("║ID                ║Project Title                                                                       ║Student Name    ║Student Email            ║Status   ║");

        for(Project p : this.projects){
            System.out.println("╠══════════════════╬════════════════════════════════════════════════════════════════════════════════════╬════════════════╬═════════════════════════╬═════════╣");
            System.out.printf("║%-18.18s║%-80.80s\t║%-16.16s║%-25.25s║%-9.9s║\n", p.getID(), p.getTitle(), p.getStudentName(), p.getStudentEmail(), p.getStatus());
        }
        System.out.println("╚══════════════════╩════════════════════════════════════════════════════════════════════════════════════╩════════════════╩═════════════════════════╩═════════╝");
    }

}


