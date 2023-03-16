package main;
import java.io.Console;
import java.util.Scanner;

public class Page {
    Scanner sc;
    Console console;
    String userID;
    
    public Page(Scanner sc, Console console){
        this.sc = sc;
        this.console = console;
    }

    public String getUser(){
        return this.userID;
    }
}
