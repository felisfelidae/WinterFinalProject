package finalprojectwinter;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class Histogram extends JPanel {
	public Histogram(int mergeOps, int bubbleOps, int quickOps) {
		super(new GridLayout(3,2));  //3 rows, 1 column
		JLabel histLabel1, histLabel2, histLabel3;
		
		HistogramMaterials histogramVisuals = new HistogramMaterials();
		histogramVisuals.setSorts(mergeOps, bubbleOps, quickOps);
		
		histLabel1 = new JLabel("Number of Operations by Type of Sort");
		
		
		
		add(histLabel1);
		add(histogramVisuals);
		
		
	}

}
