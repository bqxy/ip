package duke;

/**
 * Represents a type of task.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo object.
     *
     * @param description description of ToDo object.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the description along with its letter-code to represent ToDo object.
     *
     * @return description and letter-code.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
