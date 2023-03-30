package main.Models;

public class EditTitleReq extends Request{

    protected String title;
    public EditTitleReq(Student s, String projectID, String newTitle){
        super(s, projectID);
        this.title = newTitle;
        this.setRequestStatus(requestStatus_ENUM.PENDING);
    }
}