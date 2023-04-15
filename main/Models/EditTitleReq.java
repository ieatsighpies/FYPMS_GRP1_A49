package main.Models;

import main.Utils.FileHandler;

/**
 * EditTitleReq is a subclass of Request that deals with edit title request
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class EditTitleReq extends Request{

    private String newTitle;

    
    /**
     * Constructor for EditTitleReq
     * 
     * @param requestID ID of this request
     * @param requesterID ID of the user sending this request
     * @param requesteeID ID of the user receiving this request
     * @param requestType Type of this request
     * @param status status of this request
     * @param projectID project ID associated with this request
     * @param comment comments
     * @param newTitle new title to be changed to
     */
    public EditTitleReq(String requestID, String requesterID, String requesteeID,
                            String requestType, requestStatus_ENUM status, String projectID, String comment, String newTitle){
        super(requestID, requesterID, requesteeID, requestType, status, projectID, comment);
        this.newTitle = newTitle;
    }
    /**
     * Method to get the new title of request
     *
     * @return the new title
     */
    public String getTitle() {
        return newTitle;
    }

    /**
     * Method to set title of a project
     *
     * @param newTitle new title to change to
     */
    public void setTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    /**
     * Method to print the information of the request
     *
     * @param currentTitle userID of student
     * @param null1 dummy variable
     * @param null2 dummy variable
     */
    @Override
    public void printInfo(String currentTitle, String null1, String null2) {
        System.out.println("╔══════════════════════════════════════╦═════════════════════╗");
        System.out.printf("║ Request ID: \u001B[33m%-25s\u001B[0m║Status: \u001B[33m%-13s\u001B[0m║ \n", this.getRequestID(), this.getRequestStatus().toString());
        System.out.println("╠══════════════════════════════════════╩═════════════════════╣");
        System.out.printf("║ Request from: \u001B[33m%-45s\u001B[0m║\n", this.getRequesterID());
        System.out.printf("║ Request to: \u001B[33m%-47s\u001B[0m║\n", this.getRequesteeID());
        System.out.println("╠════════════════════════════════════════════════════════════╩═══════════════════════════════════╗"); 
        System.out.printf("║ Current Title: \u001B[33m%-80s\u001B[0m║\n", currentTitle);
        System.out.printf("║ New Title: \u001B[33m%-84s\u001B[0m║\n", this.getTitle());
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Method to process request
     *
     * @param decision whether the request is approved/rejected
     */
    @Override
    public void processRequest(String decision) {
        // changing request records
        String newStatus = decision.equals("APPROVE")
                            ? requestStatus_ENUM.APPROVED.toString()
                            : requestStatus_ENUM.REJECTED.toString();
        String rfilepath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        String[] data = FileHandler.readFile(rfilepath, this.getRequestID(), 0);
        String newString = data[0]+","+data[1]+","+data[2]+","+data[3]+","+newStatus+","+data[5]+","+data[6]+","+data[7]+","+data[8];
        FileHandler.writeFile(rfilepath, this.getRequestID(), 0, newString);

        // changing project records
        if(decision.equals("APPROVE")){
            String pfilepath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
            String[] data1 = FileHandler.readFile(pfilepath, this.getProjectID(), 0);
            String newString1 = data1[0]+","+data1[1]+","+data1[2]+","+this.newTitle+","+data1[4]+","+data1[5]+","+data1[6];
            FileHandler.writeFile(pfilepath, this.getProjectID(), 0, newString1);
        }
        
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║          -Request processed-          ║");
        System.out.println("╚═══════════════════════════════════════╝");

    }

    
}