import java.util.Scanner;

public class Duke {
    static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        showWelcomeScreen();

        Task[] storeTasks = new Task[MAX_TASKS];
        int numOfTasks = 0;
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }

            if (line.equals("list")) {
                listCommand(numOfTasks, storeTasks);
                continue;
            }

            if (line.contains("done")) {
                doneCommand(line, numOfTasks, storeTasks);
                continue;
            }

            if (line.contains("deadline")) {
                deadlineCommand(line, numOfTasks, storeTasks);
                numOfTasks++;
                continue;
            }

            if (line.contains("todo")) {
                try {
                    todoCommand(line, numOfTasks, storeTasks);
                    numOfTasks++;
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            if (line.contains("event")) {
                eventCommand(line, numOfTasks, storeTasks);
                numOfTasks++;
                continue;
            }

            try {
                wrongCommand();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        showEndScreen();
    }

    private static void showWelcomeScreen() {
        String greet = " ____________________________________________________________\n"
                + "  Hello! I'm Duke\n"
                + "  What can I do for you?\n"
                + "\n"
                + " ____________________________________________________________\n";
        System.out.println(greet);
    }

    private static void showEndScreen() {
        String endScreen = " ____________________________________________________________\n"
                + "  Bye. Hope to see you again soon!\n"
                + " ____________________________________________________________";
        System.out.println(endScreen);
    }

    private static void listCommand(int numOfTasks, Task[] storeTasks) {
        System.out.println(" ____________________________________________________________");
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println("  " + (i + 1) + "." + storeTasks[i]);
        }
        System.out.println(" ____________________________________________________________");
    }

    private static void doneCommand(String line, int numOfTasks, Task[] storeTasks) {
        String[] words = line.split(" ");
        int taskNum = Integer.parseInt(words[1]);
        if (taskNum >= 1 || taskNum <= numOfTasks) {
            storeTasks[taskNum - 1].setDone();
            System.out.println(" ____________________________________________________________");
            System.out.println("  Nice! I've marked this task as done:");
            System.out.println("   " + taskNum + "." + storeTasks[taskNum - 1]);
            System.out.println(" ____________________________________________________________");
        }
    }

    private static void deadlineCommand(String line, int numOfTasks, Task[] storeTasks) {
        String[] words = line.split("/by");
        words[0] = words[0].replace("deadline ", "");
        storeTasks[numOfTasks] = new Deadline(words[0], words[1]);
        System.out.println(" ____________________________________________________________");
        System.out.println("  Got it. I've added this task:");
        System.out.println("    " + storeTasks[numOfTasks]);
        System.out.println("  Now you have " + (numOfTasks + 1) + " tasks in the list.");
        System.out.println(" ____________________________________________________________");
    }

    private static void todoCommand(String line, int numOfTasks, Task[] storeTasks) throws DukeException {
        try {
            if (line.equals("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            String[] words = line.split("todo ");
            storeTasks[numOfTasks] = new ToDo(words[1]);
            System.out.println(" ____________________________________________________________");
            System.out.println("  Got it. I've added this task:");
            System.out.println("    " + storeTasks[numOfTasks]);
            System.out.println("  Now you have " + (numOfTasks + 1) + " tasks in the list.");
            System.out.println(" ____________________________________________________________");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private static void eventCommand(String line, int numOfTasks, Task[] storeTasks) {
        String[] words = line.split("/at");
        words[0] = words[0].replace("event ", "");
        storeTasks[numOfTasks] = new Event(words[0], words[1]);
        System.out.println(" ____________________________________________________________");
        System.out.println("  Got it. I've added this task:");
        System.out.println("    " + storeTasks[numOfTasks]);
        System.out.println("  Now you have " + (numOfTasks + 1) + " tasks in the list.");
        System.out.println(" ____________________________________________________________");
    }

     private static void wrongCommand() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-C");
    }
}