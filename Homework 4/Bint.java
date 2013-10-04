/*import java.util.Scanner;

// This assignment is about developing a class that allows non-negative integers
// of any size.  The representations of the integers are in binary in a "prescribed
// format" described in the comments by the private instance variables.
// ----------------------------------------------------------
// Added 9/18/2013:
//
// On a previous assignment, some of you shortcutted a sort I wanted you to
// write by calling a sort method available on the system.  I gave some credit
// for this, since you met the postcondition and I didn't say you couldn't do it
// that way.
//
// For this assignment, you have to solve the problems yourself.  When I ask you
// to find logFloor by repeatedly dividing by b, don't instead call a method
// from the Math package that gives you the log of a number.  Don't
// turn your Bint class into a wrapper for some class already available on
// the system that accomplishes the same thing the Bint class is
// supposed to accomplish, which is arithmetic on large integers
// that would overflow a standard Java integer.  Create your own array
// of bits and operate on these bits to get your answers.
//
// Can you do a Java vision by b in computing logFloor?  No problem.
// Can you use the mod operator to figure out whether to boost a
// value up to the next higher integer in order to get the ceiling?
// Sure.  Can you add 1 to a number in Java?  That's not a problem.
// If you use a Java expression to add the values of the two parameters
// to plus, however, your program will bomb when we try it on some huge 
// integers.  Similarly, plus1 will bomb if we use it to add 1 to a huge 
// integer.
//
// A rule of thumb is that when you are asked to come up with an algorithm
// to solve a problem, don't use somebody else's algorithm for that same 
// problem, even if that person is somebody who wrote a method that
// is available in Java or in a Java package.

/*  Bint:  Represents non-negative integers of arbitrary size /*
public class Bint
{
   /******************************************************************/
   // Private variables
   
   private boolean [] _Digits;  // Array of digits where true represents 1 
                                // and false represents 0 (or null if value 
                                // is 0)
   private int _length;   // number of digits, *starting at leftmost 1*
                          // _length is 0 if the value is 0
   //
   // You must write your code so that _length and _Digits are always
   //  in the format described in the comments by the private variables.  
   //  Let us call this format the "prescribed format."  A precondition 
   //  on the Bint parameters to every method that we will implicitly 
   //  assume is that they are in the prescribed format.
   /******************************************************************/

   // find floor(log_b n)
   private int logFloor (int n, int b)
   {
       // precondition:  b >= 2 and n >= 1
       // postcondition:  floor(log_b n)
       //  has been returned

       // Explanation:  The floor of a real number is the integer
       //  you get by throwing away any fractional part.
       //
       // The floor of the log of a positive integer n is the number of times
       //  you can divide by b, starting at n, throwing away any fractional
       //  part at each step, until you reach 1.  
       //
       //  For example, the floor of log_2 22 is obtained as follows:
       //  Divide 22 by two to get 11
       //  Divide 11 by two to get 5
       //  Divide 5 by two to get  2
       //  Divide 2 by two to get 1.
       //
       //  That's four divisions, so floor(log_b n) = 4
       //
       //  Notice that floor(log_b 1) = 0
       int count = 0;
       while (n > 1)
     {
         n /= b;
         if (n > 0)
       {
           count ++;
       }
     }
       return count;
   }

   // find the ceiling(log_b n)
   //
   // precondition:  b >= 2 and n >= 1
   // postcondition:  ceiling(log_b n) has been returned
   private int logCeiling (int n, int b)
   {
       // precondition:  b >= 2 and n >= 1
       // postcondition:  ceiling(log_b n)
       //  has been returned

       // Explanation:  The ceiling of a real number is the smallest
       //  integer that's at least as large as the number.
       //  In other words, if there is a fractional part, you round
       //  down rather than up.  The ceiling of an integer is itself.
       //
       // The ceiling of the log of a positive integer n is the number of times
       //  you can divide by b, starting at n, taking the ceiling
       //  at each step, until you reach 1.  
       //
       //  For example, the floor of log_2 22 is obtained as follows:
       //  Divide 22 by two to get 11
       //  Divide 11 by two to get 6  (round up from 5.5)
       //  Divide 6 by two to get  3
       //  Divide 3 by two to get 2.  (round up from 1.5)
       //  Divide 2 by two to get 1.
       //  That's five divisions, so ceiling(log_b n) = 5
       int count = 0;
       while (n > 1)
     {
         if (n % b == 0){
       n /= b;
       // if (n > 0)
           // {
         count ++;
         //}
         }
         else{
      n /= b;
      // if (n > 0)
      //  {
        count ++;
        //  }
      n++;
         }
     }
       return count;
   }

    // An algorithm for finding the binary digits of a non-negative integer n, from
    //     least to most significant.
    //   If n is odd, its least significant digit is 1; otherwise it's zero.
    //   If n <= 1, that's  the end of the story.
    //   Otherwise, floor(n/2) has the same binary representation as
    //    n does, except that it's missing its last digit.  Therefore, if you
    //    find the last digit of this and you get the second-to-last digit of n.
    //    Then divide this by two to get the next digit.  Repeat until you reach 1.
    //
    //   Example:  19 is odd, so its last digit is 1
    //             floor (19/2) = 9 is odd, so its last digit is 1
    //             floor (9/2) = 4 is even, so its last digit is 0
    //             floor (4/2) = 2 is even, so its last digit is 0
    //             floor(2/2) = 1, so its only digit is 1.
    //    Reading out the digits backward gives the digits of 19 from most-to-least
    //     significant:  10011
    //    A hint about why it works:  
    //    The binary representation of 19 is 10011.
    //    The binary representation of 9 is   1001
    //    The binary representation of 4 is    100
    //    The binary representation of 2 is     10
    //    The binary representation of 1 is      1
    //
    //    The base-10 representation of floor(39526/10) is 3952.  
    //    A similar thing happens in base 2.

    // QUESTION:  What is the relationship between floor (log_2 n) and
    //  the number of binary digits in n when n >= 1?  You need to figure
    //  this out in order to figure out how long to make your array of bits.

   private void setLength (int length)
   // precondition:  length >= 0;
   // postcondition:  the length is recorded in the private _length variable
   {
      _length = length;
   }

   public int getLength()
   // returns value in private _length variable
   {
      return _length;
   }


   private boolean setDigit(int index, boolean val)
   // preconditions:  'this' is in the prescribed format
   // postcondition:  if 0 <= index < _length, it assigns the digit
   //  in position index to be val and returns true to indicate success; 
   //  otherwise it does nothing and returns false.
   {
        if(index >= 0 && index <= _length){
            _Digits[index] = val;
            return true;
        }
        else{
            return false;
        }
    }

   private boolean getDigit (int index)
   // precondition:  'this' is in the prescribed format
   // postcondition:  The value of the digit in that position is returned
   //   if 0 <= index < _length; it returns false (0) if index is outside of
   //   this range.
   {
       if (0 <= index && index <= _length)
      {
        return _Digits [index];
      }
       return false;
   }

   private void setDigits(boolean [] Digits)
   // preconditions:  none
   // postcondition:  _Digits points to the Digits array
   {
       _Digits = Digits; 
   }

   // default constructor:  assigns usual representation of 0
   public Bint()
   // preconditions:  none
   // postconditions:  'this' represents 0 in the prescribed format
   {
      setLength(0);
      _Digits = null;
   }

   // constructor that turns a non-negative integer into a Bint
   public Bint (int val)
   // precondition:  none
   // postcondition:  the object contains the prescribed representation
   //   of val if val >= 0; otherwise it contains the prescribed
   //   representation of 0

   // Hints:  Handle the case of 0 separately; use logFloor to help
   //  you figure out how big an array to allocate.
   {
        setLength( logFloor(val, 2) + 1);
        _Digits = new boolean[_length]; 
        if(val < 0){
            setLength(0);
            _Digits = null;
        } 
        
        if( val >= 0){
            for(int i=getLength()-1; i>=0; i--){
                if(val % 2 == 0){
                  setDigit (i, false);
                }
                else{
                  setDigit(i, true);
                }
                val = val/2;
            }
        }
    }

   // copy constructor;  creates a copy of ni
   public Bint (Bint ni)
   // precondition:  ni is in the prescribed format
   // postcondition:  'this' is an exact copy of ni and doesn't share
   //   any memory with it.  ni is unchanged.
   {
     _Digits = new boolean [ni.getLength()];
     setLength(ni.getLength()); 
     for (int i = 0; i < getLength () - 1; i ++)
     {
      setDigit(i, ni.getDigit(i));
     }
   }


   public String toBase2()
   // preconditions:  'this' is in the prescribed format
   // postcondition:  The binary representation of the value represented
   //   by 'this' is returned as a String of 0's and 1's and 'this' is 
   //   unchanged.
   {
      String binary = "";
      for (int i = 0; i < getLength (); i++)
      {
        if (getDigit(i) == false)
        {
          binary += "0";
        }
        if (getDigit(i) == true)
        {
          binary += "1";
        }
      }
      return binary;
   } 

   // preconditions:  'this' is in the prescribed format
   // postcondition:  The base-10 representation of the value represented
   //  by 'this' is returned as a String of digits between 0 and 9, inclusive.
   public String toString()
   {
    int j = 1;
    int number = 0;
    for (int i = getLength() -1; i >= 0; i--)
    {
      if (getDigit(i) == true)
      {
        number += 2 ^ j;
      }
      j++;
    }
    return Integer.toString(number);
   }

   public boolean lt(Bint nj)
   // precondition:  'this' and 'nj' are in the prescribed format
   // postcondition;  has returned 'true' if 'this' represents a smaller
   //   value than 'nj' does, and false otherwise;  'this' and 'nj'
   //   are unchanged.
   {
       if (getLength() < nj.getLength())
           return true;
       else if (getLength() > nj.getLength())
           return false;
       else
       {
           for (int i = getLength() - 1; i >= 0; i--)
           {
               if (getDigit(i) && !nj.getDigit(i))
                  return false;
               else if (!getDigit(i) && nj.getDigit(i))
                  return true;
           }
           return false;
       }
   }

   public boolean gt (Bint nj)
   // like 'lt()' but test whether 'this' is greater than 'nj'
  
   {
       if (getLength() > nj.getLength())
           return true;
       else if (getLength() < nj.getLength())
           return false;
       else
       {
           for (int i = getLength() - 1; i >= 0; i--)
           {
               if (getDigit(i) && !nj.getDigit(i))
                  return true;
               else if (!getDigit(i) && nj.getDigit(i))
                  return false;
           }
           return false;
       }
   }

   public boolean eq (Bint nj)
   // like 'lt()' but tests whether 'this' is equal to 'nj'
    {
       if (getLength() < nj.getLength())
           return false;
       else if (getLength() > nj.getLength())
           return false;
       else
       {
           for (int i = getLength() - 1; i >= 0; i--)
           {
               if (!getDigit(i) && nj.getDigit(i))
                  return false;
               else if (getDigit(i) && !nj.getDigit(i))
                  return false;
           }
           return true;
       }
   }

   public boolean le(Bint nj)
   // like 'lt()' but tests whether 'this' is less than or equal to 'nj'
   {
       if (getLength() < nj.getLength())
           return true;
       else if (getLength() > nj.getLength())
           return false;
       else
       {
           for (int i = getLength() - 1; i >= 0; i--)
           {
               if (getDigit(i) && !nj.getDigit(i))
               {
                  return false;
               }
           }
           return true;
       }
   }
   public boolean ge (Bint nj)
   // like 'lt()' but tests whether 'this' is greater than or equal to 'nj'
   {
       if (getLength() < nj.getLength())
           return false;
       else if (getLength() > nj.getLength())
           return true;
       else
       {
           for (int i = getLength() - 1; i >= 0; i--)
           {
               if (!getDigit(i) && nj.getDigit(i))
               {
                  return false;
               }
           }
           return true;
       }
   }

   public boolean isZero()
   // precondition:  'this' is in the prescribed format
   // postcondition:  has returned 'true' if 'this' represents the value 0,
   //   and has returned 'false' otherwise.  'this' is unchanged.
   {
    for (int i = 0; i < getLength (); i++)
    {
      if (getDigit (i))
       return false;
    }
    return true;
   }

   // The reason you need this:  When you write plus, below, the number of
   // bits of the result will either be equal to the number of bits of
   // the largest of the two operands, or one greater than this.  This
   // depends on whether there is a carry to the last position.  You won't
   // know this until you perform the addition.  So allocate an extra bit
   // for the answer, and if the leading bit turns out to be zero, strip
   // it off to get the answer in the prescribed format.
   public void stripLeadingZeros()
   // precondition:  'this' is in the prescribed format, except that it
   //  is allowed to have leading zeros.  _length represents the number of 
   //  digits, counting the leading zeros.
   // postcondition:  'this' represents the same value in the prescribed
   //  format (no leading zeros)
   {
    int i = 0;
    int j = 0;
    if (isZero())
    {
      setLength(0);
      _Digits = null;
    }
    while (!getDigit (i))
    {
      i++;
    }
    boolean [] _Digitsstripped = new boolean [getLength() - i];
    for (int k = 0; k < getLength() -1; k++)
    {
      if (getDigit(k))
      {
        _Digitsstripped[j] = getDigit(k);
        j++;
      }
    }
    _Digits = _Digitsstripped;
   }

   public Bint plus (Bint nj)
   // preconditions:  'this' and 'nj' are in the prescribed formats.
   // postcondition:  'this' and 'nj' are unchanged and the sum of their
   //   represented values have been returned in the prescribed format
   {
    Bint added = new Bint (getLength() *nj. getLength() );
    boolean carry = false;
    int i = getLength() -1;
    int j = nj.getLength()-1;
    int k = added.getLength() -1;
      if (this.le(nj))
      {
        while (j>=0)
        {
          while (i>=0)
          {
            if (!getDigit(i) && !nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, true);
               carry = false;
              }
             else
             { 
               added.setDigit(k, false);
               carry = false;
             }
            }
            if (!getDigit(i) && nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, false);
               carry = true;
              }
             else
             { 
               added.setDigit(k, true);
               carry = false;
             }
            }
            if (getDigit(i) && !nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, false);
               carry = true;
              }
             else
             { 
               added.setDigit(k, true);
               carry = false;
             }
            }
            if (getDigit(i) && nj.getDigit(j))
            {
               if (carry)
              {
               added.setDigit(k, true);
               carry = true;
              }
             else
             { 
               added.setDigit(k, false);
               carry = true;
             } 
            }
            i--;
            j--;
            k--;
          }
          if (!getDigit(i) && !nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, true);
               carry = false;
              }
             else
             { 
               added.setDigit(k, false);
               carry = false;
             }
            }
            if (!getDigit(i) && nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, false);
               carry = true;
              }
             else
             { 
               added.setDigit(k , true);
               carry = false;
             }
            }
            if (getDigit(i) && !nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, false);
               carry = true;
              }
             else
             { 
               added.setDigit(k, true);
               carry = false;
             }
            }
            if (getDigit(i) && nj.getDigit(j))
            {
               if (carry)
              {
               added.setDigit(k, true);
               carry = true;
              }
             else
             { 
               added.setDigit(k, false);
               carry = true;
             } 
            }
            j--;
            k--;
        }
        k--;
      }
       if (this.gt(nj))
      {
        while (i>=0)
        {
          while (j>=0)
          {
            if (!getDigit(i) && !nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, true);
               carry = false;
              }
             else
             { 
               added.setDigit(k, false);
               carry = false;
             }
            }
            if (!getDigit(i) && nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, false);
               carry = true;
              }
             else
             { 
               added.setDigit(k, true);
               carry = false;
             }
            }
            if (getDigit(i) && !nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, false);
               carry = true;
              }
             else
             { 
               added.setDigit(k, true);
               carry = false;
             }
            }
            if (getDigit(i) && nj.getDigit(j))
            {
               if (carry)
              {
               added.setDigit(k, true);
               carry = true;
              }
             else
             { 
               added.setDigit(k, false);
               carry = true;
             } 
            }
            i--;
            j--;
            k--;
          }
          if (!getDigit(i) && !nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, true);
               carry = false;
              }
             else
             { 
               added.setDigit(k, false);
               carry = false;
             }
            }
            if (!getDigit(i) && nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, false);
               carry = true;
              }
             else
             { 
               added.setDigit(k , true);
               carry = false;
             }
            }
            if (getDigit(i) && !nj.getDigit(j))
            {
              if (carry)
              {
               added.setDigit(k, false);
               carry = true;
              }
             else
             { 
               added.setDigit(k, true);
               carry = false;
             }
            }
            if (getDigit(i) && nj.getDigit(j))
            {
               if (carry)
              {
               added.setDigit(k, true);
               carry = true;
              }
             else
             { 
               added.setDigit(k, false);
               carry = true;
             } 
            }
            i--;
            k--;
        }
        k--;
      }
      return added;
   }


   public Bint plus1 ()
   // precondition:  'this' is in the prescribed format
   // postcondition:  'this' is unchanged, and the value one higher
   //  than its represented value has been returned in the prescribed format.
   {
      Bint a1  = new Bint (1);
      Bint add1 = new Bint ();
      add1 = this.plus(a1);
      return add1;
   }

   private static void printStrings(String[] Strings) 
   {
      for (int i = 0; i < Strings.length; i++) 
        System.out.println(Strings[i]);
   }


   public static void main(String[] args)
   {
      Scanner Input = new Scanner(System.in);

      String[] MenuChoices = 
      {
         "0.  Exit",
         "1.  logFloor, logCeiling",
         "2.  Bint(), toString()",
         "3.  Bint(int), getLength(), getDigit(), toBase2(), toString()",
         "4.  getDigit",
         "5.  Copy constructor Bint(Bint)",
         "6.  lt(), gt(), eq(), le(), ge()",
         "7.  plus",
         "8.  plus1",
      };
          

      int choice = -1; // Records the option number the user selected
                         //   from the menu

      do
      {
          System.out.println("\n ---------------------------------------");
          System.out.println("                MENU");

          printStrings(MenuChoices);

          System.out.print("\n    Which? ");
          choice = Input.nextInt();
          System.out.print("\n\n");
          // "1.  logFloor, logCeiling",
          if (choice == 1)
          {
       Bint ni = new Bint();
             System.out.print("Enter a non-negative integer n >= 1:  ");
             int n = Input.nextInt();
       System.out.print("Enter a non-negative integer b >= 2:  ");
             int b = Input.nextInt();
             System.out.println("The floor of the log of that number is: " + ni.logFloor (n, b));     // insert calls 
             System.out.println("The ceiling of the log of that number is: " + ni.logCeiling (n, b));    //  here
          }

          // "2.  Bint(), toString()",
          if (choice == 2)
          {
             Bint ni = new Bint();
             System.out.println(ni);   // calls toString() on a Bint of value 0
          }
          // "3.  Bint(int), getLength(), getDigit(), toBase2(), toString()",
          if (choice == 3)
          {
             System.out.print("Enter a non-negative integer i:  ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("\nRunning toBase2 on the result of Bint(" + i 
                      + ") yields: " + ni.toBase2());  // fill in the call
             System.out.println("A call to getLength() on it returns: " + ni.getLength());  // fill in the call
          }
          // "4.  getDigit",
          if (choice == 4)
          {
             System.out.print("Enter a non-negative integer i and digit position j:  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("Constructed Bint: " + ni.toString());  // figure out how to call toString here
             System.out.print("The digit at position " + j + " is "); 
             if (ni.getDigit(j))
              System.out.print("1");
             else System.out.print("0");
             // use an if-else statement here to print out 0 if false and 1 if true ..
          }
          // "5.  Copy constructor Bint(Bint)",
          if (choice == 5)
          {
             System.out.print("Enter a non-negative integer i:  ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(ni);
             System.out.println("Constructed Bint: " + ni.toBase2());
             System.out.println("Bint returned by copy constructor: " 
                                 + nj.toBase2());
          }
          // "6.  lt(), gt(), eq(), le(), ge()",
          else if (choice == 6)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             System.out.println ("i < j: " + ni.lt(nj));
             System.out.println ("i <= j: " + ni.le(nj));   // insert calls in these four lines to 
             System.out.println ("i == j: " + ni.eq(nj));   //  print out true/false values ...
             System.out.println ("i >= j: " + ni.ge(nj));
             System.out.println ("i > j: " + ni.gt(nj));
          }
          // "7.  plus",
          else if (choice == 7)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.plus(nj);   // replace call to this constructor with a call to plus.
                                     //  Store the result in nk
             System.out.println(ni.toBase2() + " + " + nj.toBase2() + " = " 
                                + nk.toBase2());
          }
          // "8.  plus1",
          else if (choice == 8)
          {
             System.out.print("Enter a non-negative integer i:   ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nk = new Bint();   // replace call to this constructor with a call to plus1.
                                     //  Store the result in nk
             System.out.println(ni.toBase2() + " + 1 = " + nk.toBase2());
          }
       } while (choice != 0);
    }
}
*/
