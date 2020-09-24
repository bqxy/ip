package duke;

public class Parser extends Duke {

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

    protected static String showWrongCommand() {
       return ("I'm sorry, but I don't know what that means :-C");
    }
}
