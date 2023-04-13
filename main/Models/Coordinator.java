package main.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * Coordinator is a subclass of Supervisor
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 2023-4-13
 */
public class Coordinator extends Supervisor{
    /**
     * Constructing the Coordinator class
     *
     * @param name type of user
     * @param email email of user
     */
    public Coordinator(String name, String email){

        super(name, email);
        this.type = 3;
        this.updateProject();
        this.updateRequest();
    }
    /**
     * Method to retrieve projects of an user
     *
     * @param type mode of retrieve
     * @return an array list of said user
     */
    public ArrayList<Project> getProjects(int type){
        if(type == 1){
            return super.getProjects();
        }
        else if(type == 2){
            ArrayList<Project> myProjects = new ArrayList<Project>();
            System.out.println(myProjects);
            for(Project p : super.getProjects()){
                if(p.getSupervisorID().equalsIgnoreCase(this.getUserID())){
                    myProjects.add(p);
                }
            }
            return myProjects;
        }
        return null;
    }
    /**
     * Method to initialise the project list of an user
     *
     */
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
                if(currentLine.trim().length()>0){
                    data = currentLine.split("\\s*,\\s*");
                    if(!data[0].equals("ProjectID")){
                        Project project = new Project(data[0], data[1], data[2], data[3], projectStatus_ENUM.valueOf(data[4]), data[5], data[6]);
                        this.getProjects().add(project);
                        if(data[col].equalsIgnoreCase(this.getEmail())&& data[4].equals(projectStatus_ENUM.ALLOCATED.toString())){
                            this.countSupervising++;
                        }
                    }
                }
                

            }
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
    /**
     * Method to initialize the request list of an user
     *
     */
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
                if(currentLine.trim().length()>0){
                    data = currentLine.split("\\s*,\\s*");

                    if(data[3].equals("1")){
                        Request request = new RegisterProjectReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6]);
                        this.getRequests().add(request);
                    }
                    if(data[3].equals("2")){
                        Request request = new DeregisterProjectReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6]);
                        this.getRequests().add(request);
                    }
                    if(data[3].equals("3")){
                        Request request = new EditTitleReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6],data[8]);
                        this.getRequests().add(request);
                    }
                    if(data[3].equals("4")){
                        Request request = new TransferStudentReq(data[0],data[1],data[2],data[3],requestStatus_ENUM.valueOf(data[4]),data[5],data[6],data[7]);
                        this.getRequests().add(request);
                    }
                }
                

            }
            br.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
    /**
     * Method to print the projects of an user
     *
     * @param type mode of retrieve
     */
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
}
