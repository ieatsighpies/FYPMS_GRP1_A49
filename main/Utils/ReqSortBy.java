package main.Utils;

import java.util.Comparator;

import main.Models.Request;

public class ReqSortBy {
    public static Comparator<Request> ID = new Comparator<Request>() {

        @Override
        public int compare(Request r1, Request r2) {
            Long id1 = Long.parseLong(r1.getProjectID());
            Long id2 = Long.parseLong(r2.getProjectID());

            return (id1-id2)>0?1:-1;
        }
        
    };

    public static Comparator<Request> Type = new Comparator<Request>() {

        @Override
        public int compare(Request r1, Request r2) {
            String type1 = r1.getRequestType();
            String type2 = r2.getRequestType();

            return type1.compareTo(type2);
        }
        
    };

    public static Comparator<Request> Status = new Comparator<Request>() {

        @Override
        public int compare(Request r1, Request r2) {
            String status1 = r1.getRequestStatus().toString();
            String status2 = r2.getRequestStatus().toString();

            return status1.compareTo(status2);
        }
        
    };
}
