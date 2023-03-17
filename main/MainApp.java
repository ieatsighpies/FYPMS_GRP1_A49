package main;

import main.Pages.Exit;
import main.Pages.Page;
import main.Pages.Welcome;

public class MainApp {
    public static void main(String[] args) {

        Page currentPage = new Welcome(null, 0);
        do {
            currentPage = currentPage.executable();
        } while (!(currentPage instanceof Exit));

    }
}
