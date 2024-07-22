package quizApplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Quiz extends JFrame implements ActionListener {

    // Database Credentials
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/quizapp";
    static final String USER = "root";
    static final String PASS = "root";

    private static final long serialVersionUID = 1L;
    String questions[][] = new String[10][5];
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1];
    JRadioButton op1, op2, op3, op4;
    ButtonGroup groupoptions;
    JButton next, submit;
    JLabel queno, question;

    public static int count = 0;
    public static int score = 0;

    String name;

    public Quiz(String name) {
        this.name = name;
        setTitle("QUIZ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 0, 1100, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Load and set the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz1.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(40, 0, 900, 240);
        add(image);

        // Set question number label
        queno = new JLabel();
        queno.setBounds(100, 250, 500, 50);
        queno.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(queno);

        question = new JLabel();
        question.setBounds(120, 260, 900, 30);
        question.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(question);

        // Define questions and answers
        questions[0][0] = "Who invented Java Programming?";
        questions[0][1] = "Guido van Rossum";
        questions[0][2] = "James Gosling";
        questions[0][3] = "Dennis Ritchie";
        questions[0][4] = "Bjarne Stroustrup";

        questions[1][0] = "Which of the following is not a Java feature?";
        questions[1][1] = "Dynamic";
        questions[1][2] = "Architecture Neutral";
        questions[1][3] = "Use of pointers";
        questions[1][4] = "Object-oriented";

        questions[2][0] = "Which package contains the Random class?";
        questions[2][1] = "java.util package";
        questions[2][2] = "java.lang package";
        questions[2][3] = "java.awt package";
        questions[2][4] = "java.io package";

        questions[3][0] = "Which of the following is a valid syntax to synchronize the HashMap?";
        questions[3][1] = "Map m = hashMap.synchronizeMap();";
        questions[3][2] = "HashMap map = hashMap.synchronizeMap();";
        questions[3][3] = "Map m1 = Collections.synchronizedMap(hashMap);";
        questions[3][4] = "Map m2 = Collection.synchronizeMap(hashMap);";

        questions[4][0] = "Which of the following is true about the anonymous inner class?";
        questions[4][1] = "It has only methods";
        questions[4][2] = "Objects can't be created";
        questions[4][3] = "It has a fixed class name";
        questions[4][4] = "It has no class name";

        questions[5][0] = "What is the initial quantity of the ArrayList list?";
        questions[5][1] = "5";
        questions[5][2] = "10";
        questions[5][3] = "0";
        questions[5][4] = "10";

        questions[6][0] = "Which of the following is an immediate subclass of the Panel class?";
        questions[6][1] = "Applet class";
        questions[6][2] = "Window class";
        questions[6][3] = "Frame class";
        questions[6][4] = "Dialog class";

        questions[7][0] = "Which option is false about the final keyword?";
        questions[7][1] = "A final method cannot be overridden in its subclasses.";
        questions[7][2] = "A final class cannot be extended.";
        questions[7][3] = "A final class cannot extend other classes.";
        questions[7][4] = "A final method can be inherited.";

        questions[8][0] = "Which of the following is a marker interface?";
        questions[8][1] = "Runnable interface";
        questions[8][2] = "Remote interface";
        questions[8][3] = "Readable interface";
        questions[8][4] = "Result interface";

        questions[9][0] = "Which keyword is used for accessing the features of a package?";
        questions[9][1] = "package";
        questions[9][2] = "import";
        questions[9][3] = "extends";
        questions[9][4] = "export";

        answers[0][1] = "James Gosling";
        answers[1][1] = "Use of pointers";
        answers[2][1] = "java.util package";
        answers[3][1] = "Map m1 = Collections.synchronizedMap(hashMap);";
        answers[4][1] = "It has no class name";
        answers[5][1] = "10";
        answers[6][1] = "Applet class";
        answers[7][1] = "A final class cannot extend other classes.";
        answers[8][1] = "Remote interface";
        answers[9][1] = "import";

        // Initialize useranswers with empty strings to avoid null values
        for (int i = 0; i < useranswers.length; i++) {
            useranswers[i][0] = "";
        }

        op1 = new JRadioButton();
        op1.setBounds(120, 290, 700, 30);
        op1.setBackground(Color.WHITE);
        op1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(op1);

        op2 = new JRadioButton();
        op2.setBounds(120, 320, 700, 30);
        op2.setBackground(Color.WHITE);
        op2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(op2);

        op3 = new JRadioButton();
        op3.setBounds(120, 350, 700, 30);
        op3.setBackground(Color.WHITE);
        op3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(op3);

        op4 = new JRadioButton();
        op4.setBounds(120, 380, 700, 30);
        op4.setBackground(Color.WHITE);
        op4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(op4);

        groupoptions = new ButtonGroup();
        groupoptions.add(op1);
        groupoptions.add(op2);
        groupoptions.add(op3);
        groupoptions.add(op4);

        next = new JButton("Next");
        next.setBounds(850, 320, 200, 40);
        next.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        submit = new JButton("Submit");
        submit.setBounds(850, 390, 200, 40);
        submit.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        start(count);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            // Capture user's answer
            if (groupoptions.getSelection() != null) {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            } else {
                useranswers[count][0] = "";
            }

            // Proceed to the next question
            count++;
            if (count == 9) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }

            start(count);
        } else if (e.getSource() == submit) {
            // Capture the last answer
            if (groupoptions.getSelection() != null) {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            } else {
                useranswers[count][0] = "";
            }

            calculateScore();

            // Display the score
            JOptionPane.showMessageDialog(this, name + ", your score is: " + score + "/100");

            // Insert the score into the database
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                String sql = "INSERT INTO quiz_results (name, score) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setInt(2, score);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Score recorded successfully.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            System.exit(0);
        }
    }

    public void start(int count) {
        queno.setText(" " + (count + 1) + ".");
        question.setText(questions[count][0]);
        op1.setText(questions[count][1]);
        op2.setText(questions[count][2]);
        op3.setText(questions[count][3]);
        op4.setText(questions[count][4]);
        groupoptions.clearSelection();

        // Set action commands for radio buttons to capture the selected option
        op1.setActionCommand(op1.getText());
        op2.setActionCommand(op2.getText());
        op3.setActionCommand(op3.getText());
        op4.setActionCommand(op4.getText());
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i][0] != null && useranswers[i][0].equalsIgnoreCase(answers[i][1])) {
                score += 10;
            }
        }
    }

    public static void main(String[] args) {
        new Quiz("User");
    }
}
