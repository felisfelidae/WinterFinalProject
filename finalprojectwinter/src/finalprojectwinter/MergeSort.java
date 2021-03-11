package finalprojectwinter;

public class MergeSort {
	//AVERY	

	private static int countOperations = 0;
	
	/*recursive method which splits the array in half until it has only single unit sized fractions, and then calls mergeSort to zip those units
	back together*/
	public void mergeParse(int left, int right, int[] sortArray) {
		//backs out of the recursion if you've gotten down to a single number so the previous iteration will move on to the next sort or merge
		countOperations +=1;
		if (left >= right) {
			countOperations += 1;
			//one count for the left/right comparison, one for the return
			return;
		}
		
		//creates a halfway point between the two ends of the array so that the mergeParse can be recursively called with one half each
		int med = (left + right - 1)/2;
		countOperations += 3;
		//recursively calls the mergeParse on the left until it hits the return statement condition
		countOperations +=1;
		mergeParse(left, med, sortArray);
		//recursively calls the mergeParse the right of center until it hits the return statement condition
		countOperations +=1;
		mergeParse(med + 1, right, sortArray);
		//actually sorts the two fractions of the array that form the left and right boundaries for whichever fraction of the array
		countOperations +=1;
		mergeSort(left, med, right, sortArray);
	}
	
	/*function to sort the fractional array pieces defined in mergeParse (no new arrays were actually created, but rather boundaries for sections
	of the array were defined by integer limits) from smallest number to highest)*/
	public static void mergeSort(int left, int middle, int right, int[] sortArray) {
		//sets the size of the temporary arrays to hold the ordered values of arr[]
		int size1 = middle - left + 1;
		countOperations += 1;
		int size2 = right - middle;
		countOperations += 1;
		int[] temp1 = new int[size1];
		countOperations += 1;
		int[] temp2 = new int[size2];
		countOperations +=1;
		
		//assigns temp indices to the values of arr
		for (int i=0; i < size1; ++i) {
			countOperations += 2;
			temp1[i] = sortArray[left + i];
			countOperations += 2;
		}
		for (int i=0; i < size2; ++i) {
			countOperations += 2;
			temp2[i] = sortArray[middle + 1 + i];
			countOperations += 2;
		}
		
		int i = 0;
		int j = 0;
		int k = left;
		countOperations += 3;
		
		//checks that neither i nor j have exceeded their bounds
		while (i < size1 && j < size2) {
			countOperations += 2;
			//assigns i to k if temp1[i] is less than or equal to temp2[j], then increments i
			if (temp1[i] <= temp2[j]) {
				countOperations +=1;
				sortArray[k] = temp1[i];
				++i;
				countOperations += 2;
			}
			//assigns j to k if temp1[i] is greater than temp2[j], then increments j
			else {
				sortArray[k] = temp2[j];
				++j;
				countOperations += 2;
			}
			//increments k, so that the next iteration of the while loop won't write over the previous value
			//i and j are only selectively incremented so that a value of temp1[i]>temp2[j] can be checked for being less than temp2[j+1]
			++k;
			countOperations +=1;
		}
		
		//fills in any remaining values not already added to arr[]
		//if all of the temp2 values were greater than temp1 values, they still need to be added and vice versa
		while (i < size1) {
			sortArray[k] = temp1[i];
			++i;
			++k;
			countOperations += 4;
		}
		
		while (j < size2) {
			sortArray[k] = temp2[j];
			++j;
			++k;
			countOperations +=4;
		}
	}
	
	public int getMergeCount() {
		int returnInt = countOperations;
		countOperations = 0;
		return returnInt;
		
	}

}