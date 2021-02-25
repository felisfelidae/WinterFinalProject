package finalprojectwinter;

import java.util.Scanner;

public class Main {
	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter length of array:");
		int userLength = scnr.nextInt();
		
		SortAlgorithms newArray = new SortAlgorithms(userLength);
		userLength -=1;
		MergeSort.mergeParse(0, userLength);
		System.out.println("Number of operations in merge sort: " + MergeSort.getMergeCount());

	}

}
