package Quiz_Application_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JFrame implements ActionListener {

    // Constructor
    public Score(String username, int score){
        setBounds(600, 160, 650, 450); // 550
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setTitle("The Java Puzzles Quiz");
        setLocation(350,100);

        ImageIcon i1 = new ImageIcon("src/Quiz_Application_Project/score.jpg");
        JLabel imageLabel = new JLabel(i1);
        imageLabel.setBounds(0, 120, 300, 200);

        JLabel heading = new JLabel("Thankyou " + username +  " for play The Java Puzzles ");
        heading.setBounds(45, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));

        JLabel scoreLabel = new JLabel("Your score is: " + score);
        scoreLabel.setBounds(350, 200, 300, 30);
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 25));




        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(250, 320, 150, 30);
        playAgainButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        playAgainButton.setBackground(new Color(30, 144, 255));
        playAgainButton.setForeground(Color.white);
        playAgainButton.addActionListener(this);


        JButton exitQuizButton = new JButton("Exit Quiz");
        exitQuizButton.setBounds(430, 320, 150, 30);
        exitQuizButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        exitQuizButton.setBackground(new Color(30, 144, 255));
        exitQuizButton.setForeground(Color.white);
        exitQuizButton.addActionListener(this);
        exitQuizButton.addActionListener(this);



        add(imageLabel);
        add(heading);
        add(scoreLabel);
        add(playAgainButton);



        setVisible(true);
    }



    public static void main(String[] args) {
        new Score("User", 0);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        new Login();
    }
}
