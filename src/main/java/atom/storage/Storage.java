package atom.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import atom.task.Deadline;
import atom.task.Event;
import atom.task.Task;
import atom.task.ToDo;

/**
 * Handles saving and loading data.
 */
public class Storage {
    /** The file path of the save file */
    private static final String savePath = "./data/atom.txt";

    /**
     * An enum for types of tasks.
     */
    public enum TaskName {
        T, D, E
    }

    /**
     * Instantiates a <code>Storage</code> and
     * checks if a save file exists and creates one if it does not.
     */
    public Storage() {
        File file = new File(savePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create save file.");
        }
    }

    /**
     * Loads data from the save file.
     *
     * @return A <code>TaskList</code> filled with tasks from the loaded data.
     */
    public ArrayList<Task> load() {
        Path path = Paths.get(savePath);
        List<String> lines;

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Error in reading from save file.");
            return null;
        }

        List<Task> loadedTasks = lines.stream()
                .map(this::convertLineToTask)
                .toList();

        return new ArrayList<>(loadedTasks);
    }

    /**
     * Converts a line in a save file to the corresponding task.
     *
     * @param line A save file line.
     * @return The corresponding task.
     */
    private Task convertLineToTask(String line) {
        String[] splitLine = line.split(" /");
        Task task;

        switch (TaskName.valueOf(splitLine[0])) {
            case TaskName.T:
                task = new ToDo(splitLine[2]);
                break;
            case TaskName.D:
                task = new Deadline(splitLine[2], splitLine[3]);
                break;
            case TaskName.E:
                task = new Event(splitLine[2], splitLine[3], splitLine[4]);
                break;
            default:
                assert false : "Invalid save format.";
                return null;
        }

        if (splitLine[1].equals("1")) {
            task.mark();
        }

        return task;
    }

    /**
     * Adds a task to the save file.
     *
     * @param taskName The name of the task.
     * @param args The arguments to the task.
     */
    public void writeTask(TaskName taskName, String[] args) {
        StringBuilder sb = new StringBuilder(taskName.toString());
        sb.append(" /0");
        for (String s : args) {
            sb.append(" /").append(s);
        }
        sb.append("\n");

        try {
            FileWriter fw = new FileWriter(savePath, true);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error in writing to save file.");
        }
    }

    /**
     * Marks a task in the save file.
     *
     * @param taskIdx The index of the task to be marked.
     */
    public void markTask(int taskIdx) {
        Path path = Paths.get(savePath);
        try {
            List<String> lines = Files.readAllLines(path);
            assertValidTask(lines.size(), taskIdx);

            StringBuilder markedTask = new StringBuilder(lines.get(taskIdx - 1));
            markedTask.setCharAt(3, '1');
            lines.set(taskIdx - 1, markedTask.toString());

            Files.write(path, lines);
        } catch (IOException e) {
            System.out.println("Error in writing to save file.");
        }
    }

    /**
     * Marks a task in the save file.
     *
     * @param taskIdx The index of the task to be marked.
     */
    public void unmarkTask(int taskIdx) {
        Path path = Paths.get(savePath);
        try {
            List<String> lines = Files.readAllLines(path);
            assertValidTask(lines.size(), taskIdx);

            StringBuilder markedTask = new StringBuilder(lines.get(taskIdx - 1));
            markedTask.setCharAt(3, '0');
            lines.set(taskIdx - 1, markedTask.toString());

            Files.write(path, lines);
        } catch (IOException e) {
            System.out.println("Error in writing to save file.");
        }
    }

    /**
     * Deletes a task in the save file.
     *
     * @param taskIdx The index of the task to be deleted.
     */
    public void deleteTask(int taskIdx) {
        Path path = Paths.get(savePath);
        try {
            List<String> lines = Files.readAllLines(path);
            assertValidTask(lines.size(), taskIdx);

            lines.remove(taskIdx);

            Files.write(path, lines);
        } catch (IOException e) {
            System.out.println("Error in writing to save file.");
        }
    }

    /**
     * Asserts if the provided task index is valid.
     *
     * @param maxIdx The maximum task index.
     * @param taskIdx The task index provided.
     */
    private void assertValidTask(int maxIdx, int taskIdx) {
        assert taskIdx >= 0 && taskIdx < maxIdx : "Task index out of bounds";
    }
}
