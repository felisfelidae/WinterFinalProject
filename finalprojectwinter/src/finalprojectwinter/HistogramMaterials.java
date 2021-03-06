package finalprojectwinter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class HistogramMaterials extends JComponent {
	
	@Override
	   public void paintComponent(Graphics g) {  
	      // Cast to Graphics2D
	      Graphics2D graphicsObj = (Graphics2D) g;
	      
	      Rectangle bubbleSort = new Rectangle(10, 10, 100, 50);
	      Color bubbleColor = new Color(128, 128, 0);
	      graphicsObj.setColor(bubbleColor);
	      graphicsObj.fill(bubbleSort);
	}
	

}
