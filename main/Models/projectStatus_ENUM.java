package main.Models;
/**
 * Provide status of project
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public enum projectStatus_ENUM {

    /**
     * project is available
     */
    AVAILABLE,

    /**
     * project is unavailable when supervisor is supervising 2 projects
     */
    UNAVAILABLE,
    
    /**
     * project is reserved after a student request for it
     */
    RESERVED,
    
    /**
     * project is allocated after coordinator approves the request
     */
    ALLOCATED;
}