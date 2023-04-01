package main.Pages;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import main.Models.Student;
import main.Models.requestStatus_ENUM;
import main.Utils.ConsoleUtils;
import main.Utils.UIDGenerator;

public class StudentProjectRegister extends Page{
    private Student student;
    private String[] projectData;
    private Scanner sc = new Scanner(System.in);

    public StudentProjectRegister(Page previousPage, Student student, String[] projectData) {
        super(previousPage);
        this.student = student;
        this.projectData = projectData;
    }

    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        // print menu
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                          -Project Registration-                                       ║");
        System.out.println("╠══════════════════╦════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║%-18.18s║%-80.80s\t║\n", projectData[0],projectData[3]);
        System.out.println("╠══════════════════╩════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                         \u001B[31m-WARNING: You may not change project once confirmed-\u001B[0m                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════════════════════╝");

        // Get confirmation
        String confirmation;
        while(true){
            System.out.print("Type \"\u001B[31mCONFIRM\u001B[0m\" to confirm(Empty to back): ");
            confirmation = sc.nextLine();
            if(confirmation.isBlank()){
                return this.getPreviousPage();
            }
            if(confirmation.equals("CONFIRM")){
                break;
            }
            else{
                System.out.println("Invalid input!");
            }
        }

        // generate request
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        Long UID = UIDGenerator.generateLongId();
        try(FileWriter fw = new FileWriter(filepath, true)){
            PrintWriter writer = new PrintWriter(fw);
            writer.println(UID+","+student.getUserID()+",NaN,1,"+requestStatus_ENUM.PENDING.toString()+","+projectData[0]+",NaN,NaN,NaN");

        }catch(Exception e){
            System.out.println(e);
        }

        // reload request
        student.updateRequest();

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║             -Request Sent-            ║");
        System.out.println("╚═══════════════════════════════════════╝");

        System.out.println("Enter any input to return to Main Menu:");
        String hold = sc.nextLine();
        
        return this.getPreviousPage().getPreviousPage();
    }

}