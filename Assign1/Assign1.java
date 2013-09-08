import java.io.File;
import java.io.IOException;
import java.util.*;



public class Assign1
{

    // Read a sequence of integers from a file into an array.
    // The first integer of the file tells the number n of integers in the
    // sequence.  The remaining n integers in the file are the sequence.
    // Each integer in the file is separated from the next by one
    // or more white-space characters (spaces or newlines).
    // The method returns an array that is allocated to have size n
    // and holds the n-integer sequence.  If there is an IO exception,
    // which can happen if the file doesn't exist or doesn't adhere
    // to the formatting requirements, the method prints an error message
    // and halts the program with status 1.
    public static int[] readIn(String fName) {
       int[] res = {};
       try {
           Scanner scan = new Scanner(new File(fName));
           int size;
           size = scan.nextInt();
           res = new int[size];
           // loop through numbers in input file and sum them
           for(int i=0; i<size; i++) {
               res[i] = scan.nextInt();
           }
       } catch (IOException E) {
           System.out.println("readIn: IOException!!");
           System.exit(1);
       }
       return res;
    }
    
    
    // Menu options 1 and 2.  SOLVED PROBLEM:  Print out elements A[0..size]
    // Precondition:  0 < size <= A.length;
    // Postcondition:  A[0], A[1], ..., A[size-1] have been printed
    //   to one line of output, each followed by a space.  The line
    //   is followed by a newline.
    public static void printArr(int A[], int size)
    {

        int counter = 0;   //How many elements of A [] have been printed

        while (counter < size)
        {
            System.out.print(A[counter] + " ");  
            counter++;   
        }

        System.out.println();  // The newline we're supposed to put at the
                               //  end of the output
    }

    // Menu option 3.  Use a for loop to print out the contents of 
    // an array
    // Print out A[0] through A[size-1] using a for loop.

    // Precondition:  0 < size <= A.length;
    // Postcondition:  A[0], A[1], ..., A[size-1] have been printed
    //   to one line of output, each followed by a space.  The line
    //   is followed by a newline.
    public static void printArrFor(int A[], int size)
    {
        for (int i=0; i < size; i++)
        {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    //  Menu option 4.  Use a do-while loop to print out the contents of 
    //   an array 
    // Print out A[0] through A[size-1] using a do-while loop.

    // Precondition:  0 < size <= A.length
    // Postcondition:  A[0], A[1], ..., A[size-1] have been printed
    //   to one line of output, each followed by a space.  The line
    //   is followed by a newline.
    public static void printArrDo (int A[], int size)
    {
        int i = 0; // Counter to go through A []
        do
        {
            System.out.print (A[i] + " ");
            i++;
        }
        while (i < size);
        System.out.println();
    }

    // Menu option 5.  Find the maximum value in an array of integers.
    // Precondition:  0 < size <= A.length;
    // Postcondition:  The method has returned the maximum
    //   value that occurs in A[0..size-1]
    public static int arrayMax(int A[], int size)
    {
       int max = A[0]; // Maximum value found in the array
       int i = 1; // Counter to go through A []
       while (i < size)
       {
        if (A[i] > max)
        {
            max = A[i];
        }
        i++;
       }
       return max;
    }

    // Menu option 6.  Find the sum of a section of an array.
    // precondition:  0 <= i <= j < A.length;
    // postcondition:  The sum of elements in A[i..j] has been returned
    public static int arraySectSum(int [] A, int i, int j)
    {
        int sum = 0; // Total sum of all elements A [] in the section that has been given as a parameter
       for (i = i; i <= j; i++)
       {
        sum += A[i];
       }
       return sum;
    }

    // Menu option 7.  Swap 2 elements of an array. 
    // Preconditions:  0 <= i <= j < A.length
    // Postconditions:  the contents of A[i] and A[j] have been
    //  swapped
    public static void swap (int A[], int i, int j)
    {
        int temp; // Temporary place holder for a value of A []
        temp = A[i];
        A[i] = A[j];
        A[j] = temp; 
    }


    // Menu option 8.  Reverse a section of an array.
    // preconditions:  0 <= i <= j < A.length;
    // postconditions:  The order of elements in A[i..j] has been reversed
    //
    // Hint:  You should make calls to 'swap' from within the method.
    public static void arrSectReverse (int [] A, int i, int j)
    {
        for (i = i; i < j; i++)
        {
            swap (A, i, j);
            j--;
        }  
    }

    // Menu option 9.  Concatinate 2 arrays.
    // Preconditions:  0 < size1 <= A1.length; 0 < size2 <= A2.length
    // Postcondition:  An array of length size1 + size2 has been allocated,
    //   Its contents are the concatenation A1[0..size1-1]A2[0..size2-1],
    //   and a reference to this array has been returned
    public static int [] concatArrs(int [] A1, int size1, int[] A2, int size2)
    {
        int [] arrayBoth = new int [size1 + size2];
        for ( int i = 0; i < size1; i++)
        {
            arrayBoth[i] = A1[i];
        }
        for (int j = 0; j < size2; j++)
        {
            arrayBoth[size1 + j] = A2[j];
        }
        return arrayBoth;
    }

    // Menu option 10.  Interleave 2 arrays.
    // Preconditions:  0 < size1 <= A1.length; 0 < size2 <= A2.length
    // Postcondition:  An array of length size1 + size2 has been allocated,
    //   If size1 == size2, its contents are 
    //   (A1[0],A2[0],A1[1],A2[1], ..., A1[size1-1],A2[size1-1])
    //   If size1 < size2, its contents are 
    //     (A1[0],A2[0],A1[1],A2[1], ..., A1[size1-1],A2[size1-1], 
    //     A2[size1],A2[size1+1], ..., A2[size2-1])
    //   If size2 < size1, its contents are
    //     (A1[0],A2[0],A1[1],A2[1], ..., A1[size2-1],A2[size2-1], 
    //     A1[size2],A1[size2+1], ..., A1[size1-1])
    //   A reference to this array has been returned
    public static int [] interleave(int [] A1, int size1, int[] A2, int size2)
    {
        int j = 0; // Counter to go through arrayBoth []
        int k = 0; // Counter to go through A []
        int [] arrayBoth = new int [size1 + size2];
        if (size1 == size2)
        {
            for (int i = 0; i <= (size1 + size2 -2); i+=2)
            {
                arrayBoth [i] = A1[j]; 
                arrayBoth [i + 1] = A2[j];
                j++;
            }
        }
        else if (size1 < size2)
        {
            while (j < size2)
            {
                for (j = j; j < size1; j++)
                {
                    arrayBoth [k] = A1 [j];
                    arrayBoth [k + 1] = A2 [j];
                    k += 2;
                }
                arrayBoth [k] = A2 [j];
                j++;
                k++;
            }
        }
        else if (size2 < size1)
        {
            while (j < size1)
            {
                for (j = j; j < size2; j++)
                {
                    arrayBoth [k] = A1 [j];
                    arrayBoth [k + 1] = A2 [j];
                    k += 2;
                }
                arrayBoth [k] = A1 [j];
                j++;
                k++;
            }
        }
        return arrayBoth; 
    }

    // Menu option 11.  Merge 2 arrays
    // preconditions:  0 < size1 <= A1.length; 0 < size2 <= A2.length;
    //   A1[0..size1-1] is in sorted order
    //   A2[0..size2-1] is in sorted order
    // Postconditions:  An array of length size1 + size2 has been allocated,
    //   its contents are the elements of A1[0..size1-1] and A2[0..size2-1]
    //   in sorted order.  A reference to this array has been returned.
    public static int [] merge(int [] A1, int size1, int [] A2, int size2)
    {
        int [] arraySorted = new int [size1 + size2];
        arraySorted = interleave(A1,size1,A2,size2);
        for (int i = 0; i < size1 + size2; i++)
        {
            for (int j = 1; j < size1 + size2; j++)
            {
                if (arraySorted [j] < arraySorted[j - 1])
                {
                    swap(arraySorted, j, j-1);
                }
            }
        }
        return arraySorted;  
    }

    // Menu option 12.  Print out the ordered pairs.
    // preconditions:  0 < size <= A.length and the elements of A[0..size-1]
    //  are distinct (no two are the same)
    // postconditions:  All ordered pairs from A[0..size-1] of the form 
    //   (A[i],A[j]) have been printed out, one per line, enclosed in 
    //    parentheses, separated by commas.  No other characters should
    //    appear on the line.  The number of pairs printed 
    //    has been counted and this count has been returned.
    public static int orderedPairs(int [] A, int size)
    {
        int count = 0; // Count of how many ordered pairs were printed
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                    System.out.println ("(" + A[i] + "," + A[j] + ")");
                    count++;
            }
        }
        return count;
    }
 
    // Menu option 13.  Print out the ordered 4-tuples
    // preconditions: 0 < size <= A.length and the elements of A[0..size-1]
    //   are all distinct
    // postconditions: All ordered 4-tuples from A[0..size-1] of the form 
    //   (A[h],A[i],A[j],A[l]) have been printed out, one per line, enclosed 
    //   in parentheses, separated by commas.  No other characters should
    //   appear on the line.  The number of these 4-tuples
    //   has been counted and this count has been returned.
    public static int ordered4Tuples (int [] A, int size)
    {
        int count = 0; // Count of how many 4-tuples were printed
        for (int h = 0; h < size; h++)
        {
           for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                    {
                        for (int l = 0; l < size; l++)
                            {
                                System.out.println("(" + A[h] + "," + A[i] + "," + A[j] + "," + A[l] + ")" );
                                count++;
                            }
                    }
            }
        }
        return count; 
    }

    // Menu option 14.  Print out the ordered pairs.
    // preconditions:  0 < size <= A.length and the elements of A[0..size-1]
    //   are all distinct
    // postconditions:  All ordered pairs from A[0..size-1] of the form 
    //   (A[i],A[j]), where A[i] != A[j] have been printed out, one per 
    //   line, enclosed in parentheses, separated by commas.  No other
    //   characters should appear on the line.  The number of pairs 
    //   printed out has been counted and the count has been returned.
    public static int orderedPairsNoRepeats(int [] A, int size)
    {
        int count = 0; // Count of how many ordered pairs were printed
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (i != j)
                {
                    System.out.println ("(" + A[i] + "," + A[j] + ")");
                    count++;
                }
            }
        }
        return count;
    }
 
    // Menu option 15.  Print out the 4-tuples that hape no repeats
    // preconditions: 0 < size <= A.length and the elements of A[0..size-1]
    //    are distinct
    // postconditions: All ordered 4-tuples from A[0..size-1] of the form 
    // (A[h],A[i],A[j],A[k]), where no two of A[h], A[i], A[j], A[k] are equal, have 
    //   been printed out, one per line, enclosed in parentheses, separated
    //   by commas.  No other characters should appear on the line.
    //   The number of 4-tuples printed has been counted and the count 
    //   has been returned 
    public static int ordered4TuplesNoRepeats(int [] A, int size)
    {
        int count = 0; // count of how many 4-tuples were printed
        for (int h = 0; h < size; h++)
        {
           for (int i = 0; i < size; i++)
                {
                    if (i!=h)
                        {
                            for (int j = 0; j < size; j++)
                                {
                                    if (j!=i && j!=h)
                                        {
                                            for (int k = 0; k < size; k++)
                                                {
                                                    if (k!=j && k!=h && k!=i)
                                                        {
                                                             System.out.println("(" + A[h] + "," + A[i] + "," + A[j] + "," + A[k] + ")" );
                                                            count++;
                                                        }
                                                }
                                        }
                                }
                        }
                }
            
        }
        return count; 
    }

    // Menu option 16.  Print out the subsets of size 2.
    // preconditions: 0 < size <= A.length and the elements of A[0..size-1]
    //    are distinct
    // postconditions:  All subsets of size two of A[0..size-1] have been 
    //   printed out once, one per line, enclosed in curly braces, separated
    //   by commas.  No other characters should appear on the line.
    //   The order in which they must appear as {A[i],A[j]} 
    //   between the curly braces is dictated by i < j.  The count of the 
    //   number of subsets printed has been returned.
    public static int twoSubsets(int [] A, int size)
    {
        int count = 0; // count of how many subsets of two were printed
        for (int i=0; i<size; i++)
            {
                for (int j=i+1; j<size; j++)
                    {
                        System.out.println("{" + A[i] + "," + A[j] + "}");
                        count++;
                     }
            }
        return count;
    }

    // Menu option 17.  Print out the subsets of size 4
    // preconditions: 0 < size <= A.length and the elements of A[0..size-1]
    //    are distinct
    // postconditions:  All subsets of size 4 of elements have been printed
    //   out once, one per line, enclosed in curly braces, separated by
    //   commas, and counted.  The order in which they must appear as 
    //   {A[h], A[i], A[j], A[k]} in the curly braces is dictated by h < i < j < k.  
    //   The count of the number of subsets printed has been returned.
    public static int fourSubsets(int [] A, int size)
     {
        int count = 0; // count of how many subsets of 4 were printed
        for (int i=0; i<size; i++)
            {
                for (int j=i+1; j<size; j++)
                    {
                        for (int k=j+1; k<size; k++)
                            {
                                for (int l=k+1; l<size; l++)
                                    {
                                        System.out.println("{" + A[i] + "," + A[j] + "," + A[k] + "," + A[l] + "}");
                                        count++;
                                    }
                            }
                    }
            }
        return count;
    }
    
    // Menu option 18. Create a 2-D array.
    // precondition:  dimension >= 1
    // postcondition:  A 2-d dimension x dimension array has been
    //   allocated.  For each row i from 0 to dimension-1, 
    //   column j from 0 to dimension -1, the element in row i,
    //   column j has been filled in with the product i*j
    //   A reference to this array has been returned
    public static int [] [] multTable(int dimension)
    {
        int [] [] array2D = new int [dimension] [dimension];
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                array2D [i] [j] = i * j;
            }
        }
        return array2D;  // Fill in code
    }
    // Menu option 18.  Print the 2-D array.
    // preconditions:  0 < rows <= A.length.  For each row i from
    //   0 through rows-1, 0 < columns <= A[i].length
    // postcondition: print A for the number of rows and columns
    //   specified.  Put each row on a seperate line.  In each row,
    //   put a space between each element.
    public static void print2dArr(int [] [] A, int rows, int columns)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                System.out.print( A[i] [j] + " ");
            }
            System.out.println();
        }
    }

    // Menu option 19: Factorial
    // precondition:  0 <= n
    // postcondition:  If n == 0, 1 has been returned.  Otherwise,
    //   the product of the set of integers that are greater than
    //   or equal to 1 and less than or equal to n has been returned.
    // Examples:  If n == 3, 1*2*3 = 6 has been returned.
    //            If n == 1, 1 has been returned.
    public static int factorial(int n)
    {
        int f = 1; // the value of i factorial until i = n, then its the value of n factorial
        if (n==0)
        {
            return 1;
        }
        else if (n != 0)
        {
            for (int i = 1; i <= n; i++)
            {
                f *= i;
            }
        }
        return f;
    }

/******************************************************************
 *
 * Written work.  Run tests on your code and guess the formulas.
 *   (Insert your answers as part of the comments here):
 *
 * 1.  How many tuples does orderedPairs generate when given a set with two integers?  
 *        Answer:4
 * 2.  How many tuples does orderedPairs generate when given a set with three integers?  
 *        Answer:9
 * 3.  How many tuples does orderedPairs generate when given a set with five integers?  
 *        Answer:25
 * 4.  Guess a general formula for the number of pairs it generates when given a set
 *     n integers.  Express your answer as a formula involving n. 
 *        Answer: n^2
 *
 * 5.  How many tuples does ordered4Tuples generate when given a set with two integers?  Answer:16
 * 6.  How many tuples does ordered4Tuples generate when given a set with three integers?  Answer:81
 * 7.  How many tuples does ordered4Tuples generate when given a set with four integers?  Answer:256
 * 8.  Guess a general formula for the number of tuples it generates when given a set with
 *     n integers.  Express your answer as a formula involving n.
 *        Answer: n^4
 *
 * 9.  How many tuples does orderedPairsNoRepeats generate when given a set with two integer?  
 *        Answer:2
 * 10. How many tuples does it generate when given a set with three integers?  
 *        Answer:6
 * 11. How many does it generate when given a set with four integer?  
 *        Answer:12
 * 12. Guess a general formula for the number of tuples it generates when given a set with
 *     n integers.  Express your answer as a formula involving n.
 *        Answer: n * (n-1)
 *
 * 13. How many tuples does ordered4TuplesNoRepeats generate when given a set with four integers?  
 *        Answer:24
 * 14. How many tuples does it generate when given a set with five integers?  
 *        Answer:120
 * 15. How many tuples does it generate when given a set with six integers?  
 *        Answer:360
 * 16. How many does it generate when given a set with seven integers?
 *        Answer:840
 * 17. Guess a general formula for the number of tuples it generates when given a set with
 *     n integers.  Express your answer as a formula involving n.
 *        Answer: 
 *
 * 18. How many sets does twoSubsets generate when given a set with two integer?  
 *        Answer:1
 * 19. How many sets  does it generate when given a set with three integers?  
 *        Answer:3
 * 20. How many does it generate when given a set with four integer?  
 *        Answer:6
 * 21. What do you think the ratio is between the number of pairs that orderedPairsNoRepeats will 
 *       generate and the number of sets twoSubsets will generate when given a list of n integers.
 *        Answer: 2:1
 * 22. Guess a general formula for the number of sets it generates when given a list with
 *     n integers.  Express your answer as a formula involving n.  You may find it helpful
 *     to combine your answers from problems 12 and 21.
 *        Answer: (n * (n-1)) / 2
 *
 * 23. By doing similar experiments with fourSubsets and comparing them with your answers
 *     from problems 13-16, guess the ratio between the number of tuples that
 *     ordered4TuplesNoRepeats and the number of sets that fourSubsets will return when they 
 *     are both given a list of the same size?
 *        Answer: 24 : 1
 * 24. By combining your answers from problems 17 and 23, guess a general formula for the
 *     number of sets fourSubsets will generate when given a list of size n?
 *        Answer: 
 * 25. (Glory point)  Give a formula ratio of the following two value:
 *        The number of k-tuples with no repeats drawn from a list of length n
 *        The number of subsets of size k drawn from a list of length n
 *        Answer:
 *
 * 26.  Experiment to find the largest value of n for which factorial(n) gives a correct
 *      answer; beyond that point, integer overflow undermines the answer.
 *        Answer: 12
***************************************************************************************/ 

    
    public static void main(String[] args)
    {
         // The following line creates a Scanner object called Input whose 
         // methods hand us inputs that the user types into the keyboard.   For 
         // instance, consider the following line of code that we can
         // now put in our program:
     
         // int i = Input.nextInt();

         // The line scans through zero or more spaces and newlines, followed by
         // an integer, and assigns the integer to i.
     
         // See pages 43-44, which includes a list of other methods, such 
         //  as nextDouble, that a scanner object has available.
        Scanner Input = new Scanner(System.in);

        int choice = -1; // Records the option number the user selected
                         //   from the menu
        int [] Array1;
        int arr1Size;
        int [] Array2;
        int arr2Size;
        int [] Array3;
        int arr3Size;
        System.out.println("The first element of the file should be the size n");
        System.out.println("The next n elements are the values that get filled");
        System.out.println("into the array\n\n");

        System.out.println("Name of file for Array1:  ");
        String filename = Input.next();
        System.out.print ("\n" + filename + "\n\n\n");
        Array1 = readIn(filename); 
        arr1Size = Array1.length;
        System.out.println("Name of file for Array2:  ");
        filename = Input.next();
        System.out.print ("\n" + filename + "\n\n\n");
        Array2 = readIn(filename); 
        arr2Size = Array2.length;
        do
        {
             System.out.println("\n ---------------------------------------");
             System.out.println("                MENU");
             System.out.println("0.  Exit");
             System.out.println("1.  Read a new set of values into Array1[]");
             System.out.println("2.  Read a new set of values into Array2[]");
             System.out.println("3.  printArrFor on Array1[]");
             System.out.println("4.  printArrDo on Array2[]");
             System.out.println("5.  'arrayMax' on Array1");
             System.out.println("6.  'arraySectSum' on Array1");
             System.out.println("7.  'swap' on Array1");
             System.out.println("8.  'arraySectReverse' on Array1");
             System.out.println("9.  'concatArrs' on Array1, Array2");
             System.out.println("10.  'interleaveArrs' on Array1, Array2");
             System.out.println("11.  'merge' on Array1, Array2");
             System.out.println("12.  'orderedPairs' on Array1");
             System.out.println("13.  'ordered4Tuples' on Array1");
             System.out.println("14.  'orderedPairsNoRepeats' on Array1");
             System.out.println("15.  'ordered4TuplesNoRepeats' on Array1");
             System.out.println("16.  'twoSubsets' on Array1");
             System.out.println("17.  '4-Subsets' on Array1");
             System.out.println("18.  'multTable' and 'print2dArr'");
             System.out.println("19.  'factorial'");
                          
             System.out.print("\n    Which? ");
             choice = Input.nextInt();
             System.out.println(choice);


             if (choice == 1){ 
               System.out.println("Name of file for Array1:  ");
               filename = Input.next();
               System.out.print ("\n" + filename + "\n\n\n");
               Array1 = readIn(filename); 
               arr1Size = Array1.length;
             }
             else if (choice == 2){
               System.out.println("Name of file for Array2:  ");
               filename = Input.next();
               System.out.print ("\n" + filename + "\n\n\n");
               Array2 = readIn(filename); 
               arr2Size = Array2.length;
             }
             else if (choice == 3)  
                 printArrFor(Array1, arr1Size); //change to call to printArrFor
             else if (choice == 4) 
                 printArrDo(Array2, arr2Size); // change to call to printArrDo
             else if (choice == 5)
                 System.out.println(arrayMax(Array1, arr1Size));
             else if (choice == 6){
                 System.out.println("Enter start index");
                 int start = Input.nextInt();
                 System.out.println("Enter end index");
                 int end = Input.nextInt();
                 System.out.println("Sum="+arraySectSum(Array1,start,end));
             }
             else if (choice == 7){
                 System.out.println("Enter first index");
                 int start = Input.nextInt();
                 System.out.println("Enter second index");
                 int end = Input.nextInt();
                 swap(Array1, start, end);
             }
             else if (choice == 8){
                 System.out.println("Enter start index");
                 int start = Input.nextInt();
                 System.out.println("Enter end index");
                 int end = Input.nextInt();
                 arrSectReverse(Array1,start,end);
             }
             else if (choice == 9)
                 printArrFor(concatArrs(Array1,arr1Size,Array2,arr2Size),arr1Size+arr2Size);
             else if (choice == 10)
                 printArrFor(interleave(Array1,arr1Size,Array2,arr2Size),arr1Size+arr2Size);
             else if (choice == 11)
                 printArrFor(merge(Array1,arr1Size,Array2,arr2Size),arr1Size+arr2Size);
             else if (choice == 12){
                 int count = orderedPairs(Array1, arr1Size);
                 System.out.println("Count="+count);
             }
             else if (choice == 13){
                 int count = ordered4Tuples(Array1, arr1Size);
                 System.out.println("Count="+count);
             }
             else if (choice == 14){
                 int count = orderedPairsNoRepeats(Array1, arr1Size);
                 System.out.println("Count="+count);
             }
             else if (choice == 15){
                 int count = ordered4TuplesNoRepeats(Array1, arr1Size);
                 System.out.println("Count="+count);
             }
             else if (choice == 16){
                 int count = twoSubsets(Array1, arr1Size);
                 System.out.println("Count="+count);
             }
             else if (choice == 17){
                 int count = fourSubsets(Array1, arr1Size);
                 System.out.println("Count="+count);
             }
             else if (choice == 18){
                 System.out.println("Enter size");
                 int size = Input.nextInt();
                 int [][] table = multTable(size);
                 System.out.println("Enter rows");
                 int rows = Input.nextInt();
                 System.out.println("Enter columns");
                 int col = Input.nextInt();
                 print2dArr(table,rows,col);
             }
             else if (choice == 19){
                 int n = Input.nextInt();
                 if (n >= 0)
                    System.out.println(factorial(n));
             }
        } while (choice != 0);
    }
}