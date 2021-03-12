  
package finalprojectwinter;

import java.util.Random;

public class SortAlgorithms {
	
	protected int[] sortArray;
	private static int mergeOps = 1;
	private static int bubbleOps = 1;
	private static int quickOps = 1;
	private static int selecOps = 1;
	Random randomNum = new Random();
	MergeSort mergeSort = new MergeSort();
	BubbleSort bubbleSort = new BubbleSort();
	QuickSort quickSort = new QuickSort();
	SelectionSort selecSort = new SelectionSort();
	
	
	SortAlgorithms(){
		
	}
	
	//creates a new random array, aggregates all the sorts, and gets the operation information
	SortAlgorithms(int length) {
		createSortArray(length);
		fillSortArray(length);
		mergeSort.mergeParse(0, (length -1), sortArray);
		setMergeOps();
		bubbleSort.bubbleSort(sortArray);
		setBubbleOps();
		quickSort.quicksort(sortArray, 0, (length -1));
		setQuickOps();
		selecSort.selectron2000(sortArray);
		setSelecOps();
	}
	/*Methods to create and fill a new random array*/
	public void createSortArray(int length) {
		sortArray = new int[length];
	}
	
	public void fillSortArray(int length) {
		for (int i = 0; i < length; ++i) {
			sortArray[i] = randomNum.nextInt();
		}
	}
	
	/*Operation getters and setters*/
	public void setMergeOps() {
		mergeOps = mergeSort.getMergeCount();
	}
	
	public int getMergeOps() {
		return mergeOps;
	}
	
	public void setBubbleOps() {
		bubbleOps = bubbleSort.returnBubbleSortOperations();
	}
	
	public int getBubbleOps() {
		return bubbleOps;
	}
	
	public void setQuickOps() {
		quickOps = quickSort.returnQuickSortOperations();
	}
	
	public int getQuickOps() {
		return quickOps;
	}
	public void setSelecOps() {
		selecOps = selecSort.returnSelectionSortOperations();
	}
	
	public int getSelecOps() {
		return selecOps;
	}


}