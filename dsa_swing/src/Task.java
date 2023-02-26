public class Task {
    private String name;
    private boolean completed;

    public Task(String s, int taskIdCounter, String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return name;
    }

    public void removeDependency(Task task) {
    }

    public Object execute() {
        return null;
    }

    public boolean isExecuted() {
        return false;
    }
}