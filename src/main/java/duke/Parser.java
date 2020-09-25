package duke;

/**
 * Deals with making sense of the user command.
 */
public class Parser extends Duke {
    /**
     * Parses the line inputted by user; determines if the line consists of any keywords related to
     * the operations of Duke.
     *
     * @param line the line input by user.
     * @return either original line inputted by user or the wrong command that would be processed later.
     */
    protected static String parseCommand(String line) {
        if (line.equals("bye") || line.equals("list")) {
            return line;
        }

        if (line.contains("done") || line.contains("deadline") || line.contains("event") ||
                line.contains(("todo")) || line.contains(("delete"))) {
            return line;
        }

        return showWrongCommand();
    }

    /**
     * Returns the wrong command when line inputted by user does not contain any keywords
     * related to the operations of Duke.
     *
     * @return the wrong command that would be processed later.
     */
    protected static String showWrongCommand() {
       return ("I'm sorry, but I don't know what that means :-C");
    }
}
