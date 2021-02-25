package finalprojectwinter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI {

	//Emma
	
	
	private JFrame registrationFrame = new JFrame("Sorting Algorithm Comparison Machine 3000");

	public GUI() {
		
	    JPanel mainPanel = new JPanel();
	    //Pete's Panel:
	    JPanel userInputPanel = new JPanel();
	    GridBagConstraints userInputConstraints = new GridBagConstraints();
	    userInputPanel.setBackground(Color.RED); //Colors added for visualization's sake
	    
	    //Avery's Panel:
	    JPanel histogramPanel = new JPanel();
	    GridBagConstraints histogramLayoutConstraints = new GridBagConstraints();
	    histogramPanel.setBackground(Color.GREEN);
	    
	    //Emma's Panel:
	    JPanel lineGraphPanel = new JPanel();
	    GridBagConstraints lineGraphLayoutConstraints = new GridBagConstraints();
	    lineGraphPanel.setBackground(Color.BLUE);

        //I changed the layout from a box layout to a border layout, because box layouts only stack
	    mainPanel.setLayout(new BorderLayout());
        
        
	    
	    //Title for line graph panel
	    JLabel userNameLabel = new JLabel("Line Graph");
        lineGraphLayoutConstraints.insets = new Insets(5, 5, 5, 5);
        lineGraphLayoutConstraints.anchor = GridBagConstraints.WEST;
        lineGraphLayoutConstraints.gridx = 0;
        lineGraphLayoutConstraints.gridy = 0;
        
        lineGraphPanel.add(userNameLabel, userInputConstraints);

        
        //Border layouts use North, East, Center, West, and South
        mainPanel.add(userInputPanel, BorderLayout.NORTH);
        mainPanel.add(histogramPanel, BorderLayout.EAST);
        mainPanel.add(lineGraphPanel, BorderLayout.WEST);
        registrationFrame.add(mainPanel);
        
        
        registrationFrame.pack();
        registrationFrame.setSize(800, 800);
        
        registrationFrame.setLocationRelativeTo(null);
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setVisible(boolean TrueFalse) { 
		registrationFrame.setVisible(TrueFalse); 
	}

}
