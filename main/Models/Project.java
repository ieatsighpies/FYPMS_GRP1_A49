package main.Models;
/**
 * Provide methods to authenticate different user types
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class Project {
    private String projectID;
    private String supervisorName;
    private String supervisorEmail;
    private String supervisorID;
    private String title;
    private projectStatus_ENUM status;
    private String studentName;
    private String studentEmail;
    private String studentID;


    /**
     * Constructor of Project class
     *
     * @param projectID ID of project
     * @param supervisorEmail the email of the project's supervisor
     * @param supervisorName the name of the project's supervisor
     * @param title the title of the project
     * @param status the status of the project
     * @param studentEmail the email of the project's student
     * @param studentName the name of the project's student
     */
    public Project(String projectID, String supervisorName,
                    String supervisorEmail, String title,
                    projectStatus_ENUM status, String studentName,
                    String studentEmail){
        this.projectID = projectID;
        this.supervisorEmail = supervisorEmail;
        this.supervisorID = supervisorEmail.split("@")[0];
        this.supervisorName = supervisorName;
        this.title = title;
        this.status = status;
        this.studentEmail = studentEmail;
        this.studentID = studentEmail.split("@")[0];
        this.studentName = studentName;
    }

    /**
     * Method to get the ID of project
     * @return the projectID
     */
    public String getID(){return this.projectID;}
    /**
     * Method to get the title of project
     * @return the project's title
     */
    public String getTitle(){return this.title;}
    /**
     * Method to get the ID of project's student
     * @return the ID of project's student
     */
    public String getStudentID(){return this.studentID;}
    /**
     * Method to get the email of project's student
     * @return the email of the project's student
     */
    public String getStudentEmail(){return this.studentEmail;}
    /**
     * Method to get the naem of projec's student
     * @return the name of the project's student
     */
    public String getStudentName(){return this.studentName;}
    /**
     * Method to get the ID of project's supervisor
     * @return the ID of the project's supervisor
     */
    public String getSupervisorID(){return this.supervisorID;}
    /**
     * Method to get the name of project's supervisor
     * @return the name of the project's supervisor
     */
    public String getSupervisorName(){return this.supervisorName;}
    /**
     * Method to get the email of project's supervisor
     * @return the email the project's supervisor
     */
    public String getSupervisorEmail(){return this.supervisorEmail;}
    /**
     * Method to get the status of project
     * @return the project's status
     */
    public projectStatus_ENUM getStatus(){return this.status;}


    /**
     * Method to assign the project to a student
     * @param s student that we want to assign project to
     */

    public void setStudent(Student s){this.studentID= s.userID;}
    /**
     * Method to assign the project to a supervisor
     * @param f supervisor we want to assign project to
     */
    public void setSupervisor(Supervisor f){this.supervisorID= f.userID;}

}