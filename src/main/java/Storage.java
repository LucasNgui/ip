import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final static String savePath = "./data/atom.txt";

    public Storage() {
        // Checks if a save file exists and creates one if it does not.
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

    public List<Task> load() {
        File f = new File(savePath);
        List<Task> tasks = new ArrayList<>();
        Scanner s;

        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found");
            return tasks;
        }

        while (s.hasNext()) {
            String[] line = s.nextLine().split(" /");
            switch (line[0]) {
            case "T":
                Task t = new ToDo(line[2]);
                if (line[1].equals("1")) {
                    t.mark();
                }
                tasks.add(t);
                break;
            case "D":
                Task d = new Deadline(line[2], line[3]);
                if (line[1].equals("1")) {
                    d.mark();
                }
                tasks.add(d);
                break;
            case "E":
                Task e = new Event(line[2], line[3], line[4]);
                if (line[1].equals("1")) {
                    e.mark();
                }
                tasks.add(e);
                break;
            default:
                break;
            }
        }
        return tasks;
    }

    public void writeTask(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s).append(" /");
        }

        try {
            FileWriter fw = new FileWriter(savePath);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error in writing to save file.");
        }
    }

    public void markTask(int taskIdx) {
        Path path = Paths.get(savePath);
        try {
            List<String> lines = Files.readAllLines(path);
            if (taskIdx > 0 && taskIdx <= lines.size()) {
                StringBuilder markedTask = new StringBuilder(lines.get(taskIdx - 1));
                markedTask.setCharAt(3, '1');
                lines.set(taskIdx - 1, markedTask.toString());
            } else {
                throw new AtomTaskNotFoundException(taskIdx);
            }
            Files.write(path, lines);
        } catch (IOException e) {
            System.out.println("Error in writing to save file.");
        }
    }

    public void unmarkTask(int taskIdx) {
        Path path = Paths.get(savePath);
        try {
            List<String> lines = Files.readAllLines(path);
            if (taskIdx > 0 && taskIdx <= lines.size()) {
                StringBuilder markedTask = new StringBuilder(lines.get(taskIdx - 1));
                markedTask.setCharAt(3, '0');
                lines.set(taskIdx - 1, markedTask.toString());
            } else {
                throw new AtomTaskNotFoundException(taskIdx);
            }
            Files.write(path, lines);
        } catch (IOException e) {
            System.out.println("Error in writing to save file.");
        }
    }

    public void deleteTask(int taskIdx) {
        Path path = Paths.get(savePath);
        try {
            List<String> lines = Files.readAllLines(path);
            if (taskIdx >= 0 && taskIdx < lines.size()) {
                lines.remove(taskIdx);
                Files.write(path, lines);
            } else {
                throw new AtomTaskNotFoundException(taskIdx);
            }
        } catch (IOException e) {
            System.out.println("Error in writing to save file.");
        }
    }
}
