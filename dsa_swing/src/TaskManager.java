import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private List<Task> tasks;
    private Map<Integer, Task> taskMap;
    private int taskIdCounter;

    public TaskManager() {
        tasks = new ArrayList<>();
        taskMap = new HashMap<>();
        taskIdCounter = 1;
    }

    public Task addTask(String name) {
        Task task = new Task(name, taskIdCounter, name);
        tasks.add(task);
        taskMap.put(taskIdCounter, task);
        taskIdCounter++;
        return task;
    }

    public void removeTask(int id) {
        Task task = taskMap.get(id);
        if (task != null) {
            taskMap.remove(id);
            tasks.remove(task);
            for (Task t : tasks) {
                t.removeDependency(task);
            }
        }
    }

    public Task getTask(int id) {
        return taskMap.get(id);
    }

    public void executeTasks() {
        for (Task task : tasks) {
            if (!task.isExecuted()) {
                task.execute();
            }
        }
    }

    public String getTaskList() {
        return null;
    }
}