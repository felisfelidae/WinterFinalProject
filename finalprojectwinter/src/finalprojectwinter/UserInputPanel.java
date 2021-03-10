package finalprojectwinter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import moreGuis.Account;



public class UserInputPanel extends Panel{
	
	private JTextField userInput = new JTextField(10);
	private String userInputString;
	
	//I'll add action listener later so that it's value is set to the input
	//Static so it should be accessible by SortAlgorithms class
	public static long userInputResult = -1; 
	
	public UserInputPanel() {
		
		JLabel inputLabel = new JLabel();
		inputLabel.setText("INSERT ARRAY SIZE:");
		
		JButton yesToNumber = new JButton("Apply");
		yesToNumber.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
		        	{
						userInputString = userInput.getText();
						userInputResult = Long.parseLong(userInputString);
		        	}
				}
		);
		this.setBackground(Color.RED);
		
		this.add(inputLabel);
		this.add(userInput);
		this.add(yesToNumber);
		
				
	}
	
	public long getUserInput() {
		return userInputResult;
	}
		
}