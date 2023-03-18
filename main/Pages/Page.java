package main.Pages;
import java.io.Console;
import java.util.Scanner;

public abstract class Page {
    Scanner sc;
    Console console;
    String userID;

    private Page previousPage;
    public int accesslevel = -1;
    
    public Page(Page previousPage, int accesslevel){
        this.previousPage = previousPage;
        this.accesslevel = accesslevel;
    }

    public abstract Page executable();

    protected Page getPreviousPage() {
        if (previousPage != null)
            return previousPage;
        // if it's the first page
        else
            return this;
    }
}
