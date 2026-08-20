package atom.task;

import atom.exception.AtomTaskNotFoundException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int idx) throws AtomTaskNotFoundException {
        if (idx < 0 || idx > size()) {
            throw new AtomTaskNotFoundException(idx + 1);
        }
        return tasks.remove(idx);
    }

    public void mark(int idx) throws AtomTaskNotFoundException {
        get(idx).mark();
    }

    public void unmark(int idx) throws AtomTaskNotFoundException {
        get(idx).unmark();
    }

    public Task get(int idx) throws AtomTaskNotFoundException {
        if (idx < 0 || idx > size()) {
            throw new AtomTaskNotFoundException(idx + 1);
        }
        return tasks.get(idx);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Finds the all tasks which contains the
     * matching keyword in the description.
     *
     * @param keyword The keyword.
     * @return A <code>TaskList</code> containing all matching tasks.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                res.add(t);
            }
        }
        return new TaskList(res);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task s : tasks) {
            sb.append(i).append(". ").append(s).append("\n");
            i++;
        }
        // delete the last line break
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
