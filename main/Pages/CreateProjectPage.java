package main.Pages;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import main.Models.Project;
import main.Models.Supervisor;
import main.Models.projectStatus_ENUM;
import main.Utils.ConsoleUtils;
import main.Utils.UIDGenerator;

public class CreateProjectPage extends Page{
    Scanner sc = new Scanner(System.in);

    Supervisor staff;
    Project project;

    public CreateProjectPage(Page previousPage, Supervisor staff) {
        super(previousPage);
        this.staff = staff;
    }

    @Override
    public Page executable() {
        ConsoleUtils.clearScreen();
        // print menu
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                      -Project Creation-                  ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║      -Leave any field empty to return to Main Menu-      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        // get project title
        String title;
        System.out.print("Enter project title: ");
        title = sc.nextLine();
        while(true){
            if(title.isBlank()){
                return this.getPreviousPage();
            }
            if(title.contains(",")){
                System.out.println("Title cannot contain commas (,)");
                System.out.print("Enter project title: ");
                title = sc.nextLine();
            }
            else{
                break;
            }
        }

        // confirmation
        String confirm;
        System.out.print("Enter any input to confirm(empty to return): ");
        confirm = sc.nextLine();
        if(confirm.isBlank()){
            return this.getPreviousPage();
        }

        // write to file
        String filepath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
        Long UID = UIDGenerator.generateLongId();
        try (FileWriter fw = new FileWriter(filepath, true)) {
            PrintWriter writer = new PrintWriter(fw);
            writer.println(UID + "," + staff.getName() + "," + staff.getEmail() + "," + title + "," + projectStatus_ENUM.AVAILABLE.toString() + ",NaN,NaN");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // print added project
        ConsoleUtils.clearScreen();
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                            -Project Added-                                            ║");
        System.out.println("╠══════════════════╦════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ID                ║Project Title                                                                       ║");
        System.out.println("╠══════════════════╬════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║%-18.18s║%-80.80s\t║\n", UID.toString(), title);
        System.out.println("╚══════════════════╩════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.print("Enter any input to return to Main Menu:");
        String temp = sc.nextLine();
        staff.updateProject();
        return this.getPreviousPage();
    }

}
