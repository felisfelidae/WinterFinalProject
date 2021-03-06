package finalprojectwinter;

import java.util.Scanner;

public class Main {
	
	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
			

		GUI gui = new GUI();
		//new Gui
		
		int[] intArray = {4, 2, 1, 7};
		
		

		// TODO Auto-generated method stub
		System.out.println("Enter length of array:");
		int userLength = scnr.nextInt();
		
		SortAlgorithms newArray = new SortAlgorithms(userLength);
		userLength -=1;
		MergeSort.mergeParse(0, userLength);

		System.out.println("Number of operations in merge sort: " + MergeSort.getMergeCount());

		System.out.println("Number of operations in merge sort: " + MergeSort.getMergeCount());

			
			BubbleSort bubbleSort = new BubbleSort();
			bubbleSort.bubbleSort(intArray);
			
			System.out.println("Total Operations: " + bubbleSort.returnBubbleSortOperations());
			for (int i = 0; i < intArray.length; i++) {
				System.out.print(intArray[i] + " ");
			}

	}

}
