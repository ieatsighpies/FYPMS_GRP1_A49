package main.Pages;

public abstract class Page {
    private Page previousPage;
    
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
