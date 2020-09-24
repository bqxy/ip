package duke;

public class Ui extends Duke {
    static final String WELCOME_SCREEN = " ____________________________________________________________\n"
            + "  Hello! I'm Duke\n"
            + "  What can I do for you?\n"
            + "\n"
            + " ____________________________________________________________\n";

    static final String END_SCREEN = " ____________________________________________________________\n"
            + "  Bye. Hope to see you again soon!\n"
            + " ____________________________________________________________";

    protected static void showWelcomeScreen() {
        System.out.println(WELCOME_SCREEN);
    }

    protected static void showEndScreen() {
        System.out.println(END_SCREEN);
    }
}
