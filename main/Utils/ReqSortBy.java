package main.Utils;

import java.util.Comparator;

import main.Models.Request;

/**
* Provide methods to sort request arraylist
*  
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/
public class ReqSortBy {
    /**
     * Comparator to sort by request ID
     */
    public static Comparator<Request> ID = new Comparator<Request>() {

        @Override
        public int compare(Request r1, Request r2) {
            Long id1 = Long.parseLong(r1.getProjectID());
            Long id2 = Long.parseLong(r2.getProjectID());

            return (id1-id2)>0?1:-1;
        }
        
    };

    /**
     * Comparator to sort by request Type
     */
    public static Comparator<Request> Type = new Comparator<Request>() {

        @Override
        public int compare(Request r1, Request r2) {
            String type1 = r1.getRequestType();
            String type2 = r2.getRequestType();

            return type1.compareTo(type2);
        }
        
    };

    /**
     * Comparator to sort by request status
     */
    public static Comparator<Request> Status = new Comparator<Request>() {

        @Override
        public int compare(Request r1, Request r2) {
            String status1 = r1.getRequestStatus().toString();
            String status2 = r2.getRequestStatus().toString();

            return status1.compareTo(status2);
        }
        
    };
}
