package finalprojectwinter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI {
	ArrayList<SortAlgorithms> sortTracker = new ArrayList<SortAlgorithms>();
	private SortAlgorithms dummyAlgorithm = new SortAlgorithms();
	private JTextField userInput = new JTextField(10);
	private String userInputString;
	private int userInputResult;
	private JFileChooser fileChooser;

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
	
	public static int histWidth = 300;
	public static int histHeight = 350;
	
	public static Dimension histSize = new Dimension(histWidth, histHeight);
	
	public GUI() {

		sortTracker.add(dummyAlgorithm);
		fileChooser = new JFileChooser();
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
		
		panelMethod();
		/*Panel userInputPanel = new Panel();
		userInputPanel.setBackground(Color.YELLOW);
		
		JLabel inputLabel = new JLabel();
		inputLabel.setText("INSERT ARRAY SIZE:");
		JButton yesToNumber = new JButton("Apply");
		yesToNumber.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
		        	{
						userInputString = userInput.getText();
						userInputResult = Integer.parseInt(userInputString);
						SortAlgorithms newSort = new SortAlgorithms(userInputResult);
						sortTracker.add(newSort);
						System.out.print("blarg");
		        	}
				}
		);
		
		userInputPanel.add(inputLabel);
		userInputPanel.add(userInput);
		userInputPanel.add(yesToNumber);

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
		Histogram nDisplay = new Histogram(sortTracker.get(0).getMergeOps(), sortTracker.get(0).getBubbleOps(), sortTracker.get(0).getQuickOps());
		nDisplay.setPreferredSize(histSize);
		histogramPanel.add(nDisplay);

		
		baseConstraints.gridx = 0;
		baseConstraints.gridy = 2;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 3;

		
		setPanelDimensions(0.0, 0.0);
		Panel savePanel = new Panel();
		savePanel.setBackground(Color.RED);*/

			
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
	
	/*public void buttonMethod() {
		Panel userInputPanel = new Panel();
		userInputPanel.setBackground(Color.YELLOW);
		
		JLabel inputLabel = new JLabel();
		inputLabel.setText("INSERT ARRAY SIZE:");
		JButton yesToNumber = new JButton("Apply");
		yesToNumber.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
		        	{
						userInputString = userInput.getText();
						userInputResult = Integer.parseInt(userInputString);
						SortAlgorithms newSort = new SortAlgorithms(userInputResult);
						sortTracker.add(newSort);

						
		        	}
				}
		);
		
		userInputPanel.add(userInput);
		userInputPanel.add(yesToNumber);
		userInputPanel.add(inputLabel);

		baseConstraints.gridx = 0;
		baseConstraints.gridy = 1;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 1;
	}*/
	
	/*public void histogramMethod(boolean refresh) {
		Panel histogramPanel = new Panel();
		if (refresh == true) {
		Histogram nDisplay = new Histogram(sortTracker.get(0).getMergeOps(), sortTracker.get(0).getBubbleOps(), sortTracker.get(0).getQuickOps());
		nDisplay.setPreferredSize(histSize);
		histogramPanel.add(nDisplay);

		
		baseConstraints.gridx = 0;
		baseConstraints.gridy = 2;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 3;
		}
		
		else {
			frame.getContentPane().remove(histogramPanel);
			int last = sortTracker.size() - 1;
			Histogram xDisplay = new Histogram(sortTracker.get(last).getMergeOps(), sortTracker.get(last).getBubbleOps(), 
					sortTracker.get(last).getQuickOps());
			int dumbint = sortTracker.get(last).getMergeOps();
			System.out.println(dumbint);
			xDisplay.setPreferredSize(histSize);
			histogramPanel.add(xDisplay);
			frame.getContentPane().add(histogramPanel);
			frame.validate();
			

			
			baseConstraints.gridx = 0;
			baseConstraints.gridy = 2;
			
			baseConstraints.gridheight = 1;
			baseConstraints.gridwidth = 3;
		}
			
		
	}*/
	
	public void panelMethod() {
		Panel userInputPanel = new Panel();
		userInputPanel.setBackground(Color.YELLOW);
		
		JLabel inputLabel = new JLabel();
		inputLabel.setText("INSERT ARRAY SIZE:");
		JButton yesToNumber = new JButton("Apply");
		yesToNumber.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
		        	{
						userInputString = userInput.getText();
						userInput.setText(null);
						userInputResult = Integer.parseInt(userInputString);
						SortAlgorithms newSort = new SortAlgorithms(userInputResult);
						sortTracker.add(newSort);
						frame.getContentPane().removeAll();
						panelMethod();
						frame.add(BasePanel);
						frame.validate();
		        	}
				}
		);
		
		userInputPanel.add(inputLabel);
		userInputPanel.add(userInput);
		userInputPanel.add(yesToNumber);

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
		Histogram nDisplay = new Histogram(sortTracker.get(0).getMergeOps(), sortTracker.get(0).getBubbleOps(), sortTracker.get(0).getQuickOps());
		nDisplay.setPreferredSize(histSize);
		histogramPanel.add(nDisplay);

		
		baseConstraints.gridx = 0;
		baseConstraints.gridy = 2;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 3;

		
		setPanelDimensions(0.0, 0.0);
		Panel savePanel = new Panel();
		savePanel.setBackground(Color.RED);
		
		JButton saveArrays = new JButton("Save");
		saveArrays.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
		        	{
						try {
							saveFile();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		        	}
				}
		);
		JButton loadArrays = new JButton("Load");
		saveArrays.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
		        	{
						
		        	}
				}
		);
		savePanel.add(saveArrays);
		savePanel.add(loadArrays);
		
	}
	

	public void saveFile() throws FileNotFoundException
	{
		// File writing objects
		PrintWriter outputFile = new PrintWriter ("SortFile.txt");
		
		for (int i = 0; i < sortTracker.size(); ++i) {
			outputFile.println(sortTracker.get(i).sortArray);
			outputFile.println(sortTracker.get(i).getMergeOps());
			outputFile.println(sortTracker.get(i).getBubbleOps());
			outputFile.println(sortTracker.get(i).getQuickOps());
		}
		
		outputFile.close();
	}

}