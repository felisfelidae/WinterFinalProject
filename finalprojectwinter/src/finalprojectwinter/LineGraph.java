package finalprojectwinter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.AlphaComposite;
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
	
	//fields for plotting big o  graphs 
	
	//this should be changed not sure if we're doing 1k or 10k
	int tempMaxArrayInputSize = 100;
	int plotPointFrequency = tempMaxArrayInputSize;
	int coordinatesArraySize;
	
	int[] nSquaredXCoordinates;
	int[] nSquaredYCoordinates;
	
	int[] nLognXCoordinates;
	int[] nLognYCoordinates;
	
	int inputArraySize = 300;
	
	int mergeSortResult = 300;
	int quickSortResult = 300;
	int selectionSortResult = 300;
	int bubbleSortResult = 300;
	
	Color background = (Color.WHITE);
	
	Color bigOnSquaredColor = background;
	Color bigOnLognColor = background;
	
	Color mergeSortPointColor = background;
	Color quickSortPointColor = background;
	Color selectionSortPointColor = background;
	Color bubbleSortPointColor = background;
	
	private float alpha = 0.7f ;
			

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
				RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHints(rh);
	    
	    AlphaComposite transparent = AlphaComposite.getInstance(
	            AlphaComposite.SRC_OVER, alpha);
	    
		
		panelHeight = GUI.getPanelHeight(this);
		panelWidth = GUI.getPanelWidth(this);
		
		graphRectSide = (int) (panelHeight * 0.8);
		graphRectYCoord = (panelHeight - graphRectSide) / 2;
		graphRectXCoord = (panelWidth - graphRectSide) / 2;
		
		double graphRectSideAsDouble = (double) graphRectSide;
		scaleXAxis = graphRectSideAsDouble/tempMaxArrayInputSize;
		scaleYAxis = graphRectSideAsDouble/square(tempMaxArrayInputSize);
		
		g2d.setColor(background);
		g2d.fillRect(graphRectXCoord, graphRectYCoord, graphRectSide, graphRectSide);
		
		
		//setting elements on graph as transparent
		g2d.setComposite(transparent);
		
		g2d.setStroke(new BasicStroke(3));

		g2d.setColor(bigOnSquaredColor);
		create_BigO_of_nSquared_Coordinates(tempMaxArrayInputSize);
		g2d.drawPolyline(nSquaredXCoordinates, nSquaredYCoordinates, coordinatesArraySize);
		
		g2d.setColor(bigOnLognColor);
		create_BigO_of_nLogn_Coordinates(tempMaxArrayInputSize);
		g2d.drawPolyline(nLognXCoordinates, nLognYCoordinates, coordinatesArraySize);
		
		int plotPointDiameter = 15;
		
		g2d.setColor(mergeSortPointColor);
		g2d.fillOval(inputArraySize, mergeSortResult, plotPointDiameter, plotPointDiameter);
		
		g2d.setColor(quickSortPointColor);
		g2d.fillOval(inputArraySize, quickSortResult, plotPointDiameter, plotPointDiameter);
		
		g2d.setColor(quickSortPointColor);
		g2d.fillOval(inputArraySize, selectionSortResult, plotPointDiameter, plotPointDiameter);
		
		g2d.setColor(bubbleSortPointColor);
		g2d.fillOval(inputArraySize, bubbleSortResult, plotPointDiameter, plotPointDiameter);
	
		
	}
	//method to square n for graphing Big O
	private int square(int number) {	
		int squaredNumber = number * number;
		return squaredNumber;
			
	}	
	
	//creates an arrays for x and y coordinates to graph big o of n squared polyline
	private void create_BigO_of_nSquared_Coordinates(int maximumInputArraySize) {
		
		coordinatesArraySize = maximumInputArraySize;
		
		nSquaredXCoordinates = new int[coordinatesArraySize];
		nSquaredYCoordinates = new int[coordinatesArraySize];
		
		for(int i = 0; i < coordinatesArraySize; i++) {
			nSquaredXCoordinates[i] = i;
			nSquaredYCoordinates[i] = square(nSquaredXCoordinates[i]);
			
			//scale each coordinate
			nSquaredXCoordinates[i] = adjustXForGraphing(nSquaredXCoordinates[i]);
			
			nSquaredYCoordinates[i] = adjustYForGraphing(nSquaredYCoordinates[i]);	
		
		}
		
	}
	
	
	//creates an arrays for x and y coordinates to graph big o of n log n polyline
	private void create_BigO_of_nLogn_Coordinates(int maximumInputArraySize) {
		
		coordinatesArraySize = maximumInputArraySize;
		
		nLognXCoordinates = new int[coordinatesArraySize];
		nLognYCoordinates = new int[coordinatesArraySize];
		
		for(int i = 0; i < coordinatesArraySize; i++) {
			nLognXCoordinates[i] = i;
			
			//one issue might be where the double and ints are cast
			//tried this with math.log and math.log10
			nLognYCoordinates[i] = (int) (nSquaredXCoordinates[i] * Math.log10((double) (nSquaredXCoordinates[i]) ) );
			
			//for testing
			//System.out.println(nLognXCoordinates[i] + ", " + nLognYCoordinates[i]);
			
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
	
	public void plotResults(int userResultArraySize, int mergeSortResults, int quickSortResults, int selectionSortResults, int bubbleSortResults) {
		inputArraySize = adjustXForGraphing(userResultArraySize);
		mergeSortResult = adjustYForGraphing(mergeSortResults);
		quickSortResult = adjustYForGraphing(quickSortResults);
		selectionSortResult = adjustYForGraphing(selectionSortResults);
		bubbleSortResult = adjustYForGraphing(selectionSortResults);
		
		mergeSortPointColor = Histogram.mergeColor;
		quickSortPointColor = Histogram.quickColor;
		selectionSortPointColor = Histogram.selecColor;
		bubbleSortPointColor = Histogram.bubbleColor;
		
		bigOnLognColor = Color.GRAY;
		bigOnSquaredColor = Color.GRAY;
		
	}

}
