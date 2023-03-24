package main.Pages;

public class Exit extends Page{
    
    public Exit(Page previousPage){
        super(previousPage);
    }

    @Override
    public Page executable(){
        return null;
    }
}
