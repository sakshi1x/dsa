import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProfileCreator extends JFrame implements ActionListener {

    // Components for the user interface
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderComboBox;
    private JButton createButton;

    public ProfileCreator() {
        // Set up the window
        setTitle("Profile Creator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the components for the user interface
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(3);
        JLabel genderLabel = new JLabel("Gender:");
        String[] genders = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<String>(genders);
        createButton = new JButton("Create Profile");

        // Add the components to the window
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        panel.add(nameLabel, c);
        c.gridx = 1;
        panel.add(nameField, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(ageLabel, c);
        c.gridx = 1;
        panel.add(ageField, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(genderLabel, c);
        c.gridx = 1;
        panel.add(genderComboBox, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(createButton, c);
        add(panel);

        // Add action listeners to the button
        createButton.addActionListener(this);

        // Display the window
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Create a new user profile
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = (String)genderComboBox.getSelectedItem();
        userprofile profile = new userprofile(name, age, gender);

        // Display the profile
        JOptionPane.showMessageDialog(this, profile.toString());
    }

    public static void main(String[] args) {
        new ProfileCreator();
    }
}