package main.Pages;

public class Exit extends Page{
    
    public Exit(Page previousPage){
        super(previousPage, -1);
    }

    @Override
    public Page executable(){
        return null;
    }
}
