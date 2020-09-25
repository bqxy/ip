package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList extends Duke {
    protected static boolean executeCommand(String command, ArrayList<Task> taskList, String filePath,
                                            ArrayList<String> tasksText) {
        if (command.equals("bye")) {
            return true;
        }
        if (command.equals("list")) {
            return false;
        }
        if (command.contains("done")) {
            try {
                TaskList.doneCommand(command, taskList, filePath, tasksText);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
        if (command.contains("deadline")) {
            try {
                TaskList.deadlineCommand(command, taskList, filePath, tasksText);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
        if (command.contains("todo")) {
            try {
                TaskList.todoCommand(command, taskList, filePath, tasksText);

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
        if (command.contains("event")) {
            try {
                TaskList.eventCommand(command, taskList, filePath, tasksText);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
        if (command.contains("delete")) {
            try {
                TaskList.deleteCommand(command, taskList, filePath, tasksText);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
        if (command.contains("find")) {
            try {
                TaskList.findCommand(command, taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        System.out.println(command);
        return false;
    }

    protected static void listCommand(ArrayList<Task> storeTasks) {
        System.out.println(" ____________________________________________________________");
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < storeTasks.size(); i++) {
            System.out.println("  " + (i + 1) + "." + storeTasks.get(i));
        }
        System.out.println(" ____________________________________________________________");
    }

    protected static void doneCommand(String line, ArrayList<Task> storeTasks, String filePath,
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
                Storage.updateFile(tasksText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Folder not found");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please select the appropriate task number on the list");
        }
    }

    protected static void deadlineCommand(String line, ArrayList<Task> storeTasks, String filePath,
                                        ArrayList<String> tasksText) throws DukeException{
        try {
            String[] words = line.split("/by");
            words[0] = words[0].replace("deadline ", "");
            if (words[0].isBlank()) {
                throw new DukeException("For deadline command, the format is" +
                        " 'deadline <DETAILS> /by <DATE_AND_OR_TIME>");
            }
            if (!line.contains("/by")) {
                throw new DukeException("For deadline command, the format is" +
                        " 'deadline <DETAILS> /by <DATE_AND_OR_TIME>");
            }
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
                Storage.updateFile(tasksText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Folder not found");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("For deadline command, the format is" +
                    " 'deadline <DETAILS> /by <DATE_AND_OR_TIME>");
        }
    }

    protected static void todoCommand(String line, ArrayList<Task> storeTasks, String filePath,
                                    ArrayList<String> tasksText) throws DukeException {
        try {
            String[] words = line.split("todo ");
            if (words[1].isBlank()) {
                throw new DukeException("For todo command, the format is" + " 'todo <DETAILS>");
            }
            storeTasks.add(new ToDo(words[1]));
            System.out.println(" ____________________________________________________________");
            System.out.println("  Got it. I've added this task:");
            System.out.println("    " + storeTasks.get(storeTasks.size() - 1));
            System.out.println("  Now you have " + storeTasks.size() + " tasks in the list.");
            System.out.println(" ____________________________________________________________");

            // Structuring text to write to file
            String text = "T" + " | " + "0" + " | " + storeTasks.get(storeTasks.size() - 1).description;
            tasksText.add(text);
            try {
                Storage.updateFile(tasksText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Folder not found");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("For todo command, the format is" + " 'todo <DETAILS>");
        }
    }

    protected static void eventCommand(String line, ArrayList<Task> storeTasks, String filePath,
                                     ArrayList<String> tasksText) throws DukeException {
        try {
            String[] words = line.split("/at");
            words[0] = words[0].replace("event ", "");
            if (words[0].isBlank()) {
                throw new DukeException("For event command, the format is" +
                        " 'event <DETAILS> /at <DATE_AND_OR_TIME>");
            }
            if (!line.contains("/at")) {
                throw new DukeException("For event command, the format is" +
                        " 'event <DETAILS> /at <DATE_AND_OR_TIME>");
            }
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
                Storage.updateFile(tasksText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Folder not found");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("For event command, the format is" +
                    " 'event <DETAILS> /at <DATE_AND_OR_TIME>");
        }
    }

    protected static void deleteCommand(String line, ArrayList<Task> storeTasks, String filePath,
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
            tasksText.remove(taskNum - 1);

            try {
                Storage.updateFile(tasksText, filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Folder not found");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task on the list");
        }
    }

    protected static void findCommand(String line, ArrayList<Task> storeTasks) throws DukeException {
        try {
            String[] words = line.split("find ");
            if (words[1].isBlank()) {
                throw new DukeException("For find command, the format is" + " 'find <DETAILS>");
            }
            System.out.println(" ____________________________________________________________");
            System.out.println("  Here are the matching tasks in your list:");
            for (int i = 0; i < storeTasks.size(); i++) {
                if (storeTasks.get(i).toString().contains(words[1])) {
                    System.out.println("  " + (i + 1) + "." + storeTasks.get(i));
                }
            }
            System.out.println(" ____________________________________________________________");

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("For find command, the format is" + " 'find <DETAILS>");
        }
    }
}
