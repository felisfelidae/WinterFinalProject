package finalprojectwinter;

import java.io.Serializable;

public class BubbleSort implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//EMMA
	
	public int operationsCount = 0;
		
	public void bubbleSort(int[] sortArray){

		operationsCount++; //initializing i
			
		for (int i = 0 ; i < sortArray.length - 1; i++) {

			operationsCount+=3; 
			// 1 - subtracting 1 from numbers.length
			// 2 - comparing i to numbers.length
			// 3 - increasing i
				
			for (int j = 0; j < sortArray.length - i - 1; j++) {
					
			operationsCount += 5;
			// 1 - initializing j
			// 2 - subtracting i from numbers.length
			// 3 - subtracting 1 from numbers.length
			// 4 - comparing j to numbers.length - i - 1
			// 5 - increasing j
					
				if (sortArray[j] > sortArray[j + 1]) {
						
					operationsCount++; //comparing value of numbers[j] to numbers[j + 1]
						
					// swap numbers[j + 1] and numbers[j]
					int temp = sortArray[j];
						
					operationsCount++; // setting value of temp to numbers[j]
						
					sortArray[j] = sortArray[j + 1];
						
					operationsCount++; // setting value of numbers[j] to numbers[j + 1]
						
					sortArray[j + 1] = temp;
						
					operationsCount++; // setting value of numbers[j + 1] to value of temp
						
				} 
			}
		}
	}
		
		//"get" mehtod for operationsCount
	
		public int returnBubbleSortOperations() {
			int returnInt = operationsCount;
			operationsCount = 0;
			return returnInt;
		}

	}

