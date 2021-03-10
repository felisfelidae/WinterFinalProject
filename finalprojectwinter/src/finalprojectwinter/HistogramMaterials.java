package finalprojectwinter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class HistogramMaterials extends JComponent {
	private int mergeOps, bubbleOps, quickOps;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void setSorts(int mergeOps, int bubbleOps, int quickOps) {
		this.mergeOps = mergeOps;
		this.bubbleOps = bubbleOps;
		this.quickOps = quickOps;
	}

	@Override
	   public void paintComponent(Graphics g) {  
		
	      // Cast to Graphics2D
	      Graphics2D graphicsObj = (Graphics2D) g;
	      
	      Rectangle bubbleSort = new Rectangle(10, 0, bubbleOps, 25);
	      Color bubbleColor = new Color(255, 105, 180);
	      graphicsObj.setColor(bubbleColor);
	      graphicsObj.fill(bubbleSort);
	      
	      Rectangle mergeSort = new Rectangle(10, 35, mergeOps, 25); 
	      Color mergeColor = new Color(0, 200, 200);
	      graphicsObj.setColor(mergeColor);
	      graphicsObj.fill(mergeSort);
	      
	      Rectangle quickSort = new Rectangle(10, 75, quickOps, 25);
	      Color quickColor = new Color(159, 226, 191);
	      graphicsObj.setColor(quickColor);
	      graphicsObj.fill(quickSort);
	      
	}
	

}
