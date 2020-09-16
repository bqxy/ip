package duke;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        showWelcomeScreen();

        ArrayList<Task> storeTasks = new ArrayList<>();
        ArrayList<String> tasksText = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);
        String filePath = "data/duke.txt";

        try {
            readFileContents(filePath, storeTasks ,tasksText);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            listCommand(storeTasks);
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                listCommand(storeTasks);
                continue;
            }
            if (line.contains("done")) {
                try {
                    doneCommand(line, storeTasks, filePath, tasksText);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if (line.contains("deadline")) {
                try {
                    deadlineCommand(line, storeTasks, filePath, tasksText);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if (line.contains("todo")) {
                try {
                    todoCommand(line, storeTasks, filePath, tasksText);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if (line.contains("event")) {
                try {
                    eventCommand(line, storeTasks, filePath, tasksText);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if (line.contains("delete")) {
                try {
                    deleteCommand(line, storeTasks, filePath, tasksText);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
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

    private static void listCommand(ArrayList<Task> storeTasks) {
        System.out.println(" ____________________________________________________________");
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < storeTasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + storeTasks.get(i));
        }
        System.out.println(" ____________________________________________________________");
    }

    private static void doneCommand(String line, ArrayList<Task> storeTasks, String filePath,
                                    ArrayList<String> tasksText) throws DukeException {
        try {
            String[] words = line.split(" ");
            int taskNum = Integer.parseInt(words[1]);

            if (taskNum >= 1 && taskNum <= storeTasks.size()) {
                storeTasks.get(taskNum - 1).setDone();
                System.out.println(" ____________________________________________________________");
                System.out.println("  Nice! I've marked this task as done:");
                System.out.println("   " + taskNum + "." + storeTasks.get(taskNum - 1));
                System.out.println(" ____________________________________________________________");
            }
            // Update text file
            String textLine = tasksText.get(taskNum - 1);
            String[] text = textLine.split(" \\| ");
            if (text[0].equals("T")) {
                String newText = "T" + " | " + "1" + " | " + storeTasks.get(taskNum - 1).description;
                tasksText.set(taskNum - 1, newText);
            }
            if (text[0].equals("D")) {
                String newText = "D" + " | " + "1" + " | " + text[2] + " | " + text[3];
                tasksText.set(taskNum - 1, newText);
            }
            if (text[0].equals("E")) {
                String newText = "E" + " | " + "1" + " | " + text[2] + " | " + text[2];
                tasksText.set(taskNum - 1, newText);
            }
            try {
                updateFile(tasksText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Folder not found");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You do not have any task(s)");
        }
    }

    private static void deadlineCommand(String line, ArrayList<Task> storeTasks, String filePath,
                                        ArrayList<String> tasksText) throws DukeException{
        String[] words = line.split("/by");
        words[0] = words[0].replace("deadline ", "");
        storeTasks.add(new Deadline(words[0], words[1]));
        System.out.println(" ____________________________________________________________");
        System.out.println("  Got it. I've added this task:");
        System.out.println("    " + storeTasks.get(storeTasks.size() - 1));
        System.out.println("  Now you have " + storeTasks.size() + " tasks in the list.");
        System.out.println(" ____________________________________________________________");

        // Structuring text to write to file
        String text = "D" + " | " + "0" + " | " + words[0] + "|" + words[1];
        tasksText.add(text);
        try {
            updateFile(tasksText, filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Folder not found");
        }
    }

    private static void todoCommand(String line, ArrayList<Task> storeTasks, String filePath,
                                    ArrayList<String> tasksText) throws DukeException {
        try {
            if (line.equals("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            String[] words = line.split("todo ");
            storeTasks.add(new ToDo(words[1]));
            //storeTasks[numOfTasks] = new ToDo(words[1]);
            System.out.println(" ____________________________________________________________");
            System.out.println("  Got it. I've added this task:");
            System.out.println("    " + storeTasks.get(storeTasks.size() - 1));
            System.out.println("  Now you have " + storeTasks.size() + " tasks in the list.");
            System.out.println(" ____________________________________________________________");

            // Structuring text to write to file
            String text = "T" + " | " + "0" + " | " + storeTasks.get(storeTasks.size() - 1).description;
            tasksText.add(text);
            try {
                updateFile(tasksText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Folder not found");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private static void eventCommand(String line, ArrayList<Task> storeTasks, String filePath,
                                     ArrayList<String> tasksText) throws DukeException {
        String[] words = line.split("/at");
        words[0] = words[0].replace("event ", "");
        storeTasks.add(new Event(words[0], words[1]));
        System.out.println(" ____________________________________________________________");
        System.out.println("  Got it. I've added this task:");
        System.out.println("    " + storeTasks.get(storeTasks.size() - 1));
        System.out.println("  Now you have " + storeTasks.size() + " tasks in the list.");
        System.out.println(" ____________________________________________________________");

        // Structuring text to write to file
        String text = "E" + " | " + "0" + " | " + words[0] + "|" + words[1];
        tasksText.add(text);
        try {
            updateFile(tasksText, filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Folder not found");
        }
    }

    private static void deleteCommand(String line, ArrayList<Task> storeTasks, String filePath,
                                      ArrayList<String> tasksText) throws DukeException {
        try {
            String[] words = line.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            if (taskNum >= 1 && taskNum <= storeTasks.size()) {
                System.out.println(" ____________________________________________________________");
                System.out.println("  Nice! I've removed this task:");
                System.out.println("   "  + storeTasks.get(taskNum - 1));
                System.out.println(" ____________________________________________________________");
                storeTasks.remove(taskNum - 1);
            } else {
                throw new DukeException("No such task on the list");
            }

            // Update text file
            String textLine = tasksText.get(taskNum - 1);
            String[] text = textLine.split(" \\| ");
            if (text[0].equals("T")) {
                tasksText.remove(taskNum - 1);
            }
            if (text[0].equals("D")) {
                tasksText.remove(taskNum - 1);
            }
            if (text[0].equals("E")) {
                tasksText.remove(taskNum - 1);
            }
            try {
                updateFile(tasksText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Folder not found");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task on the list");
        }
    }

    private static void wrongCommand() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-C");
    }

    private static void readFileContents(String filePath, ArrayList<Task> storeTasks,
                                         ArrayList<String> tasksText) throws FileNotFoundException,
            DukeException {
        File f = new File(filePath); // create a File for the given file path
        File folder = new File("./data/"); // folder directory for duke.txt
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        // Check if folder exist
        if (folder.isDirectory() == false) {
            throw new DukeException("Folder not found");
        }
        // Read tasks into Duke
        while (s.hasNext()) {
            String line = s.nextLine();
            tasksText.add(line);
            String[] words = line.split(" \\| ");
            // Read Todo tasks into Duke
            if (words[0].equals("T")) {
                storeTasks.add(new ToDo(words[2]));
                if (words[1].equals("1")) {
                    storeTasks.get(storeTasks.size() - 1).setDone();
                }
            }
            // Read Deadline tasks into Duke
            if (words[0].equals("D")) {
                storeTasks.add(new Deadline(words[2], words[3]));
                if (words[1].equals("1")) {
                    storeTasks.get(storeTasks.size() - 1).setDone();
                }
            }
            // Read Event tasks into Duke
            if (words[0].equals("E")) {
                storeTasks.add(new Event(words[2], words[3]));
                if (words[1].equals("1")) {
                    storeTasks.get(storeTasks.size() - 1).setDone();
                }
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    private static void updateFile(ArrayList<String> tasksText, String filePath) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filePath); // reset .txt file
        int count = 0;
        for (int i = 0; i < tasksText.size(); i++) {
            try {
                writeToFile(filePath, tasksText.get(i));
            } catch (IOException e) {
                System.out.println("IO Error");
            }
        }
    }
}