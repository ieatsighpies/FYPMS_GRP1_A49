package main.Utils;

/**
 * Provide code to change console color
 * The class does not have any public constructors and should be used as a static utility class.
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class ConsoleColors {
    // Reset
    /**
     * resets console color
     */
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    /**
     * red console color
     */
    public static final String RED = "\033[0;31m";     // RED
    /**
     * green console color
     */
    public static final String GREEN = "\033[0;32m";   // GREEN
    /**
     * yellow console color
     */
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    /**
     * blue console color
     */
    public static final String BLUE = "\033[0;34m";    // BLUE

    // Bold High Intensity
    /**
     * bold bright blue console color
     */
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
}
