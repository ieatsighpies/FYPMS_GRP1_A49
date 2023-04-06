package main.Models;


public class DeregisterReq extends Request {
    public DeregisterReq(String requestID, String requesterID, String requesteeID, String requestType,
                         requestStatus_ENUM status, String projectID, String comment) {
        super(requestID, requesterID, requesteeID, requestType, status, projectID, comment);
    }

    @Override
    public void printInfo(String info) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printInfo'");
    }

    @Override
    public void proccessRequest(String decision) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'proccessRequest'");
    }
}
