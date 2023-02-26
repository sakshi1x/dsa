import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PipeLine {
    private List<Task> tasks;
    private HashMap<Task, Object> inputMap;
    private HashMap<Task, Object> outputMap;

    public PipeLine() {
        tasks = new ArrayList<Task>();
        inputMap = new HashMap<Task, Object>();
        outputMap = new HashMap<Task, Object>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setInput(Task task, Object input) {
        inputMap.put(task, input);
    }

    public Object getOutput(Task task) {
        return outputMap.get(task);
    }

    public void execute() {
        for (Task task : tasks) {
            Object input = inputMap.get(task);
            Object output = task.execute();
            outputMap.put(task, output);
        }
    }
}