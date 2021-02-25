package finalprojectwinter;

public class BubbleSort extends SortAlgorithms {
	
	public int operationsCount = 0;
		
	public void bubbleSort(int [] numbers){

		operationsCount++; //initializing i
			
		for (int i = 0 ; i < numbers.length - 1; i++) {

			operationsCount+=3; 
			// 1 - subtracting 1 from numbers.length
			// 2 - comparing i to numbers.length
			// 3 - increasing i
				
			for (int j = 0; j < numbers.length - i - 1; j++) {
					
			operationsCount += 5;
			// 1 - initializing j
			// 2 - subtracting i from numbers.length
			// 3 - subtracting 1 from numbers.length
			// 4 - comparing j to numbers.length - i - 1
			// 5 - increasing j
					
				if (numbers[j] > numbers[j + 1]) {
						
					operationsCount++; //comparing value of numbers[j] to numbers[j + 1]
						
					// swap numbers[j + 1] and numbers[j]
					int temp = numbers[j];
						
					operationsCount++; // setting value of temp to numbers[j]
						
					numbers[j] = numbers[j + 1];
						
					operationsCount++; // setting value of numbers[j] to numbers[j + 1]
						
					numbers[j + 1] = temp;
						
					operationsCount++; // setting value of numbers[j + 1] to value of temp
						
				} 
			}
		}
	}
		
		//"get" mehtod for operationsCount
	
		public int returnBubbleSortOperations() {
			return operationsCount;
		}

	}

