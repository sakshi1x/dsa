import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagerGUI extends JFrame implements ActionListener {
    private JTextField taskNameField;
    private JTextField taskDurationField;
    private JTextField taskDependencyField;
    private JButton addTaskButton;
    private JTextArea taskListArea;
    private TaskManager taskManager;

    public TaskManagerGUI() {
        // Set up the GUI
        setTitle("Task Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the components
        JLabel titleLabel = new JLabel("Task Manager");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel taskNameLabel = new JLabel("Task Name:");
        taskNameField = new JTextField(20);

        JLabel taskDurationLabel = new JLabel("Task Duration:");
        taskDurationField = new JTextField(10);

        JLabel taskDependencyLabel = new JLabel("Task Dependency:");
        taskDependencyField = new JTextField(20);

        addTaskButton = new JButton("Add Task");
        addTaskButton.addActionListener(this);

        JLabel taskListLabel = new JLabel("Task List:");
        taskListArea = new JTextArea(10, 40);
        taskListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taskListArea);

        // Add the components to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(titleLabel);
        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 2));
        centerPanel.add(taskNameLabel);
        centerPanel.add(taskNameField);
        centerPanel.add(taskDurationLabel);
        centerPanel.add(taskDurationField);
        centerPanel.add(taskDependencyLabel);
        centerPanel.add(taskDependencyField);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addTaskButton);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(taskListLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(rightPanel, BorderLayout.EAST);

        // Create the task manager
        taskManager = new TaskManager();

        // Display the GUI
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTaskButton) {
            // Get the task details from the text fields
            String name = taskNameField.getText();
            int duration = Integer.parseInt(taskDurationField.getText());
            String dependency = taskDependencyField.getText();

            // Create the task and add it to the task manager
            Task task = new Task(name, duration, dependency);
            taskManager.addTask(String.valueOf(task));

            // Clear the text fields and update the task list
            taskNameField.setText("");
            taskDurationField.setText("");
            taskDependencyField.setText("");
            taskListArea.setText(taskManager.getTaskList());
        }
    }

    public static void main(String[] args) {
        new TaskManagerGUI();
    }
}