package finalprojectwinter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LineGraph extends Panel{
	
	public LineGraph() {
		JLabel titleLabel = new JLabel();
		titleLabel.setText("Line Graph Panel");
		this.add(titleLabel);
		
	}
	
	
	//We should just be able to edit our graphics in these methods directly
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int panelHeight = GUI.getPanelHeight(this);
		int panelWidth = GUI.getPanelWidth(this);
		
		int graphRectSide = (int) (panelHeight * 0.8);
		int graphRectYCoord = (panelHeight - graphRectSide) / 2;
		int graphRectXCoord = (panelWidth - graphRectSide) / 2;
		
		g.setColor(Color.BLACK);
		g.drawRect(graphRectXCoord, graphRectYCoord, graphRectSide, graphRectSide);
		g.setColor(Color.WHITE);
		g.fillRect(graphRectXCoord, graphRectYCoord, graphRectSide, graphRectSide);
		
	}
	

}
