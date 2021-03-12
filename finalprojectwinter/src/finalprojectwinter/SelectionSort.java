package finalprojectwinter;

public class SelectionSort {
	
	int operationsCount = 0;
	
	public void selectron2000(int [] numbers) {

		operationsCount++; // initializing i with value of 0
		for (int i = 0; i < numbers.length - 1; ++i) {
			
			// Find index of smallest remaining element
			int indexSmallest = i;
			operationsCount++; // initializing indexSmallest with value of i
			
			for (int j = i + 1; j < numbers.length; ++j) {
				operationsCount += 4;
					// 1 - finding the value of i + 1
					// 2 - initializing j with sum of i and 1
					// 3 - checking if j is less than numbers.length
					// 4 - incrementing j
				
				operationsCount++; // checking if j is less than the value of numbers[indexSmallest]
				if (numbers[j] < numbers[indexSmallest]) {
					indexSmallest = j;
					operationsCount++; // assigning value of j to indexSmallest
					
					}
				}
			// Swap numbers[i] and numbers[indexSmallest]
			int temp = numbers[i];
			numbers[i] = numbers[indexSmallest];
			numbers[indexSmallest] = temp;
			operationsCount += 3;
				// 1 - initializing temp with value of numbers[i]
				// 2 - assigning value of numbers[indexSmallest] to numbers[i]
				// 3 - assigning value of temp to numbers[indexSmallest]
			}
		}

	public int returnSelectionSortOperations() {
		int returnInt = operationsCount;
		operationsCount = 0;
		return returnInt;
	}
	
	}