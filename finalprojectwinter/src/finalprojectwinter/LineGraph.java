package finalprojectwinter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;


public class LineGraph extends Panel{
	
	//panel dimensions, required for relocating coordinates
	int panelHeight;
	int panelWidth;
	
	int graphRectYCoord;
	int graphRectXCoord;
	
	int graphRectSide;
	
	double scaleXAxis;
	double scaleYAxis;
	
	//fields for plotting big o graphs
	
	//this should be replaced with whatever the user input result is
	int tempMaxArrayInputSize = 100;
	int plotPointFrequency = (int) (tempMaxArrayInputSize * 0.1);
	int coordinatesArraySize;
	
	int[] nSquaredXCoordinates;
	int[] nSquaredYCoordinates;
	
	int[] nLognXCoordinates;
	int[] nLognYCoordinates;

	public LineGraph() {
		JLabel titleLabel = new JLabel();
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setText("Line Graph Panel");
		this.add(titleLabel);
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		//this was supposed to be to smooth out the lines, but I couldn't get it working
		super.paintComponent(g2d);
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	    g2d.setRenderingHints(rh);
		
		panelHeight = GUI.getPanelHeight(this);
		panelWidth = GUI.getPanelWidth(this);
		
		graphRectSide = (int) (panelHeight * 0.8);
		graphRectYCoord = (panelHeight - graphRectSide) / 2;
		graphRectXCoord = (panelWidth - graphRectSide) / 2;
		
		double graphRectSideAsDouble = (double) graphRectSide;
		scaleXAxis = graphRectSideAsDouble/tempMaxArrayInputSize;
		scaleYAxis = graphRectSideAsDouble/square(tempMaxArrayInputSize);
		
		g2d.setColor(new Color(255, 234, 186));
		g2d.fillRect(graphRectXCoord, graphRectYCoord, graphRectSide, graphRectSide);
		
		g2d.setColor(new Color(93, 201, 154));
		g2d.setStroke(new BasicStroke(2));
		
		create_BigO_of_nSquared_Coordinates(tempMaxArrayInputSize);
		g2d.drawPolyline(nSquaredXCoordinates, nSquaredYCoordinates, coordinatesArraySize);
		
		create_BigO_of_nLogn_Coordinates(tempMaxArrayInputSize);
		g2d.drawPolyline(nLognXCoordinates, nLognYCoordinates, coordinatesArraySize);
		
	}
	//method to square n for graphing Big O
	private int square(int number) {	
		int squaredNumber = number * number;
		return squaredNumber;
			
	}	
	
	//creates an arrays for x and y coordinates to graph big o of n squared polyline
	private void create_BigO_of_nSquared_Coordinates(int maximumInputArraySize) {
		
		coordinatesArraySize = (int)maximumInputArraySize/plotPointFrequency;
		
		nSquaredXCoordinates = new int[coordinatesArraySize];
		nSquaredYCoordinates = new int[coordinatesArraySize];
		
		for(int i = 0; i < coordinatesArraySize; i++) {
			nSquaredXCoordinates[i] = plotPointFrequency * i;
			nSquaredYCoordinates[i] = square(nSquaredXCoordinates[i]);
			
			//scale each coordinate
			nSquaredXCoordinates[i] = adjustXForGraphing(nSquaredXCoordinates[i]);
			
			nSquaredYCoordinates[i] = adjustYForGraphing(nSquaredYCoordinates[i]);	
		
		}
		
	}
	
	
	//creates an arrays for x and y coordinates to graph big o of n log n polyline
	private void create_BigO_of_nLogn_Coordinates(int maximumInputArraySize) {
		
		coordinatesArraySize = (int) (maximumInputArraySize/plotPointFrequency);
		
		nLognXCoordinates = new int[coordinatesArraySize];
		nLognYCoordinates = new int[coordinatesArraySize];
		
		for(int i = 0; i < coordinatesArraySize; i++) {
			nLognXCoordinates[i] = plotPointFrequency * i;
			
			//one issue might be where the double and ints are cast
			//tried this with math.log and math.log10
			nLognYCoordinates[i] = (int) (nSquaredXCoordinates[i] * Math.log10((double) (nSquaredXCoordinates[i]) ) );
			
			//for testing
			System.out.println(nLognXCoordinates[i] + ", " + nLognYCoordinates[i]);
			
			nLognXCoordinates[i] = adjustXForGraphing(nLognXCoordinates[i]);
			nLognYCoordinates[i] = adjustYForGraphing(nLognYCoordinates[i]);
			
			}
		}

	//scaling and settting coordinates to line up with graph's rectangle
	private int adjustXForGraphing(int xValue) {
		int xOriginPoint = graphRectXCoord;
		int xCoordinate = xOriginPoint + (int) (xValue * scaleXAxis);
		
		return xCoordinate;
	}
	
	
	//scaling and settting coordinates to line up with graph's rectangle
	private int adjustYForGraphing(int yValue) {
		int yOriginPoint = panelHeight - graphRectYCoord;
		int yCoordinate = yOriginPoint - (int) (yValue * scaleYAxis);
		
		return yCoordinate;
	}

}
