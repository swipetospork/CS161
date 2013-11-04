import java.io.File;
import java.io.IOException;
import java.util.*;

public class Hw7 {
	public static void printArrForward(int A[], int size)
	{
		if (size == 1)
			System.out.println(A[1]);
		else {
			printArrForward(A, size -1);
			System.out.println(A[size]);
		}
	}
	public static void printArrBackward( int A[], int size)
	{
		if (size == 1)
			System.out.println(A[A.length -1]);
		else {
			printArrBackward(A, size -1);
			System.out.println (A[A.length - size]);
		}
	}
	public static int RecSum(int [] A, int size)
	{
		int sum = 0;
		if (size == 1)
			return A[1];
		else {
			sum += A[size];
			sum += RecSum(A, size - 1);
			return sum;
		}
	}
	public static void main(String[] args)
    {
        Scanner Input = new Scanner(System.in);

        int choice = -1; // Records the option number the user selected
                         //   from the menu
        int [] Array1 = new int [] {1, 2, 3, 4, 5};
        int Size = 2;

        do
        {
             System.out.println("\n ---------------------------------------");
             System.out.println("                MENU");
             System.out.println("0.  Exit");
             System.out.println("1.  printArrForward");
             System.out.println("2.  printArrBackward");
             System.out.println("3.  sumArray");
             
                          
             System.out.print("\n    Which? ");
             choice = Input.nextInt();
             System.out.println(choice);


             if (choice == 1){ 
               printArrForward(Array1 , Size);
             }
             else if (choice == 2){
				printArrBackward(Array1 , Size);
             }
             else if (choice == 3) {
             	System.out.println (RecSum(Array1, Size));
             } 
                
             
        } while (choice != 0);
    }
}