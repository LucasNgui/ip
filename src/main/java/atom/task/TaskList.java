package atom.task;

import java.util.ArrayList;

/**
 * A container for a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates a <code>TaskList</code> with an
     * <code>ArrayList</code> of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param idx The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param idx The index of the task to be marked.
     */
    public void mark(int idx) {
        tasks.get(idx).mark();
    }

    /**
     * Marks a task as undone.
     *
     * @param idx The index of the task to be unmarked.
     */
    public void unmark(int idx) {
        tasks.get(idx).unmark();
    }

    /**
     * Retrieve a task from the list.
     *
     * @param idx The index of the task to be retrieved.
     * @return The retrieved task.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * @return The size of the list.
     */
    public int size() {
        return tasks.size();
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
