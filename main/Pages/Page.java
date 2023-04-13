package main.Pages;
/**
* Abstract class for pages. All pages will inherit Page
*
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/

public abstract class Page {
    /**
     * Previous page visited
     */
    private Page previousPage;
    /**
     * Constructor of Page
     * @param previousPage previous page visited
     */
    public Page(Page previousPage){
        this.previousPage = previousPage;
    }
    /**
     * main functionality of the page
     * @return page
     */
    public abstract Page executable();
    /**
     * Gets previous page visited
     * @return previous page visited
     */
    protected Page getPreviousPage() {
        if (this.previousPage != null)
            return this.previousPage;
        // if it's the first page
        else
            return this;
    }
}
