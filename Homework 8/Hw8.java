import java.io.File;
import java.io.IOException;
import java.util.*;
public class Hw8 {
	public static void StraightHanoi(int n, int source, int dest)
	{
		if (n == 1)
		{ // base case
			System.out.println("Move disk 1 from peg " + source
			+ " to peg "  + " 2");
			System.out.println("Move disk 1 from peg 2 to peg "  + dest);
		}
		else 
		{
			StraightHanoi(n-1, source, dest);
			System.out.println("Move disk " + n + " from peg " + source +
			 " to peg 2");
			StraightHanoi(n-1, dest, source);
			System.out.println("Move disk " + n + " to peg " + dest);
			StraightHanoi(n -1, source, dest);
		}

	}
	public static void StraightHanoi8(int n, int source, int dest)
	{
		if (n>0)
		{
			if (n == 1)
				{ 
					System.out.println("Move disk 1 from peg " + source
					+ " to peg "  + " 2");
					System.out.println("Move disk 1 from peg 2 to peg "  + dest);
				}
			else 
				{
					StraightHanoi(n-1, source, dest);
					System.out.println("Move disk " + n + " from peg " + source +
					 " to peg 2");
					StraightHanoi(n-1, dest, source);
					System.out.println("Move disk " + n + " to peg " + dest);
					StraightHanoi(n -1, source, dest);
				}
		}

	}

	public static int NoDoubleOnes(int n)
	{
		if (n==0)
			return 0;
		else if (n==1)
			return 2;
		else if (n==2)
			return 3;
		else
		{	
			return (NoDoubleOnes(n-1))+ (NoDoubleOnes(n-2));		
		}
	}
	public static void HanoiCleanup(int n, int [] A, int dest)
	// preconditions:
	// n >= 0 is the number of disks.
	// For 1 <= i <= n, A[i] tells what peg the ith smallest disk is on
	// dest is 1, 2, or 3, and gives the destination peg.
	// postcondition: the shortest sequence of moves that gets the disks
	// piled on the destination peg has been printed out.
	{
		int other = 0;
		if (dest == 1)
			other = 2;
		if (dest == 2)
			other = 3;
		if (dest == 3)
			other = 1;
		if (n==0)
			return;
		if (n==1)
		{
			if (A[n] != dest )
				System.out.println("Move disk " + n + " to peg " + dest);
		}
		else 
			{
				if (A[n] == dest)
					{
						HanoiCleanup(n-1, A, dest);
					}
				else
				{
					
					HanoiCleanup(n-1, A, other);
					System.out.println ("Move disk " + n + " to peg " + dest);
					HanoiCleanup(n-1, A, dest);
				}
			}
	}
	public static int similarity (String s1, String s2)
		{
			if (s1.length() == 0 || s2.length() == 0)
				return 0; 
			else if (s1.equalsIgnoreCase(s2))
				return s1.length();
			else if (s1.charAt(s1.length()-1) == (s2.charAt(s2.length()-1))){
				return similarity(s1.substring(0, s1.length() - 1), s2.substring(0, s2.length() - 1)) +1;				
			}
			else 
			{
				if (similarity(s1.substring(0, s1.length() -1), s2) > similarity(s1, s2.substring(0, s2.length() -1)))
					return similarity(s1.substring(0, s1.length() -1), s2);
				else {
					return similarity(s1, s2.substring(0, s2.length() -1));
				}
			}
		}
	public static double Truck (int n, int w, int [] W, double [] Profit)
		// precondition: n >= 0 is the number of items to choose from;
		// w >= 0 is the maximum weight capacity, and
		// for 0 <= i < n, W[i] and Profit[i] are the weight
		// and profit of item i.
		// postcondition: the maximum profit achievable has been returned
		{
			if (n==0 || w == 0)
				return 0;
			else if (n==1 && W[n] <= w)
				return Profit[n];
			else if (n==1 && W[n] > w)
				return 0;
			else {
				if (Truck (n-1, w-W[n], W, Profit) + Profit[n] >= Truck (n-1, w, W, Profit) && W[n] <= w)
					return Truck (n-1, w-W[n], W, Profit) + Profit[n];
				else return Truck (n-1, w, W, Profit);
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
             System.out.println("1.  #StraightHanoi");
             System.out.println("2.  #StraightHanoi8");
             System.out.println("3.  #NoDoubleOnes");
             System.out.println("4.  HanoiCleanup");
             System.out.println("5.  #similarity");
             System.out.println("6.  #Truck");
                          
             System.out.print("\n    Which? ");
             choice = Input.nextInt();
             System.out.println(choice);


             if (choice == 1){ 
               int	n = Input.nextInt();
               int	source = Input.nextInt();
               int  dest = Input.nextInt();
               StraightHanoi(n , source, dest);
             }
             else if (choice == 2){
               int	n = Input.nextInt();
               int	source = Input.nextInt();
               int  dest = Input.nextInt();
               StraightHanoi8(n , source, dest);
				
             }
             else if (choice == 3) {
             	int	n = Input.nextInt();
             	System.out.println(NoDoubleOnes(n));
             } 
             else if (choice == 4) {
             	int	dest = Input.nextInt();
             	int	[] A = new int [] {0,1,2,2};
             	HanoiCleanup ( A.length -1, A, dest);
             } 
             else if (choice == 5) {
             	String	A = "GAG";
             	String	B = "AGA";
             	System.out.println (similarity (A, B));
             } 
             else if (choice == 6) {
             	int	[] W = new int [] {0,1,3,3,3};
             	double	[] P = new double [] {0,1,3,3,2};
             	int n = W.length-1; 
             	int w = 8;
             	System.out.println (Truck(n, w, W, P));
             } 
                
             
        } while (choice != 0);
    }
}