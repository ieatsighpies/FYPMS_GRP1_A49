package main.Models;

public class EditTitleReq extends Request{

    private String newTitle;

    public EditTitleReq(String requestID, String requesterID, String requesteeID,
                            String requestType, requestStatus_ENUM status, String projectID, String comment, String newTitle){
        super(requestID, requesterID, requesteeID, requestType, status, projectID, comment);
        this.newTitle = newTitle;
    }

    public String getTitle() {
        return newTitle;
    }

    public void setTitle(String newTitle) {
        this.newTitle = newTitle;
    }
}