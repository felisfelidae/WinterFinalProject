package finalprojectwinter;

public class MergeSort extends SortAlgorithms {
	
	private static int countOperations = 0;
	
	/*recursive method which splits the array in half until it has only single unit sized fractions, and then calls mergeSort to zip those units
	back together*/
	public static void mergeParse(int arr[], int left, int right) {
		//backs out of the recursion if you've gotten down to a single number so the previous iteration will move on to the next sort or merge
		if (left >= right) {
			countOperations += 1;
			return;
		}
		
		//creates a halfway point between the two ends of the array so that the mergeParse can be recursively called with one half each
		int med = (left + right - 1)/2;
		countOperations += 1;
		//recursively calls the mergeParse on the left until it hits the return statement condition
		mergeParse(arr, left, med);
		//recursively calls the mergeParse the right of center until it hits the return statement condition
		mergeParse(arr, med + 1, right);
		//actually sorts the two fractions of the array that form the left and right boundaries for whichever fraction of the array
		mergeSort(arr, left, med, right);
	}
	
	/*function to sort the fractional array pieces defined in mergeParse (no new arrays were actually created, but rather boundaries for sections
	of the array were defined by integer limits) from smallest number to highest)*/
	public static void mergeSort(int arr[], int left, int middle, int right) {
		//sets the size of the temporary arrays to hold the ordered values of arr[]
		int size1 = middle - left + 1;
		countOperations += 1;
		int size2 = right - middle;
		countOperations += 1;
		int[] temp1 = new int[size1];
		countOperations += 1;
		int[] temp2 = new int[size2];
		
		//assigns temp indices to the values of arr
		for (int i=0; i < size1; ++i) {
			countOperations += 1;
			countOperations +=1;
			temp1[i] = arr[left + i];
			countOperations += 1;
		}
		for (int i=0; i < size2; ++i) {
			temp2[i] = arr[middle + 1 + i];
			countOperations += 1;
		}
		
		int i = 0;
		int j = 0;
		int k = left;
		
		//checks that neither i nor j have exceeded their bounds
		while (i < size1 && j < size2) {
			//assigns i to k if temp1[i] is less than or equal to temp2[j], then increments i
			if (temp1[i] <= temp2[j]) {
				arr[k] = temp1[i];
				++i;
				countOperations += 1;
			}
			//assigns j to k if temp1[i] is greater than temp2[j], then increments j
			else {
				arr[k] = temp2[j];
				++j;
				countOperations += 1;
			}
			//increments k, so that the next iteration of the while loop won't write over the previous value
			//i and j are only selectively incremented so that a value of temp1[i]>temp2[j] can be checked for being less than temp2[j+1]
			++k;
		}
		
		//fills in any remaining values not already added to arr[]
		//if all of the temp2 values were greater than temp1 values, they still need to be added and vice versa
		while (i < size1) {
			arr[k] = temp1[i];
			++i;
			countOperations += 1;
			++k;
			countOperations += 1;
		}
		
		while (j < size2) {
			arr[k] = temp2[j];
			++j;
			countOperations +=1;
			++k;
			countOperations +=1;
		}
	}
	
	private int getMergeCount() {
		return countOperations;
	}

}
