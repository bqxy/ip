package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage extends Duke {
    /**
     * Reads file content whenever Duke starts up and updates the content in text file into both arrays
     * that contains the tasks and tasks (in text format).
     *
     * @param filePath file path that contains all the tasks in text format.
     * @param storeTasks array that stores all tasks.
     * @param tasksText array that stores all tasks in text format.
     * @throws FileNotFoundException if file that contains data of tasks is not found.
     * @throws DukeException if folder not found.
     */
    protected static void readFileContents(String filePath, ArrayList<Task> storeTasks,
                                         ArrayList<String> tasksText) throws FileNotFoundException, DukeException {
        try {
            createFile(filePath);
        } catch (IOException e) {
            System.out.println("IO Error");
        }
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

    /**
     * Writes task data to text file.
     *
     * @param filePath file path that contains all the tasks in text format.
     * @param textToAdd string that would be added to the text file.
     * @throws IOException if there are issues with input or output operations.
     */
    protected static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    /**
     * Updates text file whenever an operation is done on the task,
     * for e.g. todo, event, deadline and delete.
     *
     * @param tasksText array that stores all tasks in text format.
     * @param filePath file path that contains all the tasks in text format.
     * @throws FileNotFoundException if file that contains data of tasks is not found.
     */
    protected static void updateFile(ArrayList<String> tasksText, String filePath) throws FileNotFoundException {
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

    /**
     * Creates file if existing file is not available.
     *
     * @param filePath file path that contains all the tasks in text format.
     * @throws IOException if there are issues with input or output operations.
     */
    protected static void createFile(String filePath) throws IOException {
        File storageFile = new File(filePath);
        if (storageFile.exists()) {
            return;
        }
        if (!storageFile.getParentFile().exists()) {
            storageFile.getParentFile().mkdirs();
        }
        storageFile.createNewFile();
        System.out.println(storageFile.getAbsolutePath());
    }
}
