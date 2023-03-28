package main.Models;

public abstract class EditTitleReq extends Request{

    protected String title;
    public EditTitleReq(Student s, String projectID, String newTitle){
        super(s, projectID);
        this.title = newTitle;
        this.requestStatus = requestStatus_ENUM.PENDING;
    }
    public abstract void approve();
}