package duke;

/**
 * Represents a type of task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for Deadline object.
     *
     * @param description description of Deadline object.
     * @param by deadline of the Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the description, schedule and letter-code to represent Deadline object.
     *
     * @return description, schedule and letter-code.
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + "(by:" + by + ")");
    }
}
