package main.Pages;

/**
* Exit the current page to return to the previous page
*
* @author Dr. Heinz Doofenshmirtz
* @version 1.0
* @since 2023-4-13
*/

public class Exit extends Page{
    /**
     * Constructor for Exit
     * @param previousPage the previous page
     */
    public Exit(Page previousPage){
        super(previousPage);
    }
    /**
    * Main executable for this page
    * @return null
    */
    @Override
    public Page executable(){
        return null;
    }
}
