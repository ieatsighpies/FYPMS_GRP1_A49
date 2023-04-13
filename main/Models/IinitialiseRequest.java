package main.Models;

/**
 * Interface for classes that have to initialise request
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public interface IinitialiseRequest {
    /**
     * Method to initialise request
     *
     */
    public abstract void initialiseRequest();

    /**
     * Method to update request
     *
     */
    public abstract void updateRequest();
}