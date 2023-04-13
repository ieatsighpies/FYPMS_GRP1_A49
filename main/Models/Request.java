package main.Models;
/**
*  The Request base class. Different request types will inherit this class
*
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-04-13
*/
public abstract class Request {
    /**
     * The ID of the request
     */
    private String requestID;
    /**
     * The ID of the requester
     */
    private String requesterID;
    /**
     * The ID of the requestee: either Coordinator or Supervisor
     */
    private String requesteeID;
    /**
     * The ID of the project
     */
    private String projectID;
    /**
     * The type of request
     */
    private String requestType;
    /**
     * The request status
     */
    private requestStatus_ENUM requestStatus;
    /**
     * Comments made by requester/requestee
     */
    private String comments;
    /**
     * The constructor of Request
     * @param requestID The ID of the request
     * @param requesterID The ID of the requester
     * @param requesteeID The ID of the requestee: either Coordinator or Supervisor
     * @param requestType The type of request
     * @param status The request status
     * @param projectID The ID of the project
     * @param comment Comments made by requester/requestee
     */
    public Request(String requestID, String requesterID, String requesteeID,
                    String requestType, requestStatus_ENUM status, String projectID, String comment){
        this.requestID = requestID;
        this.requesterID = requesterID;
        this.requesteeID = requesteeID;
        this.requestType = requestType;
        this.requestStatus = status;
        this.projectID = projectID;
        this.comments = comment;
    }
    /**
     * Gets the status of request
     * @return the request status
     */
    public requestStatus_ENUM getRequestStatus() {
        return requestStatus;
    }
    /**
     * Gets the requestID
     * @return ID of request
     */
    public String getRequestID() {
        return requestID;
    }
    /**
     * Sets the requestID
     * @param requestID ID of request
     */
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
    /**
     * Gets the requester's ID
     * @return requester's ID
     */
    public String getRequesterID() {
        return requesterID;
    }
    /**
     * Sets the request's requester's ID
     * @param requesterID ID of requester
     */
    public void setRequesterID(String requesterID) {
        this.requesterID = requesterID;
    }
    /**
     * Gets the projectID
     * @return ID of project
     */
    public String getProjectID() {
        return projectID;
    }
    /**
     * Sets the projectID of the request
     * @param projectID ID of project
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
    /**
     * Gets the type of request
     * @return request's type
     */
    public String getRequestType() {
        return requestType;
    }
    /**
     * Sets the type of request
     * @param requestType2 type of request
     */
    public void setRequestType(String requestType2) {
        this.requestType = requestType2;
    }
    /**
     * Sets request status
     * @param requestStatus status of request
     */
    public void setRequestStatus(requestStatus_ENUM requestStatus) {
        this.requestStatus = requestStatus;
    }
    /**
     * Gets comments in request
     * @return comments
     */
    public String getComments() {
        return comments;
    }
    /**
     * Sets comments in request
     * @param comments comments made by requester/requestee
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * Gets ID of requestee
     * @return ID of requestee
     */
    public String getRequesteeID() {
        return requesteeID;
    }
    /**
     * Sets ID of requestee
     * @param requesteeID ID of requestee
     */
    public void setRequesteeID(String requesteeID) {
        this.requesteeID = requesteeID;
    }
    /**
     * Prints the information of the request
     * @param info1 project title
     * @param info2 supervisor's ID
     * @param info3 supervisor's email
     */
    public abstract void printInfo(String info1, String info2, String info3);
    /**
     * Process the request
     * @param decision approve or reject the request
     */
    public abstract void processRequest(String decision);
}
