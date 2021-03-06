  
package finalprojectwinter;

import java.io.Serializable;
import java.util.Random;

public class SortAlgorithms implements Serializable{
	//AMM 
	//testing commit
	private static final long serialVersionUID = 1L;
	protected int[] sortArray;
	private int mergeOps;
	private int bubbleOps;
	private int quickOps;
	private int selecOps;
	private int N;
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
		setN();
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
	
	public void setN() {
		N = sortArray.length;
	}
	
	public int getN() {
		return N;
	}
}