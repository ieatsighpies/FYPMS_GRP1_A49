package main;

import main.Pages.Exit;
import main.Pages.Page;
import main.Pages.Welcome;

/**
 * The main application
 *
 * @author Dr. Heinz Doofenshmirtz
 * @version 1.0
 * @since 13-4-2023
 */
public class MainApp {
    /**
     * Main driver
     * @param args default param
     */
    public static void main(String[] args) {

        Page currentPage = new Welcome(null);
        do {
            currentPage = currentPage.executable();
        } while (!(currentPage instanceof Exit));

    }
}
