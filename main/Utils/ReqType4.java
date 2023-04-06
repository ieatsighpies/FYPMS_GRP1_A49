import main.Utils.FileHandler;
import java.util.ArrayList;
import java.util.List;
public class ReqType4 {
    public void executeRequest(String id,String action){
        //Changing request records
        String newStatus;

        String filepath = System.getProperty("user.dir") + "\\main\\Data\\request_record.csv";
        String[] data = FileHandler.readFile(filepath, id, 0);
        String newString;
        //FileHandler.writeFile(filepath, id, 0, newString);

        //Supervisor Name and Email from req_ID
        String facultyPath = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
        String[] data2 = FileHandler.readFile(facultyPath, data[7], 2);
        String Supervisor_Name= data2[0];
        String Supervisor_Email= data2[1];

        String Old_Supervisor = data[1];



        //Changing project records
        if (action=="APPROVE") {
            if(data2[5]=="1" ||data2[5]=="0"){
                //change project
            String projPath = System.getProperty("user.dir") + "\\main\\Data\\project_record.csv";
            String[] data1 = FileHandler.readFile(projPath, data[5], 0);
            String newString1;
            newString1 = data1[0] + "," + Supervisor_Name + "," + Supervisor_Email + "," + data1[3] + "," + "ALLOCATED" + "," + data1[5] + "," + data1[6];
            FileHandler.writeFile(projPath, data[5], 0, newString1);

            //change supervisor

                //change old
                String oldPath = System.getProperty("user.dir") + "\\main\\Data\\faculty_list.csv";
                String[] old = FileHandler.readFile(facultyPath, data[1], 2);
                String update_old= old[0]+ ","+old[1]+ ","+old[2]+ ","+old[3]+ ","+old[4]+ ","+String.valueOf(Integer.valueOf(old[5])-1);
                FileHandler.writeFile(oldPath, data[1], 2, update_old);
                //change new
                String update_new= data2[0]+ ","+data2[1]+ ","+data2[2]+ ","+data2[3]+ ","+data2[4]+ ","+String.valueOf(Integer.valueOf(data2[5])+1);
                FileHandler.writeFile(facultyPath, data[7], 2, update_new);
                System.out.println("Request successfully processed!");
            }
            else{
                System.out.println("This supervisor already has 2 students, can't transfer!");
                newString = data[0]+","+data[1]+","+data[2]+","+data[3]+","+"PENDING"+","+data[5]+","+data[6]+","+data[7]+","+data[8];
                FileHandler.writeFile(filepath, id, 0, newString);
            }
        }
        else{
            newString = data[0]+","+data[1]+","+data[2]+","+data[3]+","+"REJECTED"+","+data[5]+","+data[6]+","+data[7]+","+data[8];
            FileHandler.writeFile(filepath, id, 0, newString);
            System.out.println("Request successfully processed!");
        }

    }}