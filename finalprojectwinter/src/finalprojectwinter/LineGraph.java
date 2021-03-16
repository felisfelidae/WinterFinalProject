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
import java.util.ArrayList;

import javax.swing.JLabel;


public class LineGraph extends Panel{
	//testing commit
	
private Graphics2D g2d;
	
	private ArrayList<SortAlgorithms> standardSortResults = new ArrayList<SortAlgorithms>();
	
	//panel dimensions, required for relocating coordinates
	int panelHeight;
	int panelWidth;
	
	int graphRectYCoord;
	int graphRectXCoord;
	
	int graphRectSide = 100;
	
	double scaleXAxis = 1;
	double scaleYAxis = 1;
	
	//fields for plotting big o  graphs 
	
	//this should be changed not sure if we're doing 1k or 10k
	int lineGraphRange = 100;
	int plotPointFrequency = 1;
	int coordinatesArraySize;
	
	int[] nSquaredXCoordinates;
	int[] nSquaredYCoordinates;
	
	int[] nLognXCoordinates;
	int[] nLognYCoordinates;
	
	int inputArraySize = 300;
	
	int largestSortResult = 100;
	
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
	
	int[] currentPlotPointDiameterArray = {0, 8};
	int[] standardResultPlotPointDiameterArray = {0, 3};

	private float percentOpacityOfCurrentPlotPoints = 0.0f ;
	private float percentOpacityOfStandardResultPlotPoints = 0.0f ;
	private float percentOpacityOfLoadedPlotPoints = 0.0f ;
	
			

	public LineGraph() {
		JLabel titleLabel = new JLabel();
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setText("Line Graph Panel");
		this.add(titleLabel);
		
	}
	
	public void paintComponent(Graphics g) {
		
		g2d = (Graphics2D) g;
		
		//this was supposed to be to smooth out the lines, but I couldn't get it working
		super.paintComponent(g2d);
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHints(rh);
	    
	    AlphaComposite opacityOfCurrentPlotPoints = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, percentOpacityOfCurrentPlotPoints);
	    AlphaComposite opacityOfStandardResultPlotPoints = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, percentOpacityOfStandardResultPlotPoints);
	    AlphaComposite opacityOfLoadedPlotPoints = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, percentOpacityOfLoadedPlotPoints);
		
		panelHeight = GUI.getPanelHeight(this);
		panelWidth = GUI.getPanelWidth(this);
		
		graphRectSide = (int) (panelHeight * 0.8);
		graphRectYCoord = (panelHeight - graphRectSide) / 2;
		graphRectXCoord = (panelWidth - graphRectSide) / 2;
		
		double graphRectSideAsDouble = (double) graphRectSide;
		scaleXAxis = graphRectSideAsDouble/lineGraphRange;
		scaleYAxis = graphRectSideAsDouble/square(lineGraphRange);
		
		int xAxisLabelxCoord = graphRectXCoord;
		int xAxisLabelyCoord = graphRectYCoord + graphRectSide;
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, panelWidth, panelHeight);
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(graphRectXCoord, graphRectYCoord, graphRectSide, graphRectSide);
		
		g2d.drawString("0", xAxisLabelxCoord - 10, xAxisLabelyCoord + 10);
		g2d.drawString("100", xAxisLabelxCoord + graphRectSide, xAxisLabelyCoord + 10);
		g2d.drawString("10,000", xAxisLabelxCoord - 20, graphRectYCoord - 5);
		
		//setting elements on graph as transparent
		g2d.setComposite(opacityOfCurrentPlotPoints);

		g2d.setStroke(new BasicStroke(3));

		g2d.setColor(bigOnSquaredColor);
		create_BigO_of_nSquared_Coordinates(lineGraphRange);
		g2d.drawPolyline(nSquaredXCoordinates, nSquaredYCoordinates, coordinatesArraySize);
		
		g2d.setColor(bigOnLognColor);
		create_BigO_of_nLogn_Coordinates(lineGraphRange);
		g2d.drawPolyline(nLognXCoordinates, nLognYCoordinates, coordinatesArraySize);
		
		int plotPointDiameter = 9;
		
		g2d.setColor(mergeSortPointColor);
		g2d.fillOval(inputArraySize, mergeSortResult, plotPointDiameter, plotPointDiameter);
		
		g2d.setColor(quickSortPointColor);
		g2d.fillOval(inputArraySize, quickSortResult, plotPointDiameter, plotPointDiameter);
		
		g2d.setColor(quickSortPointColor);
		g2d.fillOval(inputArraySize, selectionSortResult, plotPointDiameter, plotPointDiameter);
		
		g2d.setColor(bubbleSortPointColor);
		g2d.fillOval(inputArraySize, bubbleSortResult, plotPointDiameter, plotPointDiameter);
	
		g2d.setComposite(opacityOfLoadedPlotPoints);
		
		int currentPlotPointDiameter = 10;
		
		g2d.setColor(mergeSortPointColor);
		currentPlotPointDiameter = removePointBeyondBounds(inputArraySize, mergeSortResult, currentPlotPointDiameterArray);
		g2d.fillOval(inputArraySize, mergeSortResult, currentPlotPointDiameter, currentPlotPointDiameter);
		//System.out.println("MS x: " + inputArraySize + " MS y: " + mergeSortResult);
		
		g2d.setColor(quickSortPointColor);
		currentPlotPointDiameter = removePointBeyondBounds(inputArraySize, quickSortResult, currentPlotPointDiameterArray);
		g2d.fillOval(inputArraySize, quickSortResult, currentPlotPointDiameter, currentPlotPointDiameter);
		//System.out.println("QS x: " + inputArraySize + " QS y: " + quickSortResult);
		
		g2d.setColor(quickSortPointColor);
		currentPlotPointDiameter = removePointBeyondBounds(inputArraySize, selectionSortResult, currentPlotPointDiameterArray);
		g2d.fillOval(inputArraySize, selectionSortResult, currentPlotPointDiameter, currentPlotPointDiameter);
		//System.out.println("SS x: " + inputArraySize + " SS y: " + selectionSortResult);
		
		g2d.setColor(bubbleSortPointColor);
		currentPlotPointDiameter = removePointBeyondBounds(inputArraySize, bubbleSortResult, currentPlotPointDiameterArray); 
		g2d.fillOval(inputArraySize, bubbleSortResult, currentPlotPointDiameter, currentPlotPointDiameter);
		//System.out.println("BS x: " + inputArraySize + " BS y: " + bubbleSortResult);
		
		int standardResultMergeYCoord;
		int standardResultQuickYCoord;
		int standardResultSelectYCoord;
		int standardResultBubbleYCoord;
		int standardResultXCoord;
		
		
		int standardResultPointDiameter = 3;
		g2d.setComposite(opacityOfStandardResultPlotPoints);
		
		for(int i = 0; i < lineGraphRange; i++) {
			//sorting algorithm instance for input i
			SortAlgorithms newSort = new SortAlgorithms(i * plotPointFrequency);
			
			//not sure if an array is necessary
			standardSortResults.add(newSort);
			int last = standardSortResults.size() -1;
			
			standardResultXCoord = adjustXForGraphing(i);
			
			standardResultMergeYCoord = adjustYForGraphing(standardSortResults.get(last).getMergeOps());
			g2d.setColor(mergeSortPointColor);
			standardResultPointDiameter = removePointBeyondBounds(standardResultXCoord, standardResultMergeYCoord, standardResultPlotPointDiameterArray);
			g2d.fillOval(standardResultXCoord, standardResultMergeYCoord, standardResultPointDiameter, standardResultPointDiameter);
			
			standardResultQuickYCoord = adjustYForGraphing(standardSortResults.get(last).getQuickOps());
			g2d.setColor(quickSortPointColor);
			standardResultPointDiameter = removePointBeyondBounds(standardResultXCoord, standardResultQuickYCoord, standardResultPlotPointDiameterArray);
			g2d.fillOval(standardResultXCoord, standardResultQuickYCoord, standardResultPointDiameter, standardResultPointDiameter);
			
			standardResultSelectYCoord = adjustYForGraphing(standardSortResults.get(last).getSelecOps());
			g2d.setColor(quickSortPointColor);
			standardResultPointDiameter = removePointBeyondBounds(standardResultXCoord, standardResultSelectYCoord, standardResultPlotPointDiameterArray);
			g2d.fillOval(standardResultXCoord, standardResultSelectYCoord, standardResultPointDiameter, standardResultPointDiameter);
			
			standardResultBubbleYCoord = adjustYForGraphing(standardSortResults.get(last).getBubbleOps());
			g2d.setColor(bubbleSortPointColor);
			standardResultPointDiameter = removePointBeyondBounds(standardResultXCoord, standardResultBubbleYCoord, standardResultPlotPointDiameterArray);
			g2d.fillOval(standardResultXCoord, standardResultBubbleYCoord, standardResultPointDiameter, standardResultPointDiameter);
			
		}
		
		//saved points for display after reload
		g2d.setComposite(opacityOfLoadedPlotPoints);
		
		
		for(int i = 0; i < GUI.returnLoadTracker().size(); i++) {
					
					g2d.setColor(Color.BLACK);
					GUI.returnLoadTracker().get(i).setN();
					
					int xCoord = adjustXForGraphing(GUI.returnLoadTracker().get(i).getN());
					
					int mergeY = adjustYForGraphing(GUI.returnLoadTracker().get(i).getMergeOps());
					g2d.fillOval(xCoord, mergeY, 5, 5);
					
					int selectY = adjustYForGraphing(GUI.returnLoadTracker().get(i).getSelecOps());
					g2d.fillOval(xCoord, selectY, 5, 5);
					
					int quickY = adjustYForGraphing(GUI.returnLoadTracker().get(i).getQuickOps());
					g2d.fillOval(xCoord, quickY, 5, 5);
					
					int bubbleY = adjustYForGraphing(GUI.returnLoadTracker().get(i).getBubbleOps());
					g2d.fillOval(xCoord, bubbleY, 5, 5);
					
		}	
		/*checking to see of circle works
		for(int i = 0; i < 10; i++) {
			
			g2d.setColor(Color.BLACK);
			g2d.fillOval(5*i + 100, 5*i + 100, 5, 5);
		}
		*/
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
	

	public void plotResults(ArrayList<SortAlgorithms> sortTracker) {
		panelHeight = GUI.getPanelHeight(this);
		panelWidth = GUI.getPanelWidth(this);
		
		graphRectSide = (int) (panelHeight * 0.8);
		graphRectYCoord = (panelHeight - graphRectSide) / 2;
		graphRectXCoord = (panelWidth - graphRectSide) / 2;
		
		//lineGraphRange = GUI.userInputResult * 2;
		
		int last = sortTracker.size() -1;
		inputArraySize = adjustXForGraphing(sortTracker.get(last).getN());
		mergeSortResult = adjustYForGraphing(sortTracker.get(last).getMergeOps());
		quickSortResult = adjustYForGraphing(sortTracker.get(last).getQuickOps());
		selectionSortResult = adjustYForGraphing(sortTracker.get(last).getSelecOps());
		bubbleSortResult = adjustYForGraphing(sortTracker.get(last).getBubbleOps());
		
		if (GUI.userInputResult > 100) {
			plotPointFrequency = (int) (GUI.userInputResult * 0.1);
		}
		
		mergeSortPointColor = Histogram.mergeColor;
		quickSortPointColor = Histogram.quickColor;
		selectionSortPointColor = Histogram.selecColor;
		bubbleSortPointColor = Histogram.bubbleColor;
		
		bigOnLognColor = Color.BLUE;
		bigOnSquaredColor = Color.GREEN;
		
		percentOpacityOfCurrentPlotPoints = 0.7f;
		percentOpacityOfStandardResultPlotPoints = 0.5f;
		
	}
	
	public void plotPreviousResults() {
		percentOpacityOfLoadedPlotPoints = 0.7f;
			
		}
	
	public int removePointBeyondBounds(int xCoordinate, int yCoordinate, int[] diameterArray) {
		
		int diameter = 0;
		
		if(xCoordinate > (graphRectXCoord + graphRectSide) || yCoordinate < graphRectYCoord) {
			diameter = diameterArray[0];
		} else {
			diameter = diameterArray[1];
		}
		
		return diameter;
	}
		
	}