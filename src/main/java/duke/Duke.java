package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Represents main program of Duke.
 */
public class Duke {

    public static void main(String[] args) {
        Ui.showWelcomeScreen();

        ArrayList<Task> tasksList = new ArrayList<>();
        ArrayList<String> tasksText = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String filePath = "./data/duke.txt";
        boolean isExit = false;

        try {
            Storage.readFileContents(filePath, tasksList ,tasksText);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        while (!isExit) {
            TaskList.listCommand(tasksList);
            String line = in.nextLine();
            String command = Parser.parseCommand(line);
            isExit = TaskList.executeCommand(command, tasksList, filePath, tasksText);
        }

        Ui.showEndScreen();
    }
}