package finalprojectwinter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class UserInputPanel extends Panel{
	
	private JTextField userInput = new JTextField(10);
	
	//I'll add action listener later so that it's value is set to the input
	//Static so it should be accessible by SortAlgorithms class
	public static int userInputResult = -1; 
	
	public UserInputPanel() {
		
		JLabel inputLabel = new JLabel();
		inputLabel.setText("INSERT ARRAY SIZE:");
		this.setBackground(Color.RED);
		
		this.add(inputLabel);
		this.add(userInput);
				
	}
		

}
