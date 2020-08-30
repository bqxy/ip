import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String greet = showWelcomeScreen();
        System.out.println(greet);
        
        Task currentTask;
        Task[] storeTasks = new Task[100];
        int numOfTasks = 0;
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }

            if (line.equals("list")) {
                System.out.println(" ____________________________________________________________");
                System.out.println("  Here are the tasks in your list:");
                for (int i = 0; i < numOfTasks; i++) {
                    System.out.println("  " + (i + 1) + "." + storeTasks[i]);
                }
                System.out.println(" ____________________________________________________________");
                continue;
            }

            if (line.contains("done")) {
                String[] word = line.split(" ");
                int taskNum = Integer.parseInt(word[1]);
                if (taskNum >= 1 || taskNum <= numOfTasks) {
                    storeTasks[taskNum - 1].setDone();
                    System.out.println(" ____________________________________________________________");
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("   " + taskNum + "." + storeTasks[taskNum - 1]);
                    System.out.println(" ____________________________________________________________");
                }
                continue;
            }

            if (line.contains("deadline")) {
                String[] word = line.split("/by");
                word[0] = word[0].replace("deadline ", "");
                storeTasks[numOfTasks] = new Deadline(word[0], word[1]);
                numOfTasks++;
                System.out.println(" ____________________________________________________________");
                System.out.println("  Got it. I've added this task:");
                System.out.println("    " + storeTasks[numOfTasks - 1]);
                System.out.println("  Now you have " + numOfTasks + " tasks in the list.");
                System.out.println(" ____________________________________________________________");
                continue;
            }

            if (line.contains("todo")) {
                String[] word = line.split("todo ");
                storeTasks[numOfTasks] = new ToDo(word[1]);
                numOfTasks++;
                System.out.println(" ____________________________________________________________");
                System.out.println("  Got it. I've added this task:");
                System.out.println("    " + storeTasks[numOfTasks - 1]);
                System.out.println("  Now you have " + numOfTasks + " tasks in the list.");
                System.out.println(" ____________________________________________________________");
                continue;
            }

            if (line.contains("event")) {
                String[] word = line.split("/at");
                word[0] = word[0].replace("event ", "");
                storeTasks[numOfTasks] = new Event(word[0], word[1]);
                numOfTasks++;
                System.out.println(" ____________________________________________________________");
                System.out.println("  Got it. I've added this task:");
                System.out.println("    " + storeTasks[numOfTasks - 1]);
                System.out.println("  Now you have " + numOfTasks + " tasks in the list.");
                System.out.println(" ____________________________________________________________");
                continue;
            }

            storeTasks[numOfTasks] = new Task(line);
            numOfTasks++;
            System.out.println(" ____________________________________________________________");
            System.out.println("  added: " + line);
            System.out.println(" ____________________________________________________________");
        }
        System.out.println(" ____________________________________________________________");
        System.out.println("  Bye. Hope to see you again soon!");
        System.out.println(" ____________________________________________________________");
    }

    private static String showWelcomeScreen() {
        String greet = " ____________________________________________________________\n"
                + "  Hello! I'm Duke\n"
                + "  What can I do for you?\n"
                + "\n"
                + " ____________________________________________________________\n";
        return greet;
    }
}