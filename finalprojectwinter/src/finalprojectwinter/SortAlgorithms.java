  
package finalprojectwinter;

import java.util.Random;

public class SortAlgorithms {
	
	protected static int[] sortArray;
	private static int mergeOps = 1;
	private static int bubbleOps = 1;
	private static int quickOps = 1;
	Random randomNum = new Random();
	MergeSort mergeSort = new MergeSort();
	BubbleSort bubbleSort = new BubbleSort();
	
	SortAlgorithms(){
		
	}
	
	SortAlgorithms(int length) {
		createSortArray(length);
		fillSortArray(length);
		mergeSort.mergeParse(0, (length -1), sortArray);
		setMergeOps();
		bubbleSort.bubbleSort(sortArray);
		setBubbleOps();
		setQuickOps();		
	}
	
	public void createSortArray(int length) {
		sortArray = new int[length];
	}
	
	public void fillSortArray(int length) {
		for (int i = 0; i < length; ++i) {
			sortArray[i] = randomNum.nextInt();
		}
	}
	
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
		quickOps = 5;
	}
	
	public int getQuickOps() {
		return quickOps;
	}

}