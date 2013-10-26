import java.util.Scanner;

/*  Bint:  Represents non-negative integers of arbitrary size */
public class Bint
{
   /******************************************************************/
   // You must write your code so that _length and _Digits are always
   //  in the format described here.  Let us call this format the
   //  "prescribed format."  A precondition on the Bint parameters to every
   //  method that we will implicitly assume is that they are in
   //  the prescribed format.
   private boolean [] _Digits;  // Array of digits where true represents 1 
                                // and false represents 0 (or null if value 
                                // is 0)
   private int _length;   // number of digits, *starting at leftmost 1*
                          // _length is 0 if the value is 0
   /******************************************************************/

   // find the floor of the log_b n  (From Hw1)
   private int logFloor (int n, int b)
   {
       // precondition:  b >= 2 and n >= 1
       // postcondition:  the floor of log_b n
       //  has been returned
       int counter = 0;
       while (n >= b)
       {
           n = n / b;
           counter++;
       } 
       return counter;
   }

    // QUESTION:  What is the relationship between floor (log_2 n) and
    //  the number of binary digits in n?  What about the special case
    //  where the value is 0?

   // find the ceiling of the log_b n  
   private int logCeiling (int n, int b)
   {
       // precondition:  b >= 2 and n >= 1
       // postcondition:  the ceiling of log_b n
       //  has been returned
       int counter = 0;
       while (n > 1)
       {
           int boost;
           if (n % b != 0) boost = 1;
           else boost = 0;
           n = n / b + boost;
           counter++;
       } 
       return counter;
   }

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
      if (0 <= index && index < _length)
      {
         _Digits[index] = val;
         return true;
      }
      else
         return false;
   }

   private boolean getDigit (int index)
   // precondition:  'this' is in the prescribed format
   // postcondition:  The value of the digit in that position is returned
   //   if 0 <= index < _length; it returns false (0) if index is outside of
   //   this range.
   {
      if (0 <= index && index < _length)
         return _Digits[index];
      else 
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
      if (val <= 0)
      {
          _length = 0;
          _Digits = null;
      }
      else
      {
          _length = logFloor (val, 2) + 1;
          _Digits = new boolean [_length];
          for (int i = 0; val > 0; i++)
          {
             _Digits[i] = ((val % 2) == 1);
             val /= 2;
          }
      }
   }

   // copy constructor
   public Bint (Bint ni)
   // precondition:  ni is in the prescribed format
   // postcondition:  'this' is an exact copy of ni and doesn't share
   //   any memory with it.  ni is unchanged.
   {
       setLength(ni.getLength());
       if(ni.isZero())
           setDigits(null);
       else 
       {
          setDigits(new boolean [getLength()]);
          for (int i = 0; i < _length; i++)
             setDigit(i, ni.getDigit(i));   
       }
   }


   public String toBase2()
   // preconditions:  'this' is in the prescribed format
   // postcondition:  The binary representation of the value represented
   //   by 'this' is returned as a String of 0's and 1's and 'this' is 
   //   unchanged.
   {
      if (getLength() == 0)
          return "0";
      else
      {
          String s = "";
          for (int i = _length-1; i >= 0; i--)
              if (_Digits[i])
                  s += "1";     
              else
                  s += "0";
          return s;
      }
   } 

   // Multiply or divide the number by a power of two, using integer
   // arithmetic, by simply shifting the digits.  Make sure that
   // you've reallocated the array of digits to the appropriate size,
   // and reset _length to the prescribed value.
   public void shiftBy (int shift)
   // precondition:  'this' is in the prescribed format
   // postcondition:  the number is shifted left by shift positions if shift
   //   is positive, putting in trailing zeros;  it's shifted right by
   //   -shift positions if shift is negative, discarding any digits
   //   that are shifted past the 1's place.  It is once again in the
   //   prescribed format for this new value.
   {
       if (shift == 0) 
           return;
       else if (shift < 0)
       {
           shift = -shift;
           if (shift >= _length)
           {
               _length = 0;
               _Digits = null;
           }    
           else
           {
              boolean [] NewDigits = new boolean [_length - shift];
              for (int i = _length-1; i >= shift; i--)
                 NewDigits[i - shift] = _Digits[i];
              _Digits = NewDigits;
              _length -= shift;
           }
       }
       else    
       {
           boolean [] NewDigits = new boolean[_length + shift];
           for (int i = 0; i < _length; i++)
              NewDigits[i+shift] = _Digits[i]; 
           _Digits = NewDigits;
           _length += shift;
       }
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
       return nj.lt(this);
   }

   public boolean eq (Bint nj)
   // like 'lt()' but tests whether 'this' is equal to 'nj'
   {
       return !lt(nj) && !nj.lt(this);
   }

   public boolean le(Bint nj)
   // like 'lt()' but tests whether 'this' is less than or equal to 'nj'
   {
       return eq (nj) || lt (nj);
   }

   public boolean ge (Bint nj)
   // like 'lt()' but tests whether 'this' is greater than or equal to 'nj'
   {
       return eq (nj) || gt (nj);
   }

   public boolean isZero()
   // precondition:  'this' is in the prescribed format
   // postcondition:  has returned 'true' if 'this' represents the value 0,
   //   and has returned 'false' otherwise.  'this' is unchanged.
   {
       if (_length == 0) return true;
       else return false;
   }

   public Bint plus (Bint nj)
   // preconditions:  'this' and 'nj' are in the prescribed formats.
   // postcondition:  'this' and 'nj' are unchanged and the sum of their
   //   represented values has been returned in the prescribed format
   {
       int maxLength;
       if (nj.getLength() > getLength())
          maxLength = nj.getLength();
       else
          maxLength = getLength();
       Bint result = new Bint();  // where answer will be stored
       result.setLength(maxLength + 1);   // enough room to spare
       result.setDigits(new boolean [result.getLength()]);  // create array for result

       // base-2 addition
       boolean carry = false;
       for (int i  = 0; i < maxLength; i++)
       {
          int count = 0;
          if (getDigit(i)) count++;
          if (nj.getDigit(i)) count++;
          if (carry) count++;
          result.setDigit(i,count%2 == 1);
          carry = (count / 2) == 1;  // 9/24/13 previously, this said carry = count / 2, which works
                                     //  for inelegant reasons, even though count/2 is an integer
                                     //  and carry is a boolean.  This is better.  
       }
       if (carry)
          result.setDigit(result.getLength()-1,true);
       else
          result.setLength(result.getLength() - 1);
       return result;
   }

   public Bint plus1 ()
   // precondition:  'this' is in the prescribed format
   // postcondition:  'this' is unchanged, and the value one higher
   //  than its represented value has been returned in the prescribed format.
   {
      return plus(new Bint(1));
   }

   public Bint minus (Bint nj)
   // precondition:  'this' and 'nj' are in the prescribed format
   // postcondition:  'this' and 'nj' are unchanged, and this's value
   //  minus nj's value has been returned in the prescribed format,
   //  unless nj's value is greater than this's, in which case 0
   //  has been returned in the prescribed format
   {
       if (lt(nj)) return new Bint(0);
       Bint result = new Bint();  // where answer will be stored
       result.setLength(getLength());   // size for now
       result.setDigits(new boolean [result.getLength()]);  
       boolean borrow = false;
       for (int i = 0; i < getLength(); i++)
       {
           int count = 0;
           if (getDigit(i)) count++;
           if (nj.getDigit(i)) count--;
           if (borrow) count--; 
           // Note:  the % operator only acts like "mod" for
           //   positive numbers.  Adding 2 to count won't affect its
           //   value mod 2, but boosts it into the positive range
           result.setDigit(i,(count+2) % 2 == 1);
           borrow = count < 0;
       }
       result.stripLeadingZeros();
       return result;
   }

   public Bint minus1 ()
   // precondition:  'this' is in the prescribed format
   // postcondition:  'this' is unchanged, and the value one smaller
   //  than its represented value has been returned in the prescribed format,
   //  unless 'this' represents 0, in which case 0 is returned in the
   //  prescribed format.
   {
      return minus(new Bint(1));
   }

   public void stripLeadingZeros()
   // precondition:  'this' is in the prescribed format, except that it
   //  is allowed to have leading zeros.  _length represents the number of 
   //  digits, counting the leading zeros.
   // postcondition:  'this' represents the same value in the prescribed
   //  format.
   {
       int position = getLength() - 1;
       while (position >= 0 && !getDigit(position))
          position--;
       setLength(position+1);
       if (_length == 0)   // value is 0
           _Digits = null;
       else
       {
           boolean [] NewDigits = new boolean [getLength()];
           for (position = getLength() - 1; position >= 0; position--)
               NewDigits[position] = getDigit(position);
           _Digits = NewDigits;
       }
   }

  /* public Bint mult (Bint nj)
   // precondition:  'this' and 'nj' are in the prescribed format
   // postcondition:  'this' and 'nj' are unchanged, and this's value
   //  times nj's value has been returned in the prescribed format.
   
   {
       Bint njShift = new Bint (nj);
       Bint total = new Bint(0);
       for (int i = 0; i < this.getLength() ; i++)
       {
           if (getDigit(i))
               total = total.plus (njShift);
           njShift.shiftBy(1);
       }
       return total;
   }*/
   public Bint mult (Bint nj)
   // precondition:  'this' and 'nj' are in the prescribed format
   // postcondition:  'this' and 'nj' are unchanged, and this's value
   //  times nj's value has been returned in the prescribed format.
   
   {
       Bint njShift = new Bint (nj);
       Bint total = new Bint(0);
       for (int i = 0; i < getLength(); i++)
       {
           if (getDigit(i))
               total = total.plus (njShift);
           njShift.shiftBy(1);
       }
       return total;
   }

   public Bint mod (Bint nj)
   // precondition:  'this' and 'nj' are in the prescribed format and
   //  'nj' is not 0
   // postcondition:  this mod nj has been returned in the prescribed format
   {
       int shift = getLength() - nj.getLength();
       Bint njShift = new Bint(nj);
       njShift.shiftBy(shift);
       Bint niRemainder = new Bint(this);
       while (shift >= 0)
       {
           if (njShift.le(niRemainder))
               niRemainder = niRemainder.minus (njShift);
           njShift.shiftBy(-1);
           shift--;
       }
       return niRemainder;
   }

   public Bint toThePower (int n)
   // preconditions:  n >= 0 and 'this' is in the prescribed format
   // postcondition:  this raised to the power n (this^n) is returned in the
   //   prescribed format.  Note:  if n = 0, this is 1, not matter
   //   what 'this' is.  Technically, 0^0 is undefined mathematically, 
   //   but we adopt the commonly-used convention that 0^0 = 1.  (See
   //   mathworld.wolfram.com/ExponentLaws.html.)
   {
       Bint result = new Bint(1);
       for (int i = 1; i <= n; i++)
           result = mult(result);  // same as this.mult(result)
       return result;
   }

   public Bint div (Bint nj)
   // precondition:  'this' and 'nj' are in the prescribed format and
   //  'nj' is not 0
   // postcondition:  the integer part of this nj has been returned
   //   in the prescribed format
   {
       if (lt(nj)) return new Bint(0);
       int shift = getLength() - nj.getLength();
       Bint njShift = new Bint(nj);
       njShift.shiftBy(shift);
       int quotientLength = shift+1;  // upper bound on length of quotient
       Bint result = new Bint();  // where answer will be stored
       result.setLength(quotientLength);   
       result.setDigits(new boolean [result.getLength()]);  
       Bint niRemainder = new Bint(this);
       while (shift >= 0)
       {
           if (njShift.le(niRemainder))
           {
               result.setDigit(shift,true);
               niRemainder = niRemainder.minus (njShift);
           }
           else 
               result.setDigit(shift,false);
           njShift.shiftBy(-1);
           shift--;
       }
       result.stripLeadingZeros();
       return result;
   }

   public Bint squared()
   {
       return mult(this);
   }

   public int toInt ()
   // precondition:  'this' is in the prescribed format *and the value
   //  it represents is at most the value that can be represented in
   //  a Java 'int' variable
   // postcondition:  the represented value has been converted to a Java
   //  'int' value and returned
   {
      if(_length == 0) return 0;
      else
      {
          int result = 0;
          int power = 1;
          for (int i = 0; i < _length; i++, power *= 2)
          {
              if (getDigit(i))
                  result += power;
          }
          return result;
      } 
   }

   public String toString()
   {
      Bint ten = new Bint(10);  // 10 in prescribed Bint format
      Bint d = mod(ten);         // this.mod(ten) -- last digit in Bint format
      if (lt (ten)) return "" + d.toInt();  
      else return (div(ten)).toString() + d.toInt();
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
         "1.  Bint(int), getLength(), toBase2()",
         "2.  getDigit",
         "3.  Copy constructor Bint(Bint)",
         "4.  shiftBy",
         "5.  lt(), gt(), eq(), le(), ge()",
         "6.  plus",
         "7.  mult",
         "8.  toThePower(int)",
         "9.  minus",
         "10. mod",
         "11. div",
         "13. toInt",
         "14. toString",
         "15. logFloor, logCeiling"
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
          if (choice == 1)
          {
             System.out.print("Enter a non-negative integer i:  ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("\nRunning toBase2 on the result of Bint(" + i 
                      + ") yields " + ni.toBase2());
             System.out.println("A call to getLength() on it returns " 
                                 + ni.getLength());
          }
          if (choice == 2)
          {
             System.out.print("Enter a non-negative integer i and digit position j:  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("Constructed Bint: " + ni.toBase2());
             System.out.print("The digit at position " + j + " is ");
             if (ni.getDigit(j)) System.out.println("1");
             else System.out.println("0");
          }
          if (choice == 3)
          {
             System.out.print("Enter a non-negative integer i:  ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(ni);
             System.out.println("Constructed Bint: " + ni.toBase2());
             System.out.println("Bint returned by copy constructor: " 
                                 + nj.toBase2());
          }
          else if (choice == 4)
          {
             System.out.print("Enter a non-negative integer i and a shift j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("Constructed Bint: " + ni.toBase2());
             ni.shiftBy(j);
             System.out.println("After shift by j: " + ni.toBase2());
          }
          else if (choice == 5)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             System.out.println ("i < j: " + ni.lt(nj));
             System.out.println ("i <= j: " + ni.le(nj));
             System.out.println ("i == j: " + ni.eq(nj)); 
             System.out.println ("i >= j: " + ni.ge(nj));
             System.out.println ("i > j: " + ni.gt(nj));
          }
          else if (choice == 6)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.plus(nj);
             System.out.println(ni.toBase2() + " + " + nj.toBase2() + " = " 
                                + nk.toBase2());
          }
          else if (choice == 7)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.mult (nj);
             System.out.println(ni.toBase2() + " * " + nj.toBase2() + 
                                " = " + nk.toBase2());
          }
          else if (choice == 8)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nk = ni.toThePower(j);
             System.out.println(ni.toBase2() + "^" + j + " = " + nk.toBase2());
          }
          else if (choice == 9)
          {
             System.out.print("Enter two non-negative integers i  and j  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.minus (nj);
             System.out.println(ni.toBase2() + " - " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
         
          else if (choice == 10)
          {
             System.out.print("Enter two non-negative integers i  and j w/ j > 0  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.mod (nj);
             System.out.println(ni.toBase2() + " mod " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
          else if (choice == 11)
          {
             System.out.print("Enter two non-negative integers i, j with j > 0  ");
             int i = Input.nextInt();
             int j = Input.nextInt();
             Bint ni = new Bint(i);
             Bint nj = new Bint(j);
             Bint nk = ni.div (nj);
             System.out.println(ni.toBase2() + " div " + nj.toBase2() 
                                + " = " + nk.toBase2());
          }
          else if (choice == 13)
          {
             System.out.print("Enter a non-negative integer i:  ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("Binary representation:  " + ni.toBase2());
             System.out.println("Conversion back to int: " + ni.toInt());
          }
          else if (choice == 14)
          {
             System.out.print("Enter a non-negative integer i:  ");
             int i = Input.nextInt();
             Bint ni = new Bint(i);
             System.out.println("Binary representation:       " + ni.toBase2());
             System.out.println("Conversion back to base ten: " + ni);
             System.out.println("Fast Conversion to base ten: " + ni);
          }
          else if (choice == 15)
          {
             System.out.print("Enter a positive integer in and integer base b >= 2:  ");
             int n = Input.nextInt();
             int b = Input.nextInt();
             Bint bi = new Bint();
             System.out.println("floor of log base b: " + bi.logFloor(n,b));
             System.out.println("ceiling of log base b: " + bi.logCeiling(n,b));
          }
       } while (choice != 0);
    }
}
