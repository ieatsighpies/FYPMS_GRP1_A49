package main.Models;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import main.Utils.FileHandler;
public class TransferStudentReq extends Request{

    private String newSupervisorID;

    public TransferStudentReq(String requestID, String requesterID, String requesteeID, String requestType,
            requestStatus_ENUM status, String projectID, String comment, String newSupervisorID) {
        super(requestID, requesterID, requesteeID, requestType, status, projectID, comment);
        this.newSupervisorID = newSupervisorID;
    }

    public String getNewSupervisorID() {
        return newSupervisorID;
    }

    public void setNewSupervisorID(String newSupervisorID) {
        this.newSupervisorID = newSupervisorID;
    }

    @Override
    public void printInfo(String projectTitle, String currentSup, String replacementSup) {
        System.out.println("╔══════════════════════════════════════╦═════════════════════╗");
        System.out.printf("║ Request ID: \u001B[33m%-25s\u001B[0m║Status: \u001B[33m%-13s\u001B[0m║ \n", this.getRequestID(), this.getRequestStatus().toString());
        System.out.println("╠══════════════════════════════════════╩═════════════════════╣");
        System.out.printf("║ Request from: \u001B[33m%-45s\u001B[0m║\n", this.getRequesterID());
        System.out.printf("║ Request to: \u001B[33m%-47s\u001B[0m║\n", "FYP Coordinator");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.printf("║ Current SupervisorID: \u001B[33m%-37s\u001B[0m║\n", currentSup);
        System.out.printf("║ Replacement SupervisorID: \u001B[33m%-33s\u001B[0m║\n", replacementSup);
        System.out.println("╠════════════════════════════════════════════════════════════╩═══════════════════════════════════╗"); 
        System.out.printf("║ Project Title: \u001B[33m%-80s\u001B[0m║\n", projectTitle);
        System.out.printf("║ Project ID: \u001B[33m%-83s\u001B[0m║\n", this.getProjectID());
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    @Override
    public void processRequest(String decision) {
        String newStatus = decision.equals("APPROVE")
                ? requestStatus_ENUM.APPROVED.toString()
                : requestStatus_ENUM.REJECTED.toString();

        // return if rejected
        String rfilepath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        if(decision.equals("REJECT")){
            String newString = this.getRequestID()+","+this.getRequesterID()+","+this.getRequesteeID()+","+this.getRequestType()+","+requestStatus_ENUM.REJECTED.toString()+","+this.getProjectID()+","+this.getComments()+",NaN,NaN";
            FileHandler.writeFile(rfilepath, this.getRequestID(), 0, newString);
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║          -Request processed-          ║");
            System.out.println("╚═══════════════════════════════════════╝");
            return;
        }

        // get student data
        String sfilepath = System.getProperty("user.dir") + "\\main\\Data\\student_list.csv";
        String[] studentdata = FileHandler.readFile(sfilepath, this.getRequesterID(), 2);
        String student_name = studentdata[0];
        String student_email = studentdata[1];

        // get project data
        String pfilepath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        String[] projectdata = FileHandler.readFile(pfilepath, this.getProjectID(), 0);
        String supervisor_email = projectdata[2];

        // get faculty data
        String ffilepath = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
        String[] facultydata = FileHandler.readFile(ffilepath, supervisor_email, 1);
        int supervising_count = Integer.valueOf(facultydata[5]);

        //get new faculty data

        String new_ffilepath = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
        String[] new_facultydata = FileHandler.readFile(new_ffilepath, this.newSupervisorID, 2);
        int new_supervising_count = Integer.valueOf(new_facultydata[5]);

        String new_supervisor_name= new_facultydata[0];
        String new_supervisor_email= new_facultydata[1];



        // if approve
        if(decision.equals("APPROVE")){

            // check if new supervisor supervising 2 projects already
            if(new_supervising_count >= 2){
                System.out.println("╔════════════════════════════════════════════════════════════════════╗");
                System.out.println("║       -Action Failed: Supervisor supervising capacity maxed-       ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════╝");
                return;
            }

            // update request record
            String newString = this.getRequestID()+","+this.getRequesterID()+","+this.getRequesteeID()+","+this.getRequestType()+","+requestStatus_ENUM.APPROVED.toString()+","+this.getProjectID()+","+this.getComments()+",NaN,NaN";
            FileHandler.writeFile(rfilepath, this.getRequestID(), 0, newString);

            // update project record
            String newString2 = projectdata[0]+","+new_supervisor_name+","+new_supervisor_email+","+projectdata[3]+","+projectStatus_ENUM.ALLOCATED.toString()+","+student_name+","+student_email;
            FileHandler.writeFile(pfilepath, this.getProjectID(), 0, newString2);

            // update faculty list
            supervising_count--;
            String newString3 = facultydata[0]+","+facultydata[1]+","+facultydata[2]+","+facultydata[3]+","+Integer.toString(supervising_count);
            FileHandler.writeFile(ffilepath, supervisor_email, 1, newString3);

            // update new faculty
            new_supervising_count++;
            String newString4 = new_facultydata[0]+","+new_facultydata[1]+","+new_facultydata[2]+","+new_facultydata[3]+","+Integer.toString(new_supervising_count);
            FileHandler.writeFile(ffilepath, new_supervisor_email, 1, newString4);

            // change all to unavailable if supervisor 2
            if(new_supervising_count >= 2){
                String currentLine;
                try{
                    BufferedReader br = new BufferedReader(new FileReader(pfilepath));
                    StringBuffer inputBuffer = new StringBuffer();

                    while((currentLine = br.readLine()) != null){
                        String[] lineData;
                        if(currentLine.trim().length() > 0){
                            lineData = currentLine.split(",");
                            if(lineData[2].equalsIgnoreCase(new_supervisor_email) && lineData[4].equalsIgnoreCase(projectStatus_ENUM.AVAILABLE.toString())){
                                currentLine = lineData[0]+","+lineData[1]+","+lineData[2]+","+lineData[3]+","+projectStatus_ENUM.UNAVAILABLE.toString()+","+lineData[5]+","+lineData[6];
                            }
                            inputBuffer.append(currentLine);
                            inputBuffer.append("\n");
                        }
                    }
                    br.close();

                    FileOutputStream fileout = new FileOutputStream(pfilepath);
                    fileout.write(inputBuffer.toString().getBytes());
                    fileout.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            //change all of old supervisor to available
            String currentLine2;
            try{
                BufferedReader br2 = new BufferedReader(new FileReader(pfilepath));
                StringBuffer inputBuffer2 = new StringBuffer();

                while((currentLine2 = br2.readLine()) != null){
                    String[] lineData2;
                    if(currentLine2.trim().length() > 0){
                        lineData2 = currentLine2.split(",");
                        if(lineData2[2].equalsIgnoreCase(new_supervisor_email) && lineData2[4].equalsIgnoreCase(projectStatus_ENUM.UNAVAILABLE.toString())){
                            currentLine2 = lineData2[0]+","+lineData2[1]+","+lineData2[2]+","+lineData2[3]+","+projectStatus_ENUM.AVAILABLE.toString()+","+lineData2[5]+","+lineData2[6];
                        }
                        inputBuffer2.append(currentLine2);
                        inputBuffer2.append("\n");
                    }
                }
                br2.close();

                FileOutputStream fileout2 = new FileOutputStream(pfilepath);
                fileout2.write(inputBuffer2.toString().getBytes());
                fileout2.close();
            } catch(Exception e){
                e.printStackTrace();
            }

        }

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║          -Request processed-          ║");
        System.out.println("╚═══════════════════════════════════════╝");



    }
}
