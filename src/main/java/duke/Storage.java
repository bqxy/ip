package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage extends Duke {
    protected static void readFileContents(String filePath, ArrayList<Task> storeTasks,
                                         ArrayList<String> tasksText) throws FileNotFoundException,
            DukeException {
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

    protected static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

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
