package Quiz_Application_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    // Globally defining the components:
    JButton rulesButton, exitButton;
    JTextField nameField;

    public Login() {

        // Image background
        ImageIcon i1 = new ImageIcon("src/Quiz_Application_Project/login.jpeg");
        JLabel imageLabel = new JLabel(i1);
        imageLabel.setBounds(0, 0, 600, 500);

        // Heading label
        JLabel headingLabel = new JLabel("The Java Puzzles");
        headingLabel.setBounds(800, 60, 300, 45);
        headingLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        headingLabel.setForeground(new Color(30, 140, 254));

        // Name label
        JLabel nameLabel = new JLabel("Enter Your Name: ");
        nameLabel.setBounds(850, 200, 350, 20);
        nameLabel.setFont(new Font("Futura", Font.PLAIN, 20));
        nameLabel.setForeground(new Color(30, 30, 31));

        // Name text field
        nameField = new JTextField();
        nameField.setBounds(780, 225, 310, 30);
        nameField.setFont(new Font("Futura", Font.BOLD, 20));
        nameField.setBackground(new Color(245, 245, 245));  // Light gray background

        // Rules button
        rulesButton = new JButton("Rules");
        rulesButton.setBounds(780, 330, 120, 25);
        rulesButton.setBackground(new Color(30, 144, 254));
        rulesButton.setForeground(Color.WHITE);
        rulesButton.addActionListener(this);

        // Back button
        exitButton = new JButton("Exit");
        exitButton.setBounds(995, 330, 120, 25);
        exitButton.setBackground(new Color(30, 144, 254));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(this);

        // Frame settings
        setTitle("Login");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setResizable(false);
        setSize(1200, 500);
        setLocation(60, 90);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding components into JFrame:
        add(imageLabel);
        add(headingLabel);
        add(nameLabel);
        add(nameField);
        add(rulesButton);
        add(exitButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rulesButton) {
            String username = nameField.getText();
            dispose();
            Rules obj = new Rules(username);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
