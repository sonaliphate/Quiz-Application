package quizApplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rules extends JFrame implements ActionListener {
	 private JButton backButton, startButton;

	private static final long serialVersionUID = 1L;
	

	/**
	 * Create the frame.
	
	 */
	String name;
	public Rules(String name) {
		this.name= name;
		setTitle("Rules");
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(null);
	
		  
		  JLabel heading = new JLabel("Welcome"+" " + name +" "+"to Quiz Game");
		  heading.setFont(new Font( "Algerian", Font.BOLD, 25));
		  heading.setBounds(250, 60, 400, 40);
	        heading.setForeground(new Color(30, 144, 250));
	        add(heading);
	        
	        JLabel rules = new JLabel();
	        rules.setBounds(20, 90, 700, 300);
			rules.setFont(new Font( "Cooper Black", Font.ITALIC, 15));
			rules.setText(
					"<html>" +
			" 1. The contest will consist of 10 problems to be solved in 15 seconds"+"<br><br>" +
			"2. Participation in the quiz is free and open to all persons above 18 years old.  " + "<br><br>" +
			"3. The decision of the quiz-master will be final and will not be subjected to any change." + "<br><br>" +
			"4. The questions shall be in the form of multiple choice, true/false statement, specific answer question etc."+ "<br><br>" +
			"5. 10 marks will be awarded for each correct answer "+"<br><br>" +
			"6. There is no negative marking for incorrect response." +"<br><br>"
			
					);
			add(rules);

	        
		setSize(900,600);
		setVisible(true);
		setLocation(300,100);	
		
		   backButton = new JButton("Back");
		   backButton.setBounds(300, 370, 120, 25);
		   backButton.setBackground(new Color(30, 144, 250));
		   backButton.setForeground(Color.WHITE);
		   backButton.addActionListener(this);
		  add(backButton);
		  
		  startButton = new JButton("Start");
		  startButton.setBounds(450, 370, 120, 25);
		  startButton.setBackground(new Color(30, 144, 250));
		  startButton.setForeground(Color.WHITE);
		  startButton.addActionListener(this);
	       add(startButton);
	}
public static void main(String[] args) {
		new Rules("User");
	}
@Override
public void actionPerformed(ActionEvent e) {
if(e.getSource()== startButton)	 {
	 setVisible(false);
  new Quiz(name);
}else{
	 setVisible(false);
     new Login();
	
}
}

	
}
