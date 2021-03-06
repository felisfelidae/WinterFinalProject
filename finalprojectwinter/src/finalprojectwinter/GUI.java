package finalprojectwinter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

	//The title is a work in progress
	public static JFrame frame = new JFrame("The Mondrian Monstrosity");
	
	//BasePanel (which holds the other panels), its layout and constraints.
	//Made static to be accessed by GraphicsParent
	public static JPanel BasePanel = new JPanel();
	public static GridBagLayout BasePanelLayout = new GridBagLayout();
	public static GridBagConstraints baseConstraints = new GridBagConstraints();
	
	
	//We can change frame's Width and Height here
	public static int frameWidth = 600;
	public static int frameHeight = 500;
	
	public static Dimension frameSize = new Dimension(frameWidth, frameHeight);
	
	public GUI() {

		
		//Notes: weight x and y determine how the panels
		
		BasePanel.setLayout(BasePanelLayout);
		BasePanel.setBackground(Color.BLACK); //colors just for contrast, they should be changed soon
	
		baseConstraints.weightx = 0.5; //determines left/right pull when frame is resized
		baseConstraints.weighty = 0.5; //determines horizontal/verticla pull when frame is resized

		baseConstraints.gridx = 0; //grid x is 0 out of 3, far left
		baseConstraints.gridy = 0; //grid y set to 0 to position at top
		
		baseConstraints.insets = new Insets(5, 5, 5, 5); //insets used for all panels so there's room between them
		
		baseConstraints.gridheight = 1; //a height of 1 out of three cells
		baseConstraints.gridwidth = 3; //a width of all 3 cells
		
		baseConstraints.fill = GridBagConstraints.BOTH; //resizes panels horizontally and vertically
		
		//Panel class constructor adds panel to BasePanel, BasePanelLayout, etc. when instantiated
		UserInputPanel userInputPanel = new UserInputPanel();
		userInputPanel.setBackground(Color.YELLOW);

		baseConstraints.gridx = 0;
		baseConstraints.gridy = 1;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 1;

		
		setPanelDimensions(0.5, 1.0);
		LineGraph lineGraphPanel = new LineGraph();
		lineGraphPanel.setBackground(Color.BLUE);
	
		
		baseConstraints.gridx = 1;
		baseConstraints.gridy = 1;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 1;
		baseConstraints.gridwidth = GridBagConstraints.RELATIVE;


		
		Panel histogramPanel = new Panel();
		histogramPanel.setBackground(Color.WHITE);

		
		baseConstraints.gridx = 0;
		baseConstraints.gridy = 2;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 3;

		
		setPanelDimensions(0.0, 0.0);
		Panel savePanel = new Panel();
		savePanel.setBackground(Color.RED);
		

			
		//BasePanel added after other panels instances have been created (which are added to
		//BasePanel as part of Panel's constructor)
		frame.add(BasePanel);
		
		//Pack it
		frame.pack();
		
		//Set frame to the field frameSize
		frame.setSize(frameSize);
		
		
		//Window will appear in monitor center
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

		
	}
	
	//Panel Height and Width get methods, so they can be used to center graphic object layouts
	public static int getPanelHeight(Panel panel) {
		
		return panel.getHeight();
		
	}
	
	public static int getPanelWidth(Panel panel) {
		
		return panel.getWidth();
	
	}
	
	
	// set size of the panel as portions, i.e. 0.5 for 50% of the frame
	
	// NOTE: I thought that ipadx/ipady and frame use the same measurements, but a system.out.println
	// revealed it's off. Not sure why. Method still makes adjustment easy
	
	public void setPanelDimensions(double widthFramePortion, double heightFramePortion) {
		int pixelWidth = (int) (frameWidth * widthFramePortion);
		baseConstraints.ipadx = pixelWidth / 2;
		
		int pixelHeight = (int) (frameHeight * heightFramePortion);
		baseConstraints.ipady = pixelHeight / 2;
	}

}
