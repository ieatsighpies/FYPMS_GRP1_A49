package main.Utils;

import java.util.Comparator;

import main.Models.Project;

public class ProjSortBy {
    public static Comparator<Project> ID = new Comparator<Project>() {
        
        @Override
        public int compare(Project p1, Project p2){
            Long id1 = Long.parseLong(p1.getID());
            Long id2 = Long.parseLong(p2.getID());

            return (id1-id2)>0?1:-1;
        }
    };

    public static Comparator<Project> StudentName = new Comparator<Project>() {
        
        @Override
        public int compare(Project p1, Project p2){
            String name1 = p1.getStudentName();
            String name2 = p2.getStudentName();

            return name1.compareTo(name2);
        }
    };

    public static Comparator<Project> Status = new Comparator<Project>() {
        
        @Override
        public int compare(Project p1, Project p2){
            String status1 = p1.getStatus().toString();
            String status2 = p2.getStatus().toString();

            return status1.compareTo(status2);
        }
    };
}
