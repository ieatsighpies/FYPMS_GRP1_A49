package main.Pages;

public class TransferStudent extends Page{

    public TransferStudent(Page previousPage){
        super(previousPage);
    }

    @Override
    public Page executable(){
        
        return this;
    }
}
