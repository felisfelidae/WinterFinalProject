package finalprojectwinter;

import java.util.Random;

public class SortAlgorithms {
	//avery
	
	protected static int[] sortArray;
	Random randomNum = new Random();
	
	SortAlgorithms(int length) {
		createSortArray(length);
		fillSortArray(length);
	}
	
	public void createSortArray(int length) {
		sortArray = new int[length];
	}
	
	public void fillSortArray(int length) {
		for (int i = 0; i < length; ++i) {
			sortArray[i] = randomNum.nextInt();
		}
	}
	

}
