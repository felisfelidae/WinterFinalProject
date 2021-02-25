package finalprojectwinter;

public class Main {

	public static void main(String[] args) {
		
		GUI gui = new GUI();
		//new Gui
		gui.setVisible(true);
		
		int[] intArray = {4, 2};
		
		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.bubbleSort(intArray);
		
		System.out.println("Total Operations: " + bubbleSort.returnBubbleSortOperations());
		for (int i = 0; i < intArray.length; i++) {
			System.out.print(intArray[i] + " ");
		}

	}

}
