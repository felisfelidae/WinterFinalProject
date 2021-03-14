package finalprojectwinter;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	//This might seems a little useless, but reduces some of the redundancy of the code
	
	public Panel() {
		
		GUI.BasePanel.add(this);
		GUI.BasePanelLayout.addLayoutComponent(this, GUI.baseConstraints);
		GUI.BasePanelLayout.setConstraints(this, GUI.baseConstraints);

	}

}
