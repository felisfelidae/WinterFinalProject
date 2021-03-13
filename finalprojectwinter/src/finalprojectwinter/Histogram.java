package finalprojectwinter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Histogram extends JPanel {
	/** 
	 * AMM 
	 */
	private static final long serialVersionUID = 1L;
	private int mergeOps, bubbleOps, quickOps, selecOps, N;
	private String scale = "0";
	
	public Histogram(int mergeOps, int bubbleOps, int quickOps, int selecOps, int N) {
		JLabel histLabel1;
		this.N = N;
		resetSorts(mergeOps, bubbleOps, quickOps, selecOps, N);
		
		histLabel1 = new JLabel("Number of Operations by Type of Sort");
		this.add(histLabel1);
		
		
	}
	/*This code is just an if else to set operations numbers for Histogram so that they can be displayed dynamically.
	 * It has a scaling factor of *10^-x to keep the graphs within a certain pixel constraint, represented by the variable 'scale'
	 * 'N' is the length of the array*/
	public void resetSorts(int mergeOps, int bubbleOps, int quickOps, int selecOps, int N) {
		if (N < 10) {
			this.mergeOps = mergeOps;
			this.bubbleOps = bubbleOps;
			this.quickOps = quickOps;
			this.selecOps = selecOps;
			scale = "280";
		}
		else if (N < 30){
			this.mergeOps = (int) Math.ceil(mergeOps)/10;
			this.bubbleOps =(int) Math.ceil(bubbleOps)/10;
			this.quickOps = (int) Math.ceil(quickOps)/10;
			this.selecOps = (int) Math.ceil(selecOps)/10;
			scale = "2800";
		}
		else if (N < 105) {
			this.mergeOps = (int) Math.ceil(mergeOps)/100;
			this.bubbleOps =(int) Math.ceil(bubbleOps)/100;
			this.quickOps = (int) Math.ceil(quickOps)/100;
			this.selecOps = (int) Math.ceil(selecOps)/100;
			scale = "28k";
		}
		else if (N < 330) {
			this.mergeOps = (int) Math.ceil(mergeOps)/1000;
			this.bubbleOps =(int) Math.ceil(bubbleOps)/1000;
			this.quickOps = (int) Math.ceil(quickOps)/1000;
			this.selecOps = (int) Math.ceil(selecOps)/1000;
			scale = "280k";
		}
		else if (N < 1000) {
			this.mergeOps = (int) Math.ceil(mergeOps)/10000;
			this.bubbleOps =(int) Math.ceil(bubbleOps)/10000;
			this.quickOps = (int) Math.ceil(quickOps)/10000;
			this.selecOps = (int) Math.ceil(selecOps)/10000;
			scale = "2.8 million";
		}
		else if (N < 3000) {
			this.mergeOps = (int) Math.ceil(mergeOps)/100000;
			this.bubbleOps =(int) Math.ceil(bubbleOps)/100000;
			this.quickOps = (int) Math.ceil(quickOps)/100000;
			this.selecOps = (int) Math.ceil(selecOps)/100000;
			scale = "28 million";
		}
		else if (N < 10001){
			this.mergeOps = (int) Math.ceil(mergeOps)/1000000;
			this.bubbleOps =(int) Math.ceil(bubbleOps)/1000000;
			this.quickOps = (int) Math.ceil(quickOps)/1000000;
			this.selecOps = (int) Math.ceil(selecOps)/1000000;
			scale = "280 million";
		}
	}
	
	public void paintComponent(Graphics g) {  
		
	      // Cast to Graphics2D
	      Graphics2D graphicsObj = (Graphics2D) g;
	      /*All of the writing in the code. This creates the legend labels and the text beneath the histogram*/
	      graphicsObj.drawString("Bubble Sort", 120, 44);
	      graphicsObj.drawString("Merge Sort", 120, 59);
	      graphicsObj.drawString("Quick Sort", 220, 44);
	      graphicsObj.drawString("Selection Sort", 220, 59);
	      graphicsObj.drawString("0", 8, 217);
	      graphicsObj.drawString("Number of operations per sort have been scaled by", 8, 235);
	      graphicsObj.drawString("powers of 10 as they exceed current threshholds.", 8, 250);
	      graphicsObj.drawString("Array Size: " + N, 100, 280);
	      
	      /*switch case adjusting the scale displayed on the x axis*/
	      switch(scale) {
	      case "280" : graphicsObj.drawString(scale, 280, 217);
	      break;
	      case "2800" : graphicsObj.drawString(scale, 275, 217);
	      break;
	      case "28k" :graphicsObj.drawString(scale, 280, 217);
	      break;
	      case "280k" : graphicsObj.drawString(scale, 275, 217);
	      break;
	      default : graphicsObj.drawString(scale, 240, 217);
	      }
	      
	      /*container for legend*/
	      graphicsObj.drawRect(100, 30, 199, 35);
	      /*creates the constraints of the histogram - x and y axes, number tick marks*/
	      Rectangle backBoard = new Rectangle(5, 75, 1, 131);
	      Rectangle baseBoard = new Rectangle(1, 200, 300, 2);
	      Rectangle baseMarker = new Rectangle(10, 200, 1, 7);
	      Rectangle farMarker = new Rectangle(290, 200, 1, 7);
	      graphicsObj.setColor(Color.BLACK);
	      graphicsObj.fill(backBoard);
	      graphicsObj.fill(baseBoard);
	      graphicsObj.fill(baseMarker);
	      graphicsObj.fill(farMarker);
	      
	      /*These are all of the rectangles in the histogram. The color blocks in the legend are created at the same time
	       * as the histogram rectangle they represent to make the coloring easier and not accidentally color anything in wrong by changing colors
	       * a lot. Everything is grouped by what color it fills.*/
	      Rectangle bubbleSort = new Rectangle(10, 80, bubbleOps, 25);
	      Rectangle babyBubble = new Rectangle(105, 35, 10, 10);
	      Color bubbleColor = new Color(255, 105, 180);
	      graphicsObj.setColor(bubbleColor);
	      graphicsObj.fill(bubbleSort);
	      graphicsObj.fill(babyBubble);
	      
	      Rectangle mergeSort = new Rectangle(10, 110, mergeOps, 25); 
	      Rectangle babyMerge = new Rectangle(105, 50, 10, 10);
	      Color mergeColor = new Color(0, 200, 200);
	      graphicsObj.setColor(mergeColor);
	      graphicsObj.fill(mergeSort);
	      graphicsObj.fill(babyMerge);
	      
	      Rectangle quickSort = new Rectangle(10, 140, quickOps, 25);
	      Rectangle babyQuick = new Rectangle(205, 35, 10, 10);
	      Color quickColor = new Color(159, 226, 191);
	      graphicsObj.setColor(quickColor);
	      graphicsObj.fill(quickSort);
	      graphicsObj.fill(babyQuick);
	      
	      Rectangle selecSort = new Rectangle(10, 170, selecOps, 25);
	      Rectangle babySelec = new Rectangle(205, 50, 10, 10);
	      Color selecColor = new Color(190, 178, 225);
	      graphicsObj.setColor(selecColor);
	      graphicsObj.fill(selecSort);
	      graphicsObj.fill(babySelec);
	      
	}
	
	public void setN(int N) {
		this.N = N;
	}

}
