package duke;

/**
 * Represents all interactions with the user.
 */
public class Ui {
    static final String WELCOME_SCREEN = " ____________________________________________________________\n"
            + "  Hello! I'm Duke\n"
            + "  What can I do for you?\n"
            + "\n"
            + " ____________________________________________________________\n";

    static final String END_SCREEN = " ____________________________________________________________\n"
            + "  Bye. Hope to see you again soon!\n"
            + " ____________________________________________________________";

    /**
     * Prints welcome screen when duke starts up.
     */
    protected static void showWelcomeScreen() {
        System.out.println(WELCOME_SCREEN);
    }

    /**
     * Prints end screen when user ends duke program.
     */
    protected static void showEndScreen() {
        System.out.println(END_SCREEN);
    }
}
