package duke;

/**
 * Represents all the tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object. By default, the task is not completed.
     *
     * @param description description of Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of tasks; whether if its done or not.
     *
     * @return status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Returns description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the description along with the status of the task represented by
     * ticks (done) and cross (not done).
     *
     * @return symbols that determine if the task is done or not.
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "]" + " " + description);
    }
}
