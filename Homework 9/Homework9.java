import java.io.File;
import java.io.IOException;
import java.util.*;
public class Homework9 {
	public static int [] [] similarity (String s1, String s2)
	{
		int [] [] matrix = new int [s1.length() +1] [s2.length() +1];
		for (int i = 0; i <= s1.length(); i ++)
		{
			for (int j = 0; j <= s2.length(); j ++)
			{
				if (i ==0 || j ==0)
					matrix [i] [j] = 0;
				else if(s1.charAt(i-1) == s2.charAt(j-1))
				{
					matrix[i] [j] = 1 + matrix [i-1] [j-1];
				}
				else 
				{
					if (matrix [i-1] [j] > matrix [i] [j-1])
						matrix [i] [j] = matrix [i-1] [j];
					else{
						matrix [i] [j] = matrix [i] [j-1] ;
					} 
				}
			}
		}
		return matrix;
	}
	public static double [] [] Truck (int n, int w, int [] W, double [] Profit)
	{
		double [] [] matrix = new double [n+1] [w+1];
		for (int i = 0; i <= n; i ++)
		{
			for (int j = 0; j <= w; j++)
			{
				if ((i == 0 || j == 0))
					matrix [i] [j] = 0;
				else {
					if (W[i-1] > j)
						matrix [i] [j] = matrix [i-1] [j];
					else
					{
						if (Profit[i-1] + matrix [i-1] [j-W[i-1]] > matrix [i-1] [j])
							matrix [i] [j] = Profit[i-1] + matrix [i-1] [j-W[i-1]];
						else matrix [i] [j] = matrix [i-1] [j];
					}
				}
			}
		}
		return matrix;
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
             System.out.println("1.  #similarity");
             System.out.println("2.  #Truck");
  
             System.out.print("\n    Which? ");
             choice = Input.nextInt();
             System.out.println(choice);
			
			if (choice == 1) {
             	String	A = "AGCGTAG";
             	String	B = "GTCAGA";
             	int [] [] matrix = similarity(A, B);
             	for (int i = 0; i <= A.length(); i++)
             	{
             		for (int j = 0; j <= B.length(); j++)
             		{
             			System.out.print (matrix [i] [j]);
             		}
             		System.out.println ();
             	}
             } 
             else if (choice == 2) {
             	int	[] W = new int [] {4,1,2,0};
             	double	[] P = new double [] {2,0,4,0};
             	int n = W.length; 
             	int w = 5;
             	double [] [] matrix = Truck (n, w, W, P);
             	for (int i = 0; i <= n; i++)
             	{
             		for (int j = 0; j <= w; j++)
             		{
             			System.out.print (matrix [i] [j] + " ");
             		}
             		System.out.println ();
             	}
             } 
                
             
        } while (choice != 0);
    }
}