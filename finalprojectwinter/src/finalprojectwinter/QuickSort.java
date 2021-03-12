package finalprojectwinter;


public class QuickSort {
	//EOL
	
	int operationsCount = 0;
	
		   public int partition(int [] numbers, int i, int k) {
		      int l = i;
		      int h = k;
		      boolean done = false;
		      operationsCount += 3; 
		      // assigning values to
		      	// 1 - l
		      	// 2 - h
		      	// 3 - done
		      
		      int midpoint; //Questions: do these count as operations, 
		      				//and if they do, would assigning a value be an additional operation?
		      int pivot;
		      int temp;

		      /* Pick middle element as pivot */
		      midpoint = i + (k - i) / 2;
		      pivot = numbers[midpoint];
		      operationsCount += 5; 
		      // 1 - subtracting i from k, , 
		      // 2 - dividing (k - i) by 2
		      // 3 - adding i to the quotient
		      // 4 - assigning value to midpoint
		      // 5 - assigning value of numbers[midpoint] to pivot

		      
		      operationsCount++; // checking value of done
		      while (!done) {
		         /* Increment l while numbers[l] < pivot */
		    	  operationsCount++; // checking if value of numbers[l] is less than pivot
		    	  while (numbers[l] < pivot) {
		            ++l;
		            operationsCount += 1; //incrementing l
		         }

		         /* Decrement h while pivot < numbers[h] */
		    	  operationsCount++; // checking if value of numbers[h] is greater than pivot
		    	  while (pivot < numbers[h]) {
		            --h;
		            operationsCount++; // decrementing h
		         }

		         /* If there are zero or one items remaining,
		            all numbers are partitioned. Return h */
		         operationsCount++; // checking if value of l is greater than or equal to h
		         if (l >= h) {
		            done = true;
		            operationsCount++; // assigning new value to done
		         } 
		         
		         //Question: do else statements require operation (I'm assuming they wouldn't)
		         else {
		            /* Swap numbers[l] and numbers[h],
		               update l and h */
		            temp = numbers[l];
		            numbers[l] = numbers[h];
		            numbers[h] = temp;
		            ++l;
		            --h;
		            operationsCount += 5; 
		            // 1 - initializing and assigning value of number [l] to temp
		            // 2 - assigning value of numbers[h] to numbers[l]
		            // 3 - assigning value of temp to numbers[h]
		            // 4 - incrementing l
		            // 5 - decrementing h
		         }
		      }

		      operationsCount++; // returning h
		      return h;
		   }

		   public void quicksort(int [] numbers, int i, int k) {
		      
			   int j;

		      /* Base case: If there are 1 or zero entries to sort,
		       partition is already sorted */
			   
			  operationsCount++; // checking if i is greater than or equal to k
		      if (i >= k) {
		    	 operationsCount++; // returning
		         return;
		      }

		      /* Partition the data within the array. Value j returned
		         from partitioning is location of last item in low partition. */
		      j = partition(numbers, i, k);
		      operationsCount += 2;
		      // 1 - reassigning value of j
		      // 2 - calling on partition function
		      
		      /* Recursively sort low partition (i to j) and
		         high partition (j + 1 to k) */
		      quicksort(numbers, i, j);
		      quicksort(numbers, j + 1, k);
		      operationsCount += 2; // 2 function calls
		   }
		   
		   public int returnQuickSortOperations() {
			   int returnInt = operationsCount;
				operationsCount = 0;
				return returnInt;
			}

		}