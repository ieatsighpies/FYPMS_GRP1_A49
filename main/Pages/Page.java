package main.Pages;
import java.io.Console;
import java.util.Scanner;

public abstract class Page {
    Scanner sc;
    Console console;
    String userID;

    private Page previousPage;
    public int accesslevel = -1;
    
    public Page(Page previousPage){
        this.previousPage = previousPage;
    }

    public abstract Page executable();

    protected Page getPreviousPage() {
        if (this.previousPage != null)
            return this.previousPage;
        // if it's the first page
        else
            return this;
    }
}
