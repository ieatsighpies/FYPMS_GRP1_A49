package main.Pages;

import java.util.Scanner;

public class Welcome extends Page{
    Scanner sc = new Scanner(System.in);
    String userPass;
    String userType;

    public Welcome(Page previousPage, int accesslevel){
        super(previousPage, accesslevel);
    }

    @Override
    public Page executable(){
        
        System.out.println("Welcome to FYP Interface");
        System.out.println("Select user type:");
        System.out.println("1) Student");
        System.out.println("2) Staff");
        System.out.println("Empty input to exit");

        // get user type
        System.out.print("Enter your user type: ");
        this.userType = sc.nextLine().trim();
        System.out.println();

        // if input not blank, loop to ask for valid input
        while(!(this.userType.matches("^[1-2]{1}$"))){

            // exit program if empty input
            if(this.userType.isBlank()){
                return new Exit(null);
            }

            System.out.println("Enter a valid user type:");
            this.userType = sc.nextLine().trim();
        }

        return new Login(this, -1, this.userType);

        
    }

}
