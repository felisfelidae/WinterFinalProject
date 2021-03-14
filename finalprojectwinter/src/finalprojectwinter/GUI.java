package finalprojectwinter;

import java.io.*;
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
	//teamwork makes the dreamwork
	ArrayList<SortAlgorithms> sortTracker = new ArrayList<SortAlgorithms>();
	private SortAlgorithms dummyAlgorithm = new SortAlgorithms();
	private JTextField userInput = new JTextField(10);
	private String userInputString;
	private int userInputResult;
	final private JFileChooser fileChooser = new JFileChooser();

	//The title is a work in progress
	public static JFrame frame = new JFrame("The Mondrian Monstrosity");
	
	//BasePanel (which holds the other panels), its layout and constraints.
	//Made static to be accessed by GraphicsParent
	public static JPanel BasePanel = new JPanel();
	public static GridBagLayout BasePanelLayout = new GridBagLayout();
	public static GridBagConstraints baseConstraints = new GridBagConstraints();
	
	
	//We can change frame's Width and Height here
	public static int frameWidth = 1200;
	public static int frameHeight = 800;
	
	public static Dimension frameSize = new Dimension(frameWidth, frameHeight);
	
	public static int histWidth = 300;
	public static int histHeight = 350;
	
	public static Dimension histSize = new Dimension(histWidth, histHeight);
	
	public GUI() {

		//DO NOT MESS WITH DUMMY ALGORITHM. it exists so the program doesn't break even before the user input is entered
		sortTracker.add(dummyAlgorithm);
		
		//Notes: weight x and y determine how the panels
		
		BasePanel.setLayout(BasePanelLayout);
		BasePanel.setBackground(Color.GRAY); //colors just for contrast, they should be changed soon
	
		baseConstraints.weightx = 0.5; //determines left/right pull when frame is resized
		baseConstraints.weighty = 0.5; //determines horizontal/verticla pull when frame is resized

		baseConstraints.gridx = 0; //grid x is 0 out of 3, far left
		baseConstraints.gridy = 0; //grid y set to 0 to position at top
		
		baseConstraints.insets = new Insets(0, 1, 0, 1); //insets used for all panels so there's room between them
		
		baseConstraints.gridheight = 1; //a height of 1 out of three cells
		baseConstraints.gridwidth = 3; //a width of all 3 cells
		
		baseConstraints.fill = GridBagConstraints.BOTH; //resizes panels horizontally and vertically
		
		//Panel class constructor adds panel to BasePanel, BasePanelLayout, etc. when instantiated
		
		Panel userInputPanel = new Panel();
		Color bgColor = new Color(173, 216, 230);
		userInputPanel.setBackground(bgColor);
		
		JLabel inputLabel = new JLabel();
		inputLabel.setText("INSERT ARRAY SIZE:");
		JLabel feedBack = new JLabel();
		
		userInputPanel.add(inputLabel);
		userInputPanel.add(userInput);

		baseConstraints.gridx = 0;
		baseConstraints.gridy = 1;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 1;

		
		setPanelDimensions(0.5, 1.0);
		LineGraph lineGraphPanel = new LineGraph();
		lineGraphPanel.setBackground(new Color(255, 194, 82));
	
		
		baseConstraints.gridx = 1;
		baseConstraints.gridy = 1;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 1;
		baseConstraints.gridwidth = GridBagConstraints.RELATIVE;

		//creates new panel and adds a new Histogram object with the most recent sort information
		Panel histogramPanel = new Panel();
		Color histColor = new Color(240, 240, 240);
		histogramPanel.setBackground(histColor);
		int last = sortTracker.size() - 1;
		Histogram nDisplay = new Histogram(sortTracker.get(last).getMergeOps(), sortTracker.get(last).getBubbleOps(), 
				sortTracker.get(last).getQuickOps(), sortTracker.get(last).getSelecOps(), 0);
		nDisplay.setPreferredSize(histSize);
		histogramPanel.add(nDisplay);

		
		baseConstraints.gridx = 0;
		baseConstraints.gridy = 2;
		
		baseConstraints.gridheight = 1;
		baseConstraints.gridwidth = 3;

		
		setPanelDimensions(0.0, 0.0);
		Panel savePanel = new Panel();
		savePanel.setBackground(bgColor);
		
		//save button w/action listener
		JButton saveArrays = new JButton("Save");
		saveArrays.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
		        	{
						int returnVal = fileChooser.showSaveDialog(BasePanel);
						if (returnVal == JFileChooser.APPROVE_OPTION)
						{
							File saveFile = fileChooser.getSelectedFile();
							try{
								FileOutputStream writeData = new FileOutputStream(saveFile + ".txt");
								ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
								
								writeStream.writeObject(sortTracker);
								writeStream.flush();
							    writeStream.close();
								}catch (IOException e1) {
								    e1.printStackTrace();
								}
							
						}
		        	}
				}
		);
		
		//FINISH
		JButton loadArrays = new JButton("Load");
		loadArrays.addActionListener(
				new ActionListener() {
					@SuppressWarnings("unchecked")
					public void actionPerformed(ActionEvent e)
		        	{
						int returnVal = fileChooser.showOpenDialog(BasePanel);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File loadFile = fileChooser.getSelectedFile();
							ArrayList<SortAlgorithms> loadTracker = new ArrayList<SortAlgorithms>();
							try{
							    FileInputStream readData = new FileInputStream(loadFile);
							    ObjectInputStream readStream = new ObjectInputStream(readData);

							    loadTracker = (ArrayList<SortAlgorithms>) readStream.readObject();
							    readStream.close();
							}catch (Exception e1) {
							    e1.printStackTrace();
							}
							//the values in the arraylist have to be re-initialized on a load. please don't ask me why. i don't know. 
							//HOWEVER, IT WORKS, and this is what matters, so i'm just looping through the saved ns to re-initialize the whole thing
							int last = loadTracker.size() -1;
							
							for (int i = 1; i <= last; ++i) {
								loadTracker.get(i).setN();
								System.out.println(loadTracker.get(i).getN());
							}
							
							for (int i = 1; i <= last; ++i) {
								int tempN = loadTracker.get(i).getN();
								SortAlgorithms tempSort = new SortAlgorithms(tempN);
								loadTracker.set(i, tempSort);
							}
							nDisplay.setN(loadTracker.get(last).getN());
							nDisplay.resetSorts(loadTracker.get(last).getMergeOps(), loadTracker.get(last).getBubbleOps(), 
							loadTracker.get(last).getQuickOps(), loadTracker.get(last).getSelecOps(), loadTracker.get(last).getN());
							histogramPanel.validate();
							histogramPanel.repaint();
						}
		        	}
				}
		);
		
		/*button to apply array length with action listener. Gets user input, converts to int, checks that user input is within parameters
		 * 1 to 10000, then refreshes the histogram display accordingly and adds the new array to the SortAlgorithms ArrayList so that 
		 * it can be saved and looked at later*/
		JButton yesToNumber = new JButton("Apply");
		yesToNumber.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e)
		        	{
						userInputString = userInput.getText();
						userInput.setText(null);
						userInputResult = Integer.parseInt(userInputString);
						if (userInputResult > 10000) {
							feedBack.setText("How much processing power do you think we have around here?");
						}
						else if (userInputResult < 1) {
							feedBack.setText("Hey! What kind of array do you think that is!?");
						}
						else {
							feedBack.setText(null);
							SortAlgorithms newSort = new SortAlgorithms(userInputResult);
							sortTracker.add(newSort);
							nDisplay.resetSorts(sortTracker.get(last).getMergeOps(), sortTracker.get(last).getBubbleOps(), 
					sortTracker.get(last).getQuickOps(), sortTracker.get(last).getSelecOps(), userInputResult);
							histogramPanel.validate();
							histogramPanel.repaint();
							
							lineGraphPanel.plotResults(userInputResult, sortTracker.get(last).getMergeOps(), sortTracker.get(last).getQuickOps(), sortTracker.get(last).getSelecOps(), sortTracker.get(last).getBubbleOps());
							lineGraphPanel.validate();
							lineGraphPanel.repaint();
						}
		        	}
				}
		);
		
		//adding number button and feedback last so they position appropriately/'hear' their components
		userInputPanel.add(yesToNumber);
		userInputPanel.add(feedBack);
		//add savepanel stuff at the bottom
		savePanel.add(saveArrays);
		savePanel.add(loadArrays);

			
		//BasePanel added after other panels instances have been created (which are added to
		//BasePanel as part of Panel's constructor)
		frame.add(BasePanel);
		
		//Pack it
		frame.pack();
		
		//Set frame to the field frameSize
		frame.setSize(frameSize);
		
		
		//Window will appear in monitor center
		//SUPER IMPORTANT TO EXIT ON CLOSE. that's why i was having memory issues
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		//this lined used for texting sortTracker
		for (int i = 0; i < sortTracker.size(); i++) {
		System.out.println(sortTracker.get(i).getBubbleOps());
		}
		

		
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