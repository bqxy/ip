package duke;

/**
 * Represents a type of task.
 */
public class Event extends Task {
    protected String dayAndTime;

    /**
     * Constructor for Event object.
     *
     * @param description description of Event object.
     * @param dayAndTime day and time of the Event object.
     */
    public Event(String description, String dayAndTime) {
        super(description);
        this.dayAndTime = dayAndTime;
    }

    /**
     * Returns the description, schedule and letter-code to represent Event object.
     *
     * @return description, schedule and letter-code.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + "(at:" + dayAndTime + ")");
    }
}
