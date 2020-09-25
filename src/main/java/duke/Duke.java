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

        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<String> tasksText = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String filePath = "./data/duke.txt";
        boolean isExit = false;

        try {
            Storage.readFileContents(filePath, taskList ,tasksText);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        while (!isExit) {
            TaskList.listCommand(taskList);
            String line = in.nextLine();
            String command = Parser.parseCommand(line);
            isExit = TaskList.executeCommand(command, taskList, filePath, tasksText);
        }

        Ui.showEndScreen();
    }
}