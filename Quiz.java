package Quiz_Application_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame implements ActionListener {

    String[][] questions = new String[10][5];
    String[][] answers = new String[10][2];
    String[][] userAnswers = new String[10][1];

    String username;

    JLabel questionNumLabel, questionLabel, timerLabel;
    JRadioButton optionRadioButton1, optionRadioButton2, optionRadioButton3, optionRadioButton4;
    ButtonGroup buttonGroups;
    JButton nextButton, lifelineButton, submitButton;

    public static int timer = 15;
    public static int count = 0;
    public static int score = 0;

    Timer countdownTimer;

    public Quiz(String username) {
        this.username = username;

        setBounds(50, 11, 1200, 650);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setTitle("The Java Puzzles Quiz");

        ImageIcon i1 = new ImageIcon("src/Quiz_Application_Project/quiz.jpg");
        JLabel imageLabel = new JLabel(i1);
        imageLabel.setBounds(0, 0, 1200, 300);

        questionNumLabel = new JLabel();
        questionNumLabel.setBounds(20, 340, 50, 30);
        questionNumLabel.setFont(new Font("Arial Black", Font.BOLD, 20));

        questionLabel = new JLabel();
        questionLabel.setBounds(60, 340, 900, 30);
        questionLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));

        timerLabel = new JLabel("Time Left - 15 seconds");
        timerLabel.setBounds(909, 350, 250, 30);
        timerLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        timerLabel.setForeground(Color.RED);

        // Initialize questions and answers
        loadQuestionsAndAnswers();

        optionRadioButton1 = new JRadioButton();
        optionRadioButton2 = new JRadioButton();
        optionRadioButton3 = new JRadioButton();
        optionRadioButton4 = new JRadioButton();

        JRadioButton[] options = {optionRadioButton1, optionRadioButton2, optionRadioButton3, optionRadioButton4};
        int y = 390;
        for (JRadioButton option : options) {
            option.setBounds(40, y, 600, 30);
            option.setBackground(Color.white);
            option.setFont(new Font("Dialog", Font.PLAIN, 20));
            add(option);
            y += 40;
        }

        buttonGroups = new ButtonGroup();
        buttonGroups.add(optionRadioButton1);
        buttonGroups.add(optionRadioButton2);
        buttonGroups.add(optionRadioButton3);
        buttonGroups.add(optionRadioButton4);

        nextButton = new JButton("Next");
        nextButton.setBounds(900, 390, 200, 40);
        nextButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nextButton.setBackground(new Color(30, 144, 255));
        nextButton.setForeground(Color.white);
        nextButton.addActionListener(this);

        lifelineButton = new JButton("50-50 Life-Line");
        lifelineButton.setBounds(900, 460, 200, 40);
        lifelineButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lifelineButton.setBackground(new Color(30, 144, 255));
        lifelineButton.setForeground(Color.white);
        lifelineButton.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.setBounds(900, 530, 200, 40);
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        submitButton.setBackground(new Color(30, 144, 255));
        submitButton.setForeground(Color.white);
        submitButton.setEnabled(false);
        submitButton.addActionListener(this);

        start(count);

        add(imageLabel);
        add(questionNumLabel);
        add(questionLabel);
        add(nextButton);
        add(lifelineButton);
        add(submitButton);
        add(timerLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            saveAnswer();
            count++;

            if (count == 9) {
                nextButton.setEnabled(false);
                submitButton.setEnabled(true);
            }

            start(count);

        } else if (e.getSource() == lifelineButton) {
            if (count == 2 || count == 4 || count == 6 || count == 8 || count == 9) {
                optionRadioButton2.setEnabled(false);
                optionRadioButton3.setEnabled(false);
            } else {
                optionRadioButton1.setEnabled(false);
                optionRadioButton4.setEnabled(false);
            }
            lifelineButton.setEnabled(false);

        } else if (e.getSource() == submitButton) {
            saveAnswer();
            calculateScore();
            setVisible(false);
            new Score(username, score);
        }
    }

    public void saveAnswer() {
        if (buttonGroups.getSelection() == null) {
            userAnswers[count][0] = " ";
        } else {
            userAnswers[count][0] = buttonGroups.getSelection().getActionCommand();
        }
    }

    public void calculateScore() {
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i][0] != null && userAnswers[i][0].equals(answers[i][1])) {
                score += 10;
            }
        }
    }

    public void start(int count) {
        questionNumLabel.setText(" " + (count + 1));
        questionLabel.setText(questions[count][0]);

        optionRadioButton1.setText(questions[count][1]);
        optionRadioButton1.setActionCommand(questions[count][1]);

        optionRadioButton2.setText(questions[count][2]);
        optionRadioButton2.setActionCommand(questions[count][2]);

        optionRadioButton3.setText(questions[count][3]);
        optionRadioButton3.setActionCommand(questions[count][3]);

        optionRadioButton4.setText(questions[count][4]);
        optionRadioButton4.setActionCommand(questions[count][4]);

        buttonGroups.clearSelection();
        enableAllOptions();
        startTimer();
    }

    public void enableAllOptions() {
        optionRadioButton1.setEnabled(true);
        optionRadioButton2.setEnabled(true);
        optionRadioButton3.setEnabled(true);
        optionRadioButton4.setEnabled(true);
    }

    public void startTimer() {
        timer = 15;
        timerLabel.setText("Time Left - " + timer + " seconds");

        if (countdownTimer != null) {
            countdownTimer.stop();
        }

        countdownTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer--;
                if (timer >= 0) {
                    timerLabel.setText("Time Left - " + timer + " seconds");
                } else {
                    ((Timer) e.getSource()).stop();
                    timerLabel.setText("Times up!!!");

                    saveAnswer();

                    if (count == 9) {
                        nextButton.setEnabled(false);
                        submitButton.setEnabled(true);
                        submitButton.doClick(); // Auto-submit on last question
                    } else {
                        count++;
                        start(count);
                    }
                }
            }
        });

        countdownTimer.start();
    }

    public void loadQuestionsAndAnswers() {
        questions[0][0] = "Which keyword is used to inherit a class in Java?";
        questions[0][1] = "implement";
        questions[0][2] = "extends";
        questions[0][3] = "inherits";
        questions[0][4] = "instanceof";

        questions[1][0] = "Which method is called when an object is created in Java?";
        questions[1][1] = "main()";
        questions[1][2] = "finalize()";
        questions[1][3] = "constructor";
        questions[1][4] = "static()";

        questions[2][0] = "What is the size of int variable in Java?";
        questions[2][1] = "2 bytes";
        questions[2][2] = "4 bytes";
        questions[2][3] = "8 bytes";
        questions[2][4] = "Depends on OS";

        questions[3][0] = "Which component is used to build a GUI in Java?";
        questions[3][1] = "AWT";
        questions[3][2] = "Swing";
        questions[3][3] = "JavaFX";
        questions[3][4] = "All of the above";

        questions[4][0] = "Which exception is thrown when a file is not found?";
        questions[4][1] = "IOException";
        questions[4][2] = "FileNotFoundException";
        questions[4][3] = "FileError";
        questions[4][4] = "NullPointerException";

        questions[5][0] = "Which operator is used to compare two values?";
        questions[5][1] = "=";
        questions[5][2] = "==";
        questions[5][3] = "===";
        questions[5][4] = "!=";

        questions[6][0] = "Which of the following is not a Java keyword?";
        questions[6][1] = "static";
        questions[6][2] = "Boolean";
        questions[6][3] = "void";
        questions[6][4] = "private";

        questions[7][0] = "Which method is used to start a thread in Java?";
        questions[7][1] = "init()";
        questions[7][2] = "run()";
        questions[7][3] = "start()";
        questions[7][4] = "execute()";

        questions[8][0] = "Which layout is default for JFrame?";
        questions[8][1] = "GridLayout";
        questions[8][2] = "FlowLayout";
        questions[8][3] = "BorderLayout";
        questions[8][4] = "BoxLayout";

        questions[9][0] = "What does JVM stand for?";
        questions[9][1] = "Java Variable Method";
        questions[9][2] = "Java Virtual Machine";
        questions[9][3] = "Java Verified Machine";
        questions[9][4] = "Joint Virtual Method";

        answers[0][1] = "extends";
        answers[1][1] = "constructor";
        answers[2][1] = "4 bytes";
        answers[3][1] = "All of the above";
        answers[4][1] = "FileNotFoundException";
        answers[5][1] = "==";
        answers[6][1] = "Boolean";
        answers[7][1] = "start()";
        answers[8][1] = "BorderLayout";
        answers[9][1] = "Java Virtual Machine";
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
