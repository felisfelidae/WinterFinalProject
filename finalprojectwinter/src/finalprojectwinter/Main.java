package finalprojectwinter;

public class Main {

	public static void main(String[] args) {
			

		GUI gui = new GUI();
		//new Gui
		//this lined used for texting gui.sortTracker
		System.out.println(gui.sortTracker.size());
		
		for (int i = 0; i < gui.sortTracker.size(); i++) {
		System.out.println(gui.sortTracker.get(i).getBubbleOps());
		System.out.println(gui.sortTracker.get(i).getMergeOps());
		System.out.println(gui.sortTracker.get(i).getSelecOps());
		System.out.println(gui.sortTracker.get(i).getQuickOps());
		}
	}

}