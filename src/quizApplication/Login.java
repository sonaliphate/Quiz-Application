package quizApplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener {
	//Database Credentials
			static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
			static final String DB_URL = "jdbc:mysql://localhost:3306/quizapp";
			static final String USER = "root";
			static final String PASS = "root";
			
			
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel image, heading, name, age;
    private JTextField nameField, ageField;
    private JButton rulesButton, backButton;

    public Login() {
        setTitle("Quiz Application");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Load and add image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));
        image = new JLabel(i1);
        image.setBounds(0, 0, 500, 600);
      add(image);

        // Add heading
        heading = new JLabel("Quiz Game !");
        heading.setBounds(600, 60, 300, 50);
        heading.setFont(new Font("Algerian", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 250));
      add(heading);

        // Add name label
        name = new JLabel("Enter Your Name");
        name.setBounds(650, 150, 300, 40);
        name.setFont(new Font("SansSerif", Font.ITALIC, 20));
        name.setForeground(new Color(30, 144, 250));
        add(name);

        // Add name field
        nameField = new JTextField();
        nameField.setBounds(600, 200, 300, 25);
        nameField.setFont(new Font("Palatino", Font.PLAIN, 16));
      add(nameField);

      // Add name label
      age= new JLabel("Enter Your Age");
      age.setBounds(650, 250, 300, 40);
      age.setFont(new Font("SansSerif", Font.ITALIC, 20));
      age.setForeground(new Color(30, 144, 250));
      add(age);
      

      // Add name field
      ageField = new JTextField();
      ageField.setBounds(600, 300, 300, 25);
      ageField.setFont(new Font("Palatino", Font.PLAIN, 16));
    add(ageField);
      
        // Add Rules button
        rulesButton = new JButton("Rules");
        rulesButton.setBounds(600, 350, 120, 25);
        rulesButton.setBackground(new Color(30, 144, 250));
        rulesButton.setForeground(Color.WHITE);
        rulesButton.addActionListener(this);
       add(rulesButton);

        // Add Back button
        backButton = new JButton("Back");
        backButton.setBounds(775, 350, 120, 25);
        backButton.setBackground(new Color(30, 144, 250));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
       add(backButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
        String ageText = ageField.getText();

        // Validate inputs
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (ageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Age field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (e.getSource() == rulesButton) {
            setVisible(false);
            new Rules(name);
        } else if (e.getSource() == backButton) {
            setVisible(false);
        }

        // Database insertion
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO quiz (name, age) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student registered successfully");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
}
