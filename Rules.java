package Quiz_Application_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {

    JButton startButton, backButton;
    String username;

    public Rules(String username) {
        this.username = username;

        // Heading label
        JLabel headingLabel = new JLabel("The Java Puzzles — Quiz Rules");
        headingLabel.setBounds(120, 20, 700, 45);
        headingLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 37));
        headingLabel.setForeground(new Color(30, 140, 254));

        // Rules label
        JLabel rulesLabel = new JLabel();
        rulesLabel.setBounds(20, 90, 700, 350);
        rulesLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rulesLabel.setForeground(new Color(30, 30, 31));
        rulesLabel.setText(
                "<html>" +
                        "1. Topics Covered: The quiz will test your knowledge of Java OOPs concepts and Java GUI programming." + "<br><br>" +
                        "2. Question Format: Multiple Choice Questions (MCQs)." + "<br><br>" +
                        "3. Time Limit: You will have a limited time to complete the quiz (15 Sseconds)." + "<br><br>" +
                        "4. Scoring: +1 point for each correct answer. No negative marking." + "<br><br>" +
                        "5. One Attempt: You can attempt each question only once. You cannot change your answer after submission." + "<br><br>" +
                        "6. Results: Your score will be shown at the end of the quiz." + "<br><br>" +
                        "<html>"
        );

        // Start button
        startButton = new JButton("Start");
        startButton.setBackground(new Color(30, 144, 255)); // Dodger blue
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBounds(220, 480, 160, 40); // Left of center
        startButton.addActionListener(this);

        // Back button
        backButton = new JButton("Back");
        backButton.setBackground(new Color(30, 144, 255)); // Dodger blue
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBounds(420, 480, 160, 40); // Right of center
        backButton.addActionListener(this);


        // Frame settings
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setTitle("Rules Page");
        setResizable(false);
        setSize(800, 580);
        setLocation(300, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding components into JFrame:
        add(headingLabel);
        add(rulesLabel);
        add(backButton);
        add(startButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            dispose();
            new Quiz(username);

        }

        else if (e.getSource() == backButton){
//            setVisible(false);
            dispose();
            Login obj = new Login();
        }
    }

    public static void main(String[] args) {
        new Rules("User");
    }
}
